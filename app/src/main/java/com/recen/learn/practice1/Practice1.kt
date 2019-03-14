package com.recen.learn.practice1

import android.databinding.ObservableArrayList
import android.databinding.ObservableField

import com.recen.dotframe.impl.PageWrapper
import com.recen.dotframe.interfaces.RepoCallback
import com.recen.learn.download.update.Update
import com.recen.learn.model.ReadData

import java.util.ArrayList

class Practice1 : PageWrapper<Practice1Repository>() {

    var dataList = ObservableArrayList<PracticeItem>()
    var textField = ObservableField("--")
    override fun initData() {
        mRepository.getData(object : RepoCallback<List<ReadData>> {
            override fun onSuccess(data: List<ReadData>) {
                val list = ArrayList<PracticeItem>()
                for (item in data) {
                    list.add(PracticeItem(item, page))
                }
                dataList.addAll(list)
            }

            override fun onFailure() {

            }
        })
    }

    fun sendSmsCode() {

    }

    fun login() {
        page.showMessage("login")
    }

}
