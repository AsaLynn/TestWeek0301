package com.test.month3.week1.activity;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.test.month3.week1.listener.DialogItemListener1;


public class EnterActivity extends FirstActivity {

    @Override
    protected void click0() {
        DialogPage
                .getInstance()
                .setOnOnDialogItemListener(new DialogItemListener1(this));
    }
}
