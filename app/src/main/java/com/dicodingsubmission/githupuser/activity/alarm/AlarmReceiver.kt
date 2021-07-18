package com.dicodingsubmission.githupuser.activity.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.dicodingsubmission.githupuser.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        const val REMINDER = "repeating_alarm"
        const val KEY_TYPE = "type"
        const val KEY_MESSAGE = "message"
        private const val ID_REMINDER = 100
        private const val TIME_FORMAT = "HH:mm"

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(KEY_MESSAGE)

        if (message!=null){
            showAlarmNotification(context,message)
        }

    }

    private fun showAlarmNotification(context: Context?, message: String) {

        val channelId = "Channel_1"
        val channelName = "AlarmManager channel"

        val intent= context?.packageManager?.getLaunchIntentForPackage("com.dicodingsubmission.githupuser")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notificationManager= context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_alarm)
                .setContentIntent(pendingIntent)
                .setContentTitle(context.resources.getString(R.string.title_alarm))
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setSound(alarmSound)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(channelId)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(ID_REMINDER, notification)
    }

    fun setRepeatingReminder(context: Context, type: String, time: String, message: String){
        if (isDataInvalid(time, TIME_FORMAT))return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(KEY_MESSAGE, message)
        intent.putExtra(KEY_TYPE, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REMINDER, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

        Toast.makeText(context, "Alarm reminder successfully created", Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REMINDER
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, "Reminder alarm cancelled", Toast.LENGTH_SHORT).show()
    }
    private fun isDataInvalid(time: String, timeFormat: String): Boolean {
        return try {
            val dateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(time)
            false
        }catch (e : ParseException){
            true
        }
    }
}