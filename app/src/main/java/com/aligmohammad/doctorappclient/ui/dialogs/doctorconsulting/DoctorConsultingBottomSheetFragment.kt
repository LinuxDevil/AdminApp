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

package com.aligmohammad.doctorappclient.ui.dialogs.doctorconsulting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.DoctorConsultingBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import navigateSafe

class DoctorConsultingBottomSheetFragment : BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: DoctorConsultingBottomSheetViewModel
    private lateinit var binding: DoctorConsultingBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.doctor_consulting_bottom_sheet_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(DoctorConsultingBottomSheetViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this
        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

    override fun onButtonClicked(view: View) {
        val navController = Navigation.findNavController(activity!!, R.id.fragment)
        when(view.id) {
            R.id.consultGeneralDoctor -> {
               if (navController.currentDestination?.id == R.id.doctorConsult) {
                   navigateSafe(DoctorConsultingBottomSheetFragmentDirections.doctorConsultToCityDistrict())
               }
            }
            R.id.requestADoctor -> {
                if (navController.currentDestination?.id == R.id.doctorConsult) {
                    navigateSafe(DoctorConsultingBottomSheetFragmentDirections.consultToRequestDoctor().setType("Doctor"))
                }
            }
            R.id.requestANurse -> {
                if (navController.currentDestination?.id == R.id.doctorConsult) {
                    navigateSafe(DoctorConsultingBottomSheetFragmentDirections.consultToRequestDoctor().setType("Nurse"))
                }
            }
        }
    }

}