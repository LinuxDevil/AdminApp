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

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aligmohammad.doctorappclient.BaseViewModel
import com.aligmohammad.doctorappclient.data.model.RecyclerMenuItem
import com.aligmohammad.doctorappclient.data.network.Resource
import com.aligmohammad.doctorappclient.data.network.repository.HomeRepository
import com.aligmohammad.doctorappclient.data.network.responses.MenuItemResponse
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val repository: HomeRepository) :
    BaseViewModel(repository) {

    private val _menuItems: MutableLiveData<Resource<MenuItemResponse>> = MutableLiveData()
    val menuItemReponse: LiveData<Resource<MenuItemResponse>>
        get() = _menuItems

    val menuItems by lazy { MutableLiveData<ArrayList<RecyclerMenuItem>>() }
    val imageUrl = "https://images.pexels.com/photos/1591060/pexels-photo-1591060.jpeg"

    fun setMenuItems(list: ArrayList<RecyclerMenuItem>) {
        menuItems.value = list
    }

    fun getMenuItems() = viewModelScope.launch {
        _menuItems.value = Resource.Loading
        _menuItems.value = repository.getMenuItem()
    }


    fun dummyList(): ArrayList<RecyclerMenuItem> {
        val arrayList = arrayListOf<RecyclerMenuItem>()
        arrayList.add(
            RecyclerMenuItem(
                "My Appointments",
                "https://images.pexels.com/photos/4047184/pexels-photo-4047184.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "Reserve Operations",
                "https://images.pexels.com/photos/4386467/pexels-photo-4386467.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "Reserve X-Ray Center",
                "https://images.pexels.com/photos/3957986/pexels-photo-3957986.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "Reserve Lab/ Naturalist",
                "https://images.pexels.com/photos/3825586/pexels-photo-3825586.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "Order Medical Devices",
                "https://images.pexels.com/photos/4225923/pexels-photo-4225923.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "Order Medicine Offers",
                "https://images.pexels.com/photos/356040/pexels-photo-356040.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        arrayList.add(
            RecyclerMenuItem(
                "Offers",
                "https://images.pexels.com/photos/1591060/pexels-photo-1591060.jpeg",
                HomeFragmentDirections.homeToXRay()
            )
        )
        return arrayList
    }

}