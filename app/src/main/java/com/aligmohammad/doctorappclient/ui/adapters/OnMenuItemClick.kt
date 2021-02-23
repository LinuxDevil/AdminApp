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

package com.aligmohammad.doctorappclient.ui.adapters

import android.util.Log
import android.view.View
import com.aligmohammad.doctorappclient.data.model.firebasemodels.DeviceTypesFirebaseModel
import com.aligmohammad.doctorappclient.data.model.firebasemodels.MajorsFirebaseModel
import com.aligmohammad.doctorappclient.data.model.firebasemodels.OfferFirebaseModel

interface OnMenuItemClick {

    fun onClick(v: View)

    fun onClickMenu(v: View?, menuItem: MajorsFirebaseModel) {
        Log.v("OnMenuItemClick", "Clicked on ${menuItem.name_en}")
    }

    fun onClickMenu(v: View?, menuItem: OfferFirebaseModel) {
        Log.v("OnMenuItemClick", "Clicked on ${menuItem.offerName}")
    }

    fun onClickMenu(v: View?, menuItem: DeviceTypesFirebaseModel) {
        Log.v("OnMenuItemClick", "Clicked on ${menuItem.name_ar}")
    }

}