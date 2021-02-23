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

package com.aligmohammad.doctorappclient.ui.dialogs.datedialogfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.DateAndTimeBottomSheetFragmentBinding
import com.aligmohammad.doctorappclient.ui.dialogs.OnDialogInteract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.date_and_time_bottom_sheet_fragment.view.*
import navigateSafe
import java.util.*

class DateAndTimeBottomSheetFragment : BottomSheetDialogFragment(),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, OnDialogInteract {

    private lateinit var viewModel: DateAndTimeBottomSheetViewModel
    private lateinit var binding: DateAndTimeBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.date_and_time_bottom_sheet_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(DateAndTimeBottomSheetViewModel::class.java)
        binding.viewModel = viewModel
        binding.listener = this
        binding.root.dateEditText.setOnFocusChangeListener { view, b ->
            if (b) {
                val calendar = Calendar.getInstance()
                val datePicker = DatePickerDialog.newInstance(
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show(fragmentManager!!, "DatePicker")
            }
        }
        binding.root.timeEditText.setOnFocusChangeListener { view, isFocused ->
            if (isFocused) {
                val calendar = Calendar.getInstance()
                val timePicker = TimePickerDialog.newInstance(
                    this,
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND),
                    false
                )
                timePicker.show(fragmentManager!!, "TimePicker")
            }
        }
        return binding.root
    }

    override fun onBackButtonClicked(view: View) {
        this.dismiss()
    }

    override fun onButtonClicked(view: View) {
        Toast.makeText(requireContext(), "Reserved!", Toast.LENGTH_LONG).show()
        val navController = Navigation.findNavController(activity!!, R.id.fragment)
        if (navController.currentDestination?.id == R.id.dateAndTimeBottomSheetFragment)
            this.navigateSafe(DateAndTimeBottomSheetFragmentDirections.backToHome())
        else {
            Log.v("MainActivity", navController.currentDestination?.label.toString())
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        view!!.dismiss()
        binding.root.dateEditText.setText("${year}/${monthOfYear}/$dayOfMonth")
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        var hourCalculation = hourOfDay
        var timing = "AM"
        if (hourCalculation >= 13) {
            hourCalculation -= 12
            timing = "PM"
        }
        binding.root.timeEditText.setText("${hourCalculation}:${minute} ${timing}")
    }


}