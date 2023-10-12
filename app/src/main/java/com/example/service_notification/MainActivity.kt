package com.example.service_notification

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.service_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)== PackageManager.PERMISSION_GRANTED){
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
            }else{
                requestPermissionLuancher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
            /*ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.POST_NOTIFICATIONS
                ),0
            )*/
        }
        setContentView(binding.root)

    }
    private val requestPermissionLuancher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted:Boolean->
        if(isGranted){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package",packageName,null)
            intent.data = uri
            startActivity(intent)
        }
    }


}