package com.dicodingsubmission.githupuser.activity.alarm

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private  var dtListener : DialogTimeListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dtListener = context as DialogTimeListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR_OF_DAY)
        val minute = calender.get(Calendar.MINUTE)

        val formatHour24 = true

        return TimePickerDialog(activity, this, hour, minute, formatHour24)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        dtListener?.dialogSetTime(tag, hourOfDay, minute)
    }

    interface DialogTimeListener {
        fun dialogSetTime(tag : String?, hourOfDay : Int , minute : Int)
    }

    override fun onDetach() {
        super.onDetach()
        dtListener = null
    }


}
