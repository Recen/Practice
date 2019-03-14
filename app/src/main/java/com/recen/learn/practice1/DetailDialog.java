package com.recen.learn.practice1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recen.dotui.view.BaseDialog;
import com.recen.learn.R;

public class DetailDialog extends BaseDialog {
    private Spanned content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_detail, container, false);
        TextView context = view.findViewById(R.id.content);
        context.setText(content);
        return view;
    }

    public static void show(FragmentActivity activity, String content){
        DetailDialog dialog = new DetailDialog();
        dialog.content = Html.fromHtml(content);
        dialog.fixedShow(activity);
    }
}
