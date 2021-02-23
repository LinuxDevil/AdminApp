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

package com.aligmohammad.doctorappclient.ui.fragments.homefragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.network.FireDummyData
import com.aligmohammad.doctorappclient.data.network.responses.MenuItemResponseItem
import com.aligmohammad.doctorappclient.databinding.HomeFragmentBinding
import com.aligmohammad.doctorappclient.helpers.PreferencesStore
import com.aligmohammad.doctorappclient.ui.adapters.HomeRecyclerAdapter
import com.aligmohammad.doctorappclient.ui.adapters.OnMenuItemClick
import com.aligmohammad.doctorappclient.ui.fragments.authframent.AuthViewModel
import com.aligmohammad.doctorappclient.util.getUser
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.view.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment), OnMenuItemClick {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: HomeFragmentBinding

    private val authViewModel by viewModels<AuthViewModel>()
    private var menuItems = ArrayList<MenuItemResponseItem>()
    private lateinit var userPreferencesStore: PreferencesStore

    private lateinit var adapter: HomeRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferencesStore = PreferencesStore(requireContext())

        userPreferencesStore.firebaseUser.asLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                getUser(it)
//                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            } else {
                authViewModel.logoutUser()
                redirectToLoginPage()
            }
        })
        binding = HomeFragmentBinding.bind(view)

//        val firebaseDummy = FireDummyData()
//        firebaseDummy.sendDevices()

        val appBarConfiguration =
            AppBarConfiguration(
                setOf(R.id.homeFragment, R.id.governmentHospitalsFragment),
                binding.root.drawerLayout
            )
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        setHasOptionsMenu(true)
        binding.root.navigationView.setupWithNavController(findNavController());
        binding.viewModel = viewModel

        initializeRecycler()
        initializeUI()
        initAd()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initializeUI() {
        val appBarConfiguration =
            AppBarConfiguration(
                setOf(R.id.homeFragment, R.id.governmentHospitalsFragment),
                binding.root.drawerLayout
            )
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        setHasOptionsMenu(true)
        binding.root.navigationView.setupWithNavController(findNavController())
        binding.root.navigationView.setNavigationItemSelectedListener {
            val id = it.itemId
            when (id) {
                R.id.logout -> {
                    authViewModel.logoutUser()
                    redirectToLoginPage()
                }

                R.id.mainProfileFragment -> {
                    findNavController().navigate(HomeFragmentDirections.homeToMainProfile())
                }

                R.id.settingsFragment -> {
                    findNavController().navigate(HomeFragmentDirections.homeToSettings())
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }


    private fun initAd() {

        MobileAds.initialize(requireContext()) {}
        val adRequest = AdRequest.Builder().build()
//        binding.adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        binding.adBanner.loadAd(adRequest)

    }

    private fun initializeRecycler() {
        val adapter = HomeRecyclerAdapter(viewModel.dummyList(), this)
        binding.homeListRecycler.adapter = adapter
    }

    override fun onClick(v: View) {
        val navController = Navigation.findNavController(v)
        when (v.findViewById<TextView>(R.id.itemName).text.toString()) {
            "My Appointments" -> {
                navController.navigate(HomeFragmentDirections.homeToAppointment())
            }
            "Reserve Operations" -> {
                navController.navigate(
                    HomeFragmentDirections.homeToOperation()
                )
            }
            "Reserve X-Ray Center" -> {
                navController.navigate(
                    HomeFragmentDirections.homeToXRayBottom()
                )
            }
            "Reserve Lab/ Naturalist" -> {
                navController.navigate(
                    HomeFragmentDirections.homeToNaturalistBottom()
                )
            }
            "Order Medical Devices" -> {
                navController.navigate(HomeFragmentDirections.homeToDoctorList(""))
            }

            "Order Medicine Offers" -> {
                navController.navigate(
                    HomeFragmentDirections.homeToDoctorMajor()
                )
            }
            "Offers" -> {
                navController.navigate(
                    HomeFragmentDirections.homeToOffers()
                )
            }
            else -> {
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
                        .setType(v.findViewById<TextView>(R.id.itemName).text.toString())
                )
            }
        }
    }

    private fun redirectToLoginPage() {
        findNavController().navigateUp()
    }


}