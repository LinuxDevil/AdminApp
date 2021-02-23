package com.aligmohammad.doctorappclient.ui.dialogs.appointmentmanagment.singleappointment

import androidx.lifecycle.ViewModel
import com.aligmohammad.doctorappclient.data.model.DateTime

class SingleAppointmentViewModel : ViewModel() {

    fun getTimes(): List<DateTime> {
        val timeArray = arrayListOf<DateTime>()

        timeArray.add(DateTime("05:00 - 08:00"))
        timeArray.add(DateTime("08:00 - 10:00"))
        timeArray.add(DateTime("10:00 - 12:00"))
        timeArray.add(DateTime("01:00 - 03:00"))
        timeArray.add(DateTime("03:00 - 05:00"))
        timeArray.add(DateTime("05:00 - 08:00"))
        timeArray.add(DateTime("08:00 - 10:00"))
        timeArray.add(DateTime("10:00 - 12:00"))

        return timeArray
    }

    fun getDates(): List<DateTime> {
        val dateArray = arrayListOf<DateTime>()

        dateArray.add(DateTime("Sunday 12/12/2020"))
        dateArray.add(DateTime("Monday 13/12/2020"))
        dateArray.add(DateTime("Tuesday 14/12/2020"))
        dateArray.add(DateTime("Wednesday 15/12/2020"))
        dateArray.add(DateTime("Thursday 16/12/2020"))
        dateArray.add(DateTime("Friday 17/12/2020"))
        dateArray.add(DateTime("Saturday 18/12/2020"))

        return dateArray
    }

}