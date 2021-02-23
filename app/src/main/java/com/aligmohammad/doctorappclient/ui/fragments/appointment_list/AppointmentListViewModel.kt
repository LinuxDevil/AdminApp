package com.aligmohammad.doctorappclient.ui.fragments.appointment_list

import androidx.lifecycle.ViewModel
import com.aligmohammad.doctorappclient.data.model.Appointment
import com.aligmohammad.doctorappclient.data.model.DateTime
import com.aligmohammad.doctorappclient.data.model.User

class AppointmentListViewModel : ViewModel() {

    val imageList = arrayListOf<String>(
        "https://medicalentitystorageprod.blob.core.windows.net/157544/Profile/passport_df603e88-10a4-455b-a0dc-0db8b68c6d2d.jpeg",
        "https://media.glassdoor.com/sqll/658249/omega-laboratories-squarelogo-1524436963939.png",
        "https://www.360moms.net/sites/default/files/offers/biolab.jpg",
        "https://www.360moms.net/sites/default/files/offers/Medlabs_0.jpg",
        "https://medicalentitystorageprod.blob.core.windows.net/18906/Profile/passport_7918d6af-1e68-4985-bbdc-f6821e7aca03.jpg",
        "https://www.tipntag.com/files/get/original/place/_5315b4cedb35e8.01027749_rawhipharmacy.jpg")

    fun provideList(): List<Appointment> {
        val list = arrayListOf<Appointment>()

//        list.add(Appointment(DateTime("12/12/2020"), DateTime("10:00 - 11:00"),"Amman", 4, imageList[0], User("Ali Mohammad"), false))
//        list.add(Appointment(DateTime("11/12/2020"), DateTime("11:00 - 12:00"),"Zarka", 5, imageList[1], User("Canaan Mohammad"), true))
//        list.add(Appointment(DateTime("10/12/2020"), DateTime("12:00 - 01:00"),"Amman", 2, imageList[2], User("Ghassan Mohammad"), false))
//        list.add(Appointment(DateTime("09/12/2020"), DateTime("01:00 - 02:00"),"Ma'an", 3, imageList[3], User("Ahmad Mohammad"), true))
//        list.add(Appointment(DateTime("08/12/2020"), DateTime("02:00 - 03:00"),"Amman", 1, imageList[4], User("Osama Mohammad"), false))
//        list.add(Appointment(DateTime("07/12/2020"), DateTime("03:00 - 04:00"),"Irbid", 4, imageList[5], User("Tareq Mohammad"), true))

        return list
    }

}