package com.recen.learn.practice1;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import com.recen.dotframe.impl.PageWrapper;

public class Practice1 extends PageWrapper<Practice1Repository> {
    public ObservableField<String> formatedPhone = new ObservableField<>();
    public ObservableField<String> smsCode = new ObservableField<>();
    @Override
    protected void initData() {
        //进入页面后的数据初始化

    }

    public void smsCodeBtnClick(){
        page.showMessage("点击了获取验证码");
        mRepository.getData();
    }

    public void voiceSmsCodeBtnClick(){
        page.showMessage("点击了获取语音验证码");
    }

    public void submitBtnClick(){
        page.showMessage("点击了登录");
    }
}
