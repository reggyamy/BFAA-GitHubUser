package com.dicodingsubmission.githupuser.activity.alarm

import android.content.Context

class ReminderPreference(context: Context) {
    companion object{
        const val  NAME_PREFERENCE = "reminder_preference"
        private const val REMINDER = "is remind"
    }

    private  val preference = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)

    fun setReminder(value: ReminderModel){
        val editor = preference.edit()
        editor.putBoolean(REMINDER,value.isReminded)
        editor.apply()
    }

    fun getReminder(): ReminderModel{
        val model = ReminderModel()
        model.isReminded = preference.getBoolean(REMINDER, false)
        return model
    }
}