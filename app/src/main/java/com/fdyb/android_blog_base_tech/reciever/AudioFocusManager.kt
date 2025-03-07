package com.fdyb.android_blog_base_tech.reciever

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener

/**
 * 音频焦点管理器
 */
class AudioFocusManager(private val context: Context) {
    private val audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private var audioFocusRequest: AudioFocusRequest? = null
    private var onAudioFocusChangeListener: OnAudioFocusChangeListener? = null

    /**
     * 初始化音频焦点请求
     */
    @SuppressLint("NewApi")
    private fun initAudioFocusRequest() {
        audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
            .setOnAudioFocusChangeListener { focusChange ->
                onAudioFocusChangeListener?.onAudioFocusChange(focusChange)
            }
            .build()
    }

    /**
     * 设置音频焦点变化的回调
     */
    fun setOnAudioFocusChangeListener(listener: OnAudioFocusChangeListener) {
        this.onAudioFocusChangeListener = listener
        if (audioFocusRequest == null) {
            initAudioFocusRequest()
        }
    }

    /**
     * 请求音频焦点
     */
    @SuppressLint("NewApi")
    fun requestAudioFocus(): Int {
        if (audioFocusRequest == null) {
            initAudioFocusRequest()
        }
        return audioManager.requestAudioFocus(audioFocusRequest!!)
    }

    /**
     * 放弃音频焦点
     */
    @SuppressLint("NewApi")
    fun abandonAudioFocus(): Int {
        return audioManager.abandonAudioFocusRequest(audioFocusRequest!!)
    }
}