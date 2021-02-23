package com.aligmohammad.doctorappclient.data.network

import com.aligmohammad.doctorappclient.data.model.Doctor
import com.aligmohammad.doctorappclient.data.model.Place
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceFirebaseModel
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceTypesFirebaseModel
import com.aligmohammad.doctorappclient.data.model.firebasemodels.MajorsFirebaseModel
import com.aligmohammad.doctorappclient.data.model.firebasemodels.OfferFirebaseModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FireDummyData {

    fun sendPlaces() {
        val db = Firebase.database.reference
        db.child("Places").child("X-Rays").setValue(getXRays())
        db.child("Places").child("Labs").setValue(getLabs())
        db.child("Places").child("Pharmacy").setValue(getXPharmacies())
    }

    fun sendDevices() {
        val db = Firebase.database.reference
        db.child("DeviceTypes").setValue(getDeviceTypes())
        db.child("Devices").setValue(getDeviceList())
    }

    fun getDeviceTypes(): ArrayList<DeviceTypesFirebaseModel> {
        var deviceList = arrayListOf<DeviceTypesFirebaseModel>()
        deviceList.add(DeviceTypesFirebaseModel("Therapist", "Therapist", "Therapist.png", arrayListOf("pushdevice")))
        return deviceList
    }

    fun getDeviceList(): ArrayList<DeviceFirebaseModel>{
        val deviceList = arrayListOf<DeviceFirebaseModel>()
        deviceList.add(DeviceFirebaseModel("Push Device","+96277733340","100 JD","Amman, Jordan","عمان","ابو علندا",null,"","pushdevice","Therapist"))
        return deviceList
    }

    fun sendOffers() {
        val db = Firebase.database.reference
        db.child("Offers").setValue(getOffers())
    }

    private fun getOffers(): ArrayList<OfferFirebaseModel> {
        val offers = arrayListOf<OfferFirebaseModel>()
        offers.add(OfferFirebaseModel("Dentist offer","offer description","عمان","ابو علندا","10/10/2020","10/10/2022","10:00","04:00","image.png",true))
        return offers
    }

    fun sendMajors() {
        val doctorMajorList = listOf<MajorsFirebaseModel>(MajorsFirebaseModel("القلب و الشرايين", "Heart", "https://images.pexels.com/photos/3279197/pexels-photo-3279197.jpeg", listOf("96277733302")))
        val db = Firebase.database.reference
        db.child("Majors").setValue(doctorMajorList)
    }

    fun sendDoctors() {
        val db = Firebase.database.reference
        db.child("Doctors").setValue(getDoctorList())
    }


    private fun getLabs(): ArrayList<Place> {
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
                "Biolab","",
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

    private fun getXRays(): ArrayList<Place> {
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

    private fun getXPharmacies(): ArrayList<Place> {
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

    private fun getDoctorList(): ArrayList<Doctor> {
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