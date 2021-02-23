/*******************************************************************************
 *
 * Copyright RectiCode(c) 2020.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and de-compilation.
 *
 * Created by Ali Mohammad
 *
 ******************************************************************************/

package com.aligmohammad.doctorappclient.ui.fragments.doctorlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aligmohammad.doctorappclient.data.model.Doctor
import com.aligmohammad.doctorappclient.data.model.Place

class DoctorListViewModel : ViewModel() {

    lateinit var doctorListLiveData: MutableLiveData<List<Doctor>>

    fun getLabs(): ArrayList<Place> {
        val labList = arrayListOf<Place>()

        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://medicalentitystorageprod.blob.core.windows.net/157544/Profile/passport_df603e88-10a4-455b-a0dc-0db8b68c6d2d.jpeg",
                "Amman, Jordan",
                1,
                2,
                4,
                "Mega Labs","",
                "",
                "",
                "",
                null, null, null,
                "",
                "Lab",
                null
            )
        )
        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://media.glassdoor.com/sqll/658249/omega-laboratories-squarelogo-1524436963939.png",
                "Ma\'an, Jordan",
                4,
                2,
                4,
                "Omega Labs","",
                "",
                "",
                "",
                null, null, null,
                "",
                "Lab",
                null

            )
        )
        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://www.360moms.net/sites/default/files/offers/biolab.jpg",
                "Amman, Jordan",
                2,
                2,
                4,
                "Biolab",
                "",
                "",
                "",
                "",
                null, null, null,
                "",
                "Lab",
                null

            )
        )
        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://www.360moms.net/sites/default/files/offers/Medlabs_0.jpg",
                "Zarga, Jordan",
                3,
                2,
                4,
                "medlabs","",
                "",
                "",
                "",
                null, null, null,
                "",
                "Lab",
                null

            )
        )

        return labList
    }

    fun getXRays(): ArrayList<Place> {
        val labList = arrayListOf<Place>()

        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://media-exp1.licdn.com/dms/image/C560BAQFVBiQXoVRauQ/company-logo_200_200/0?e=2159024400&v=beta&t=9HGjbEZdSeYEfWuLFIM88NfSLjCoVPIuCqMrks8DlmI",
                "Amman, Jordan",
                4,
                20,
                5,
                "Medray",
                "",
                "",
                "",
                "",
                null, null, null,
                "",
                "X-Ray",
                null

            )
        )
        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://medicalentitystorageprod.blob.core.windows.net/18906/Profile/passport_7918d6af-1e68-4985-bbdc-f6821e7aca03.jpg",
                "Ma\'an, Jordan",
                5,
                2,
                4,
                "My Ray",
                "",
                "",
                "",
                "",
                null, null, null,
                "",
                "X-Ray",
                null

            )
        )

        return labList
    }

    fun getXPharmacies(): ArrayList<Place> {
        val labList = arrayListOf<Place>()

        labList.add(
            Place(
                "",
                "",
                "",
                "",
                "https://pharmacy-one.com/images/fbCover.png",
                "Amman, Jordan",
                4,
                6,
                4,
                "Pharmacy One",
                "",
                "",
                "",
                "",
                null, null, null,
                "",
                "Pharmacy",
                null

            )
        )


        return labList
    }

    fun getDoctorList(): ArrayList<Doctor> {
        val doctorList = arrayListOf<Doctor>()
        doctorList.add(
            Doctor(
                "96277733302",
                "+96277733302",
                "Ali Mohammad",
                "Heart",
                "96277733302@doctormyclinic.com",
                "96277733302",
                "https://images.pexels.com/photos/3279197/pexels-photo-3279197.jpeg",
                "Amman",
                "Tabarbour",
                "Nat Health",
                null,
                null,
                "",
                null,
                "",
                listOf("Morning", "After noon"),
                listOf("10:00 - 11:00", "11:00 - 12:00"),
                null,
                null,
                "Jordan Hospital",
                arrayListOf(),
                0,
                "",
                4,
                listOf(),
                listOf("NatHealth"),
                "Amman, Jordan",
                34.1f,
                35f
            )
        )
        return doctorList
    }

}