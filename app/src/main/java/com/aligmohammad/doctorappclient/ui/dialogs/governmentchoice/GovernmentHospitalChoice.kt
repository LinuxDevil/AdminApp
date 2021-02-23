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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.BottomSheetGovernmentHospitalChooseBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_government_hospital_choose.view.*

class GovernmentHospitalChoice: BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: GovernmentHospitalChoiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GovernmentHospitalChoiceViewModel::class.java)
        val binding: BottomSheetGovernmentHospitalChooseBinding = DataBindingUtil.inflate(layoutInflater, R.layout.bottom_sheet_government_hospital_choose, container, false)
        binding.listener = this
        binding.viewModel = viewModel
        val title = arguments?.getString("type")
        binding.root.title.text = title
        binding.confirmButton.setOnClickListener {
//            this.dismiss()
            if (title === "Government Hospitals") {
                Navigation.findNavController(activity!!, R.id.fragment).navigate(
                    GovernmentHospitalChoiceDirections.govChoiceToGovHospitals()
                )
            } else {
                Navigation.findNavController(activity!!, R.id.fragment).navigate(
                    GovernmentHospitalChoiceDirections.govChoiceToPrivateHospitals()
                )
            }
        }
        return binding.root
    }

    fun onConfirmClicked(view: View) {
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

}