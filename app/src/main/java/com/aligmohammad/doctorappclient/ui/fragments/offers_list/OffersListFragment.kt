package com.aligmohammad.doctorappclient.ui.fragments.offers_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.RecyclerMenuItem
import com.aligmohammad.doctorappclient.data.model.firebasemodels.OfferFirebaseModel
import com.aligmohammad.doctorappclient.databinding.FragmentOffersListBinding
import com.aligmohammad.doctorappclient.ui.adapters.OfferRecyclerViewAdapter
import com.aligmohammad.doctorappclient.ui.adapters.OnMenuItemClick
import com.aligmohammad.doctorappclient.ui.fragments.homefragment.HomeFragmentDirections
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.doctor_major_fragment_item_list.view.*

class OffersListFragment : Fragment(), OnMenuItemClick {

    private val arrayList = arrayListOf<RecyclerMenuItem>()
    private val arrayListOfMajors = arrayListOf<OfferFirebaseModel>()

    private lateinit var binding: FragmentOffersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_offers_list,
            container,
            false
        )

        getFirebaseData()
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        getData()
        initRecycler()
        return binding.root
    }

    private fun initRecycler() {
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = OfferRecyclerViewAdapter(arrayListOfMajors, this@OffersListFragment)
        }
    }

    private fun getFirebaseData() {
        val db = Firebase.database.reference
        db.child("Offers").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { snap ->
                    arrayListOfMajors.clear()
                    snap.getValue(OfferFirebaseModel::class.java)?.let {
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
        // pass list here
    }

    override fun onClickMenu(v: View?, menuItem: OfferFirebaseModel) {
        super.onClickMenu(v, menuItem)
//        Navigation.findNavController(v!!).navigate(
//            DoctorMajorFragmentDirections.majorToDoctorList("")
//                .setDoctorArray(menuItem.doctorList!!.toTypedArray())
//        )
    }


}