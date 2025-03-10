package com.fdyb.android_blog_base_tech.accessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class WeChatCallMonitorService : AccessibilityService() {

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            packageNames = arrayOf("com.tencent.mm")
            flags = AccessibilityServiceInfo.DEFAULT
        }
        this.serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            if (it.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED && it.packageName == "com.tencent.mm") {
                checkCallStatus(rootInActiveWindow)
            }
        }
    }

    private fun checkCallStatus(root: AccessibilityNodeInfo?) {
        root?.let {
            val nodes = it.findAccessibilityNodeInfosByText("语音通话|视频通话".toRegex())
            val isInCall = nodes.any { node ->
                node.text?.toString()?.contains(Regex("语音通话|视频通话")) ?: false
            }
            
            if (isInCall) {
                Log.d("WeChatCall", "检测到微信通话中")
                sendBroadcast(Intent(ACTION_WECHAT_CALL).apply {
                    putExtra(EXTRA_CALL_STATUS, true)
                })
            }
        }
    }

    override fun onInterrupt() {}

    companion object {
        const val ACTION_WECHAT_CALL = "com.fdyb.android_blog_base_tech.action.CALL_STATUS"
        const val EXTRA_CALL_STATUS = "call_status"
    }
}