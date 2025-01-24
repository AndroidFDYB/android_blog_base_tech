package com.fdyb.android_blog_base_tech

import android.content.Intent
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
import com.fdyb.android_blog_base_tech.ui.theme.Android_blog_base_techTheme

class MainActivity : ComponentActivity() {
    val itemData = listOf("动态替换图标", "动态切换主题")
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
    }

    private fun jumpToDetail(index:Int) {
        startActivity(Intent(this,ReplaceAppIconsActivity::class.java))
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