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

package com.aligmohammad.doctorappclient.ui.dialogs.citydistrictcompanydialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.CityDistrictCompanyFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.aligmohammad.doctorappclient.ui.fragments.homefragment.HomeFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.city_district_company_fragment.view.*
import navigateSafe

class CityDistrictCompanyFragment : BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: CityDistrictCompanyViewModel
    private lateinit var binding: CityDistrictCompanyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.city_district_company_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(CityDistrictCompanyViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this
        binding.root.confirm_button.setOnClickListener {
            onConfirmClicked(it)
        }


        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        dismiss()
    }

    private fun onConfirmClicked(view: View) {
        val navController = Navigation.findNavController(activity!!, R.id.fragment)
        if (navArgs<CityDistrictCompanyFragmentArgs>().value.type != "consult") {
            if (navController.currentDestination?.id == R.id.cityDistrictCompanyFragment) {
                this.navigateSafe(
                    CityDistrictCompanyFragmentDirections.labsToList("")
                        .setType(navArgs<CityDistrictCompanyFragmentArgs>().value.type)
                )
            }
        } else {
            if (navController.currentDestination?.id == R.id.cityDistrictCompanyFragment) {
                this.navigateSafe(CityDistrictCompanyFragmentDirections.cityToSymptoms())
            }
        }
    }

}