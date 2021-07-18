package com.dicodingsubmission.githupuser.activity.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.dicodingsubmission.githupuser.activity.alarm.AlarmActivity
import com.dicodingsubmission.githupuser.activity.main.MainActivity
import com.dicodingsubmission.githupuser.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            buttonBackSetting.setOnClickListener {
                startActivity(Intent(this@SettingActivity, MainActivity::class.java))
            }

            buttonChangeLanguage.setOnClickListener{
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }

            buttonReminder.setOnClickListener{
                startActivity(Intent(this@SettingActivity, AlarmActivity::class.java))
            }
        }


    }
}