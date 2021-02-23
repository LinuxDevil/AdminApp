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

package com.aligmohammad.doctorappclient.ui.fragments.doctormajor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.RecyclerMenuItem
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceTypesFirebaseModel
import com.aligmohammad.doctorappclient.databinding.DoctorMajorFragmentItemListBinding
import com.aligmohammad.doctorappclient.ui.adapters.MyDoctorMajorRecyclerViewAdapter
import com.aligmohammad.doctorappclient.ui.adapters.OnMenuItemClick
import com.aligmohammad.doctorappclient.ui.fragments.homefragment.HomeFragmentDirections
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.doctor_major_fragment_item_list.view.*

class DoctorMajorFragment : Fragment(), OnMenuItemClick {

    private val arrayList = arrayListOf<RecyclerMenuItem>()

    private var columnCount = 3
    private lateinit var binding: DoctorMajorFragmentItemListBinding

    private val arrayListOfMajors = arrayListOf<DeviceTypesFirebaseModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.doctor_major_fragment_item_list,
            container,
            false
        )
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        getFirebaseData()
        initRecycler()

        return binding.root
    }

    /**
     * Init recycler view with column count
     */
    private fun initRecycler() {
        with(binding.list) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyDoctorMajorRecyclerViewAdapter(arrayListOfMajors, this@DoctorMajorFragment)
        }
    }

    private fun getFirebaseData() {
        val db = Firebase.database.reference
        db.child("DeviceTypes").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { snap ->
                    arrayListOfMajors.clear()
                    snap.getValue(DeviceTypesFirebaseModel::class.java)?.let {
                        arrayListOfMajors.add(
                            it
                        )
                        initRecycler()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


    private fun getData() {
        arrayList.clear()
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "New Name",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.actionHomeFragmentToBottomSheetGovernment()
            )
        )
    }

    override fun onClick(v: View) {
        Navigation.findNavController(v).navigate(DoctorMajorFragmentDirections.majorToDoctorList(""))
    }


    override fun onClickMenu(v: View?, menuItem: DeviceTypesFirebaseModel) {
        super.onClickMenu(v, menuItem)
        Navigation.findNavController(v!!).navigate(DoctorMajorFragmentDirections.majorToDoctorList("").setDoctorArray(menuItem.deviceList!!.toTypedArray()))
    }

}