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

package com.aligmohammad.doctorappclient.ui.dialogs

import android.view.View

interface OnDialogInteract {

    /**
     * On click
     * @param view
     */
    fun onBackButtonClicked(view: View)

    /**
     * On button clicked
     *
     * @param view
     */
    fun onButtonClicked(view: View) {

    }

}