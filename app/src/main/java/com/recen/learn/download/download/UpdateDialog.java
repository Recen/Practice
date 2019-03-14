package com.recen.learn.download.download;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.recen.learn.R;

public class UpdateDialog extends McDialog{
    private ProgressBar mProgressBar;
    private TextView mTitle;
    private TextView mProgress;
    private Object instance;
    private TextView mCancel;
    private String downloadUrl;

    public UpdateDialog() {
    }

    @SuppressLint("ValidFragment")
    public UpdateDialog(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mc_update_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mProgress = (TextView)view.findViewById(R.id.progressbar_tv);
        mCancel = (TextView) view.findViewById(R.id.update_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(getActivity(), DownloadService.class);
                cancelIntent.putExtra(DownloadService.KEY_URL, downloadUrl);
                cancelIntent.putExtra(DownloadService.KEY_DOWNLOAD_ACTION, DownloadAction.CANCEL);
                getActivity().startService(cancelIntent);
                dismiss();
            }
        });
        mProgressBar.setMax(100);
    }

    public void changeProgress(int progress){
        if (mProgressBar != null){
            mProgressBar.setProgress(progress);
            mProgress.setText(progress+"%");
        }
    }
}
