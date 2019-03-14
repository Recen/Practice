package com.recen.learn.download.download;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.apache.http.HttpStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxiaofeng02 on 2015/6/25.
 */
public class DownloadFile {
    private boolean stop = false;
    private boolean hasError = false;
    private DownloadBean downloadBean;
    private IDownloadProgress progressOutput;
    private INetworkHandler networkHandler;

    public DownloadFile(DownloadBean bean) {
        this.downloadBean = bean;
    }

    /**
     * @param progressOutput 下载进度输出类
     */
    public final void setProgressOutput(IDownloadProgress progressOutput) {
        if (progressOutput != null) {
            this.progressOutput = progressOutput;
        }
    }

    public void start() {
        final ProgressOutput output = new ProgressOutput(Looper.getMainLooper());
        RandomAccessFile randomFile = null;
        FileOutputStream fos = null;
        while (!this.stop) {
            InputStream input = null;
            try {
                if (downloadBean.size <= 0) {
                    downloadBean.size = getFileSize();
                    if (downloadBean.size <= 0) {
                        continue;
                    }
                }

                Map<String, String> headersMap = new HashMap<>(1);
                if (downloadBean.loadedSize > 0) {
                    //断点续传方式
                    String property = "bytes=" + downloadBean.loadedSize + "-" + (downloadBean.size - 1);
                    headersMap.put("Range", property);
                    if (randomFile == null) {
                        randomFile = new RandomAccessFile(downloadBean.savePath, "rw");
                        randomFile.setLength(downloadBean.size);
                    }
                } else if (fos == null) {
                    // 采用普通的下载方式
                    File file = new File(downloadBean.savePath);
                    if (file.getParentFile().exists() == false) {
                        file.getParentFile().mkdirs();
                    }
                    if (file.exists() == false) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                }

                networkHandler = NetworkHandlerFactory.createHandler();
                Response response = networkHandler.synRequest(new Request(downloadBean.url, downloadBean.url, headersMap));
                int responseCode = response.getCode();
                switch (responseCode) {
                    case HttpStatus.SC_OK:
                    case HttpStatus.SC_CREATED:
                    case HttpStatus.SC_ACCEPTED:
                    case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION:
                    case HttpStatus.SC_NO_CONTENT:
                    case HttpStatus.SC_RESET_CONTENT:
                    case HttpStatus.SC_PARTIAL_CONTENT:
                    case HttpStatus.SC_MULTI_STATUS:
                        input = response.getStream();
                        break;
                    default:
                        this.stop = true;
                        this.hasError = true;
                        output.sendEmptyMessage(0);
                        break;
                }
                if (this.stop) {
                    break;
                }
                if (input == null) {
                    continue;
                }
                int previousProgress = 0;
                long downloadSizeInSpeedReport = 0;
                long previousReportSpeedTime = System.currentTimeMillis();
                int size;
                byte[] buffer = new byte[1024];
                do {
                    size = input.read(buffer, 0, buffer.length);

                    //下载速度统计，每间隔3秒计算1次
                    long nowTime = System.currentTimeMillis();
                    double intervalTime = (nowTime - previousReportSpeedTime) / (double) 1000;
                    if (size != -1) {
                        downloadSizeInSpeedReport += size;
                    }
                    if (intervalTime >= 2) {
                        downloadBean.downloadSpeed = downloadSizeInSpeedReport / intervalTime;
                        downloadSizeInSpeedReport = 0;
                        previousReportSpeedTime = nowTime;
                    }

                    if (size != -1) {
                        if (fos != null) {
                            fos.write(buffer, 0, size);
                        } else if (randomFile != null) {
                            randomFile.seek(downloadBean.loadedSize);
                            randomFile.write(buffer, 0, size);
                        }
                        downloadBean.loadedSize += size;
                        int progress = (int) (downloadBean.loadedSize * 100 / downloadBean.size);
                        if (progress != previousProgress) {
                            previousProgress = progress;
                            output.sendEmptyMessage(0);
                        }
                    }
                } while (size > -1 && !stop && downloadBean.enable);
                this.stop = true;
                output.sendEmptyMessage(0);
            } catch (SocketTimeoutException e) {
                this.stop = true;
                this.hasError = true;
                output.sendEmptyMessage(0);
            } catch (IOException e) {
                this.stop = true;
                this.hasError = true;
                output.sendEmptyMessage(0);
            } catch (Exception e) {
                this.stop = true;
                this.hasError = true;
                output.sendEmptyMessage(0);
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                    if (randomFile != null) {
                        randomFile.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private long getFileSize() throws Exception {
        long fileLength = -1;
        INetworkHandler handler = NetworkHandlerFactory.createHandler();
        Response response = handler.synRequest(new Request(downloadBean.url, downloadBean.url, null));
        if (response != null) {
            fileLength = response.getContentLength();
        }

        return fileLength;
    }

    private class ProgressOutput extends Handler {
        private boolean isFinished = false;

        @SuppressLint("HandlerLeak")
        public ProgressOutput(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (progressOutput == null || isFinished) {
                return;
            }
            try {
                int progress = 0;
                if (downloadBean.size > 0) {
                    progress = (int) ((downloadBean.loadedSize * 100) / downloadBean.size);
                }

                if (isStop()) {
                    isFinished = true;
                    if (!hasError()) {
                        //进度100则为完成，否则是用户暂停，这时不给回调，不用刷新通知状态
                        if (progress == 100) {
                            progressOutput.downloadSuccess();
                        }
                    } else if (progress > 100) {
                        progressOutput.downloadFailure();
                        deleteFile();
                    } else {
                        progressOutput.downloadFailure();
                    }
                } else {
                    progressOutput.downloadProgress(progress);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteFile() {
        File file = new File(downloadBean.savePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public final boolean isStop() {
        return this.stop;
    }

    public final void stop() {
        stop = true;
        if (networkHandler != null)
            networkHandler.cancel(downloadBean.url);
    }

    public final boolean hasError() {
        return hasError;
    }

    public interface IDownloadProgress {

        void downloadProgress(int progress);

        void downloadSuccess();

        void downloadFailure();
    }
}
