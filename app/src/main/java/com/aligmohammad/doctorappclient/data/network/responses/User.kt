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

data class User(
    val appointments: List<Any>,
    val blocked: Any,
    val confirmed: Boolean,
    val created_at: String,
    val email: String,
    val id: Int,
    val phone: String,
    val provider: String,
    val role: Role,
    val updated_at: String,
    val username: String
)