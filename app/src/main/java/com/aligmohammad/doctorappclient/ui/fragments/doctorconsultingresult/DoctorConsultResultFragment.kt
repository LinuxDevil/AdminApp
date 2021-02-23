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

package com.aligmohammad.doctorappclient.ui.fragments.doctorconsultingresult

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.Medicine
import com.aligmohammad.doctorappclient.databinding.DoctorConsultResultFragmentBinding
import com.aligmohammad.doctorappclient.ui.adapters.MedicineListAdapter
import kotlinx.android.synthetic.main.doctor_consult_result_fragment.view.*
import navigateSafe

class DoctorConsultResultFragment : Fragment() {

    private lateinit var viewModel: DoctorConsultResultFragmentViewModel
    private lateinit var binding: DoctorConsultResultFragmentBinding

    private var medicineItems = listOf<Medicine>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.doctor_consult_result_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(DoctorConsultResultFragmentViewModel::class.java)
        binding.viewModel = viewModel

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)

        medicineItems = viewModel.getMedicine()

        binding.root.orderButton.setOnClickListener {
            val navController = Navigation.findNavController(activity!!, R.id.fragment)
            if (navController.currentDestination?.id == R.id.doctorConsultResultFragment) {
                this.navigateSafe(DoctorConsultResultFragmentDirections.medicineToOrder())
            }
        }

        initRecycler()

        return binding.root
    }

    private fun initRecycler() {
        val adapter = MedicineListAdapter(medicineItems)
        binding.root.medicineRecyclerView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}