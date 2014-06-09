package com.example.sequencelayoutdemo.activity;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastMsg
{
    /**
     * 弹出Toast消息
     * 
     * @param msg
     */
    public static void show(Context cont, String msg)
    {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context cont, int msg)
    {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context cont, String msg, int time)
    {
        Toast.makeText(cont, msg, time).show();
    }

    public static void show(Context cont, int msg, int time)
    {
        Toast.makeText(cont, msg, time).show();
    }

    public static void showTop(Context cont, String msg)
    {
        Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 120);
        toast.show();
    }
}
