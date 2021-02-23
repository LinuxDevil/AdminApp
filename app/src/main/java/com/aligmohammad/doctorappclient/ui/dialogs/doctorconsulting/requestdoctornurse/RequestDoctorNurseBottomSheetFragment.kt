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

package com.aligmohammad.doctorappclient.ui.dialogs.doctorconsulting.requestdoctornurse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.RequestDoctorNurseBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.request_doctor_nurse_bottom_sheet_fragment.view.*

class RequestDoctorNurseBottomSheetFragment : BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: RequestDoctorNurseBottomSheetViewModel
    private lateinit var binding: RequestDoctorNurseBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.request_doctor_nurse_bottom_sheet_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(RequestDoctorNurseBottomSheetViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this

        binding.root.title.text = navArgs<RequestDoctorNurseBottomSheetFragmentArgs>().value.type

        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }


}