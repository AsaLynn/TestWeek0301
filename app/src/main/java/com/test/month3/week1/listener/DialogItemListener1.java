package com.test.month3.week1.listener;

import android.app.Activity;

import com.example.demonstrate.adapter.testname.p1.w1.BaseT3P1W1ILis;
import com.test.month3.week1.activity.NavigationActivity;
import com.test.month3.week1.activity.Test1Activity;
import com.test.month3.week1.test1.Test0Activity;
import com.test.month3.week1.test1.Test2Activity;

/**
 * Created by think on 2018/3/8.
 */

public class DialogItemListener1 extends BaseT3P1W1ILis {


    public DialogItemListener1(Activity activity) {
        super(activity);
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if (which == 0) {
            return Test0Activity.class;
        } else if (which == 1) {
            return Test1Activity.class;
        } else if (which == 2) {
            return Test2Activity.class;
        }else if (which == 3) {
            return NavigationActivity.class;
        }else if (which == 4) {
            return Test1Activity.class;
        }
        return null;
    }
}
