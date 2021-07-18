package com.dicodingsubmission.githupuser.activity.alarm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicodingsubmission.githupuser.R
import com.dicodingsubmission.githupuser.activity.setting.SettingActivity
import com.dicodingsubmission.githupuser.databinding.ActivityAlarmBinding
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivity : AppCompatActivity(), View.OnClickListener, TimePickerFragment.DialogTimeListener {

    private lateinit var binding :ActivityAlarmBinding
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var reminderModel: ReminderModel

    companion object{
        private const val  TIME_PICKER_REPEAT_TAG = "time_picker_repeat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)

        binding.apply {

            toggleAlarm.isChecked = reminderPreference.getReminder().isReminded

            setTimeReminder.setOnClickListener(this@AlarmActivity)
            buttonBackAlarm.setOnClickListener(this@AlarmActivity)
            buttonBackAlarm.setOnClickListener {
                startActivity(Intent(this@AlarmActivity,SettingActivity::class.java))
            }
            toggleAlarm.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    saveReminder(false)
                    val repeatTime = tvReminderAlarm.text.toString()
                    val repeatMessage = messageReminder.text.toString()
                    alarmReceiver.setRepeatingReminder(this@AlarmActivity, AlarmReceiver.REMINDER,
                        repeatTime, repeatMessage)
                }else {
                    alarmReceiver.cancelAlarm(this@AlarmActivity)
                }
            }
        }

        alarmReceiver = AlarmReceiver()
    }

    private fun saveReminder(state : Boolean){
        val reminderPreference = ReminderPreference(this)
        reminderModel = ReminderModel()
        reminderModel.isReminded = state
        reminderPreference.setReminder(reminderModel)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.set_time_reminder ->{
                val timePickerFragment = TimePickerFragment()
                timePickerFragment.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }

            R.id.button_back_alarm ->{
                startActivity(Intent(this, SettingActivity::class.java))
            }

        }
    }

    override fun dialogSetTime(tag: String?, hourOfDay: Int, minute: Int) {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            TIME_PICKER_REPEAT_TAG -> binding.tvReminderAlarm.text = dateFormat.format(calendar.time)
            else -> {
            }
        }
    }

}