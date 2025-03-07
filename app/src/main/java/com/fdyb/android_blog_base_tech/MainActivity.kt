package com.fdyb.android_blog_base_tech

import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fdyb.android_blog_base_tech.dynamic.ReplaceAppIconsActivity
import com.fdyb.android_blog_base_tech.reciever.AudioFocusManager
import com.fdyb.android_blog_base_tech.ui.theme.Android_blog_base_techTheme

class MainActivity : ComponentActivity() {
    val itemData = listOf("动态替换图标", "动态切换主题")
    private lateinit var audioFocusManager: AudioFocusManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_blog_base_techTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(Modifier.padding(innerPadding)) {
                        items(itemData.size) { index ->
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                .height(50.dp)
                                .border(1.dp, Color.Black)
                                .clickable { jumpToDetail(index) }
                                ,
                                verticalArrangement = Arrangement.Center) {
                                Text(
                                    text = itemData[index],
                                )
                            }

                        }
                    }
                }
            }
        }

        initAudioManager()
    }

    private fun initAudioManager() {

        // 初始化 AudioFocusManager
        audioFocusManager = AudioFocusManager(this)

        // 设置音频焦点变化的回调
        audioFocusManager.setOnAudioFocusChangeListener { focusChange ->
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> {
                    // 获取音频焦点
                    println("Audio focus gained")
                }
                AudioManager.AUDIOFOCUS_LOSS -> {
                    // 永久失去音频焦点
                    println("Audio focus lost permanently")
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    // 临时失去音频焦点
                    println("Audio focus lost temporarily")
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    // 临时失去音频焦点，但可以降低音量继续播放
                    println("Audio focus lost temporarily, can duck")
                }
            }
        }

        // 请求音频焦点
        val result = audioFocusManager.requestAudioFocus()
        when (result) {
            AudioManager.AUDIOFOCUS_REQUEST_GRANTED -> {
                println("Audio focus request granted")
            }
            AudioManager.AUDIOFOCUS_REQUEST_FAILED -> {
                println("Audio focus request failed")
            }
        }
    }

    private fun jumpToDetail(index:Int) {
        startActivity(Intent(this,ReplaceAppIconsActivity::class.java))
    }


    override fun onDestroy() {
        super.onDestroy()
        // 放弃音频焦点
        audioFocusManager.abandonAudioFocus()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_blog_base_techTheme {
        Greeting("Android")
    }
}