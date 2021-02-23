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

package com.aligmohammad.doctorappclient.ui.dialogs.governmentchoice

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation

class GovernmentHospitalChoiceViewModel: ViewModel() {

    fun onConfirmClicked(view: View) {
        Navigation.findNavController(view).navigate(GovernmentHospitalChoiceDirections.govChoiceToGovHospitals())
    }

}