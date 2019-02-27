/*
 * Copyright (c) 2019 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.libselinux.sample;

import android.app.Activity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.ViewGroup;
import android.widget.TextView;

import me.zhanghai.android.libselinux.SeLinux;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String text;
        try {
            text = SeLinux.lgetfilecon("/");
        } catch (ErrnoException e) {
            text = e.getMessage();
        }

        TextView textView = new TextView(this);
        textView.setText(text);
        setContentView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
