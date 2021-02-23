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

package com.aligmohammad.doctorappclient.ui.fragments.authframent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.FragmentPhoneCodeBinding
import com.aligmohammad.doctorappclient.util.loadGif
import kotlinx.android.synthetic.main.fragment_phone_code.view.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance


class PhoneCodeFragment : Fragment(), DIAware {

    override val di: DI by lazy { (context?.applicationContext as DIAware).di }

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPhoneCodeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone_code, container, false)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        binding.root.success.loadGif(R.drawable.sucess, "Loading")

        return binding.root
    }

}