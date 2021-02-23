/*******************************************************************************
 *
 * Copyright RectiCode(c) 2021.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and de-compilation.
 *
 * Created by Ali Mohammad
 *
 ******************************************************************************/

package com.aligmohammad.doctorappclient.data.network.responses

data class Role(
    val description: String,
    val doctor: Any,
    val id: Int,
    val name: String,
    val type: String
)