package com.aligmohammad.doctorappclient.ui.fragments.main_profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.databinding.MainProfileFragmentBinding
import kotlinx.android.synthetic.main.main_profile_fragment.view.*

class MainProfileFragment : Fragment() {

    private lateinit var viewModel: MainProfileViewModel
    private lateinit var binding: MainProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.main_profile_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainProfileViewModel::class.java)
        val appBarConfiguration =
            AppBarConfiguration(
                findNavController().graph,
                null
            )

        val userInfo = UserSingleton.getCurrentUser()

        binding.doctorName.setText("Name: " + userInfo.name)
        binding.doctorRelatedInsurances.setText("Insurance: " + userInfo.insuranceCompany)
        binding.doctorMajor.setText("Insurance No. : " + userInfo.insuranceNumber)
        binding.doctorYearsOfExperience.setText("Mobile: " + userInfo.username)
        binding.doctorLocation.setText("Location: " + userInfo.city + ", " + userInfo.district)

        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        setHasOptionsMenu(true)
        return binding.root
    }


}