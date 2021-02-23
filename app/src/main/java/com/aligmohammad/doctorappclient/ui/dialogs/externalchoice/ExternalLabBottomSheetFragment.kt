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

package com.aligmohammad.doctorappclient.ui.dialogs.externalchoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.ExternalLabBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.external_lab_bottom_sheet_fragment.view.*
import navigateSafe

class ExternalLabBottomSheetFragment : BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: ExternalLabBottomSheetViewModel
    private lateinit var binding: ExternalLabBottomSheetFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.external_lab_bottom_sheet_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(ExternalLabBottomSheetViewModel::class.java)
        binding.listener = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

    override fun onButtonClicked(view: View) {
        val navController = Navigation.findNavController(activity!!, R.id.fragment)
        if (view.id == R.id.external || view.id == R.id.medical) {
            binding.root.optionsConstraint.visibility = View.VISIBLE
            binding.root.topLinear.visibility = View.GONE
        } else {
            if (navController.currentDestination?.id == R.id.externalLabBottomSheetFragment2)
                this.navigateSafe(ExternalLabBottomSheetFragmentDirections.externalToDate())
            else {
                Log.v("MainActivity", navController.currentDestination?.label.toString())
            }
        }
    }


}