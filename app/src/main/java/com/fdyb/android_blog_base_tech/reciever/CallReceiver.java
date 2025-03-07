package com.fdyb.android_blog_base_tech.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.Objects;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d("CallReceiver", "拨打的电话: " + phoneNumber);
        // 在这里可以添加更多逻辑，例如记录或通知
        if(TextUtils.equals(TelephonyManager.EXTRA_STATE_OFFHOOK, state)) {
            Log.d("CallReceiver", "communication: " + phoneNumber);
        } else if(TextUtils.equals(TelephonyManager.EXTRA_STATE_IDLE, state)) {
            Log.d("CallReceiver", "idle: " + phoneNumber);
        } else if(TextUtils.equals(TelephonyManager.EXTRA_STATE_RINGING, state)) {
            Log.d("CallReceiver", "running: " + phoneNumber);
        }
    }
}