package com.karlituxd.takeapicture1

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startForResult =
            this@MainActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    val bitmap : Any? = intent?.extras?.get("data")
                    if (bitmap != null) {
                        findViewById<ImageView>(R.id.my_image).setImageBitmap(bitmap as Bitmap)
                    }
                }
            }
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){

            findViewById<Button>(R.id.my_button).setOnClickListener {
                val enrollIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startForResult.launch(enrollIntent)
            }
        }else{
            findViewById<Button>(R.id.my_button).isEnabled = false
        }
    }
}