package com.example.service_notification

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.service_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.POST_NOTIFICATIONS
                ),0
            )
        }
        setContentView(binding.root)
            binding.startBTN.setOnClickListener{
                Intent(applicationContext,MyService::class.java).also {
                    it.action = MyService.Action.START.toString()
                    startService(it)
                    Toast.makeText(this,"Service Start",Toast.LENGTH_SHORT).show()
                }
            }

            binding.stopBTN.setOnClickListener{
                Intent(applicationContext,MyService::class.java).also {
                    it.action = MyService.Action.STOP.toString()
                    startService(it)
                    Toast.makeText(this,"Service Stop",Toast.LENGTH_SHORT).show()
                }
            }
        }
}