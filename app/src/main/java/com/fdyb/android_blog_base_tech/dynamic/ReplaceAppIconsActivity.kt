package com.fdyb.android_blog_base_tech.dynamic

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.fdyb.android_blog_base_tech.R

/**
 *
 *  替换App图标功能
 * @author sharknade on 2025/1/24
 */
class ReplaceAppIconsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replace_app_icons)
        findViewById<Button>(R.id.btn_replace_app_icons).setOnClickListener {

        }
    }



}