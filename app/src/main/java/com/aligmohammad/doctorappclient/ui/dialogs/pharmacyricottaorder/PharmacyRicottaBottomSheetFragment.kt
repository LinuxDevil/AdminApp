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
 * Copyright RectiCode(c) 2020.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and de-compilation.
 *
 * Created by Ali Mohammad
 ******************************************************************************/

package com.aligmohammad.doctorappclient.ui.dialogs.pharmacyricottaorder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.PharmacyRicottaBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PharmacyRicottaBottomSheetFragment : BottomSheetDialogFragment(), OnDialogInteract {

    private lateinit var viewModel: PharmacyRicottaBottomSheetViewModel
    private lateinit var binding: PharmacyRicottaBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pharmacy_ricotta_bottom_sheet_fragment, container, false)

        viewModel = ViewModelProvider(this).get(PharmacyRicottaBottomSheetViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this

        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        dismiss()
    }

}