package com.recen.learn.practice1;

import android.databinding.ObservableField;

import com.recen.dotframe.interfaces.IPage;
import com.recen.learn.model.ReadData;

import retrofit2.http.PUT;


public class PracticeItem {
    public IPage page;
    public String content;
    public ObservableField<String> cover = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> createdTime = new ObservableField<>();

    public PracticeItem(ReadData data , IPage page) {
        this.page = page;
        cover.set(data.cover);
        title.set(data.title);
        createdTime.set(data.created_at);
        this.content = data.content;
    }

    public void showDetail(){
        page.showDialog(1,content,null);
    }
}
