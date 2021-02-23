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

/*******************************************************************************
 * Copyright Recticode(c) 2020.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 *
 * Created by Ali Mohammad
 ******************************************************************************/

package com.aligmohammad.doctorappclient.ui.fragments.doctorconsultingresult

import androidx.lifecycle.ViewModel
import com.aligmohammad.doctorappclient.data.model.Medicine

class DoctorConsultResultFragmentViewModel : ViewModel() {
    fun getMedicine(): List<Medicine> {

        val medicineList = arrayListOf<Medicine>()
        medicineList.add(Medicine("Panadol Extra", 5.30, 5, "", "", " JD"))
        medicineList.add(Medicine("Vitamin C", 11.50, 2, "", "", " JD"))
        medicineList.add(Medicine("Eye Drops", 1.30, 1, "", "", " JD"))

        return medicineList
    }
}