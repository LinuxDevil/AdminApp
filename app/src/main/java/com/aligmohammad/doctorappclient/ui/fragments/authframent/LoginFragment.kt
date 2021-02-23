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

package com.aligmohammad.doctorappclient.ui.fragments.authframent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.aligmohammad.doctorappclient.data.network.UserSingleton
import com.aligmohammad.doctorappclient.data.network.responses.FirebaseUserResponse
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.data.model.Doctor
import com.aligmohammad.doctorappclient.databinding.FragmentLoginBinding
import com.aligmohammad.doctorappclient.helpers.PreferencesStore
import com.aligmohammad.doctorappclient.util.hideKeyboard
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import navigateSafe

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()

    private lateinit var databaseReference: DatabaseReference

    private var registerationId = ""
    var phoneNumberString: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkToken()
        checkFirebaseUser()

        binding = FragmentLoginBinding.bind(view)

        databaseReference = Firebase.database.reference

//        binding.phoneEditText.setText("+962777733340")

        binding.registerButton.setOnClickListener {

            hideKeyboard()

            if (binding.registerButton.tag == "confirm") {
                confirmCode()
            } else {
                if (binding.phoneEditText.text.toString()
                        .matches("^(00962|962|\\+962)(77|7|9|8)([0-9]{7})$".toRegex())
                ) {
                    registerUser()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please enter a valid mobile number and add (+962)",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun checkToken() {
        val userPreferencesStore = PreferencesStore(requireContext())
        userPreferencesStore.accessToken.asLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                navigateSafe(LoginFragmentDirections.loginToHome())
            }
        })
    }

    private fun checkFirebaseUser() {
        val userPref = PreferencesStore(requireContext())
        userPref.firebaseUser.asLiveData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                navigateSafe(LoginFragmentDirections.loginToHome())
            }
        })
    }

    private fun goToHomePage() {
        navigateSafe(LoginFragmentDirections.loginToHome())
    }

    private fun confirmCode() {
        viewModel.verifyCode(binding.phoneEditText.text.toString(), registerationId,
            { registerDone() }, { registerDone() }, binding.phoneEditText
        )
    }

    private fun registerDone() {
        registerUserInFirebase()
    }

    private fun registerUserInFirebase() {
        databaseReference.child("Doctors")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.v(
                        "addListenerForEvent",
                        phoneNumberString.substring(1, phoneNumberString.length)
                    )
                    viewModel.saveFirebaseUser(phoneNumberString)
                    if (!snapshot.hasChild(
                            phoneNumberString.substring(
                                1,
                                phoneNumberString.length
                            )
                        )
                    ) {
                        putUserInFirebase()
                    } else {
                        getUser();
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })

    }

    private fun getUser() {
        Log.v("Getting User", phoneNumberString)
        databaseReference.child("Doctors")
            .child(phoneNumberString.substring(1, phoneNumberString.length))
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        UserSingleton.setUser(snapshot.getValue(FirebaseUserResponse::class.java)!!)
                        Log.v("snapshot.exists", "True  it exists")
                        goToHomePage()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    private fun putUserInFirebase() {
        val user = Doctor(
            phoneNumberString.substring(1, phoneNumberString.length),
            phoneNumberString + "@myclinic.com",
            phoneNumberString,
            phoneNumberString,
            "Default Name",
            "Amman",
            "Default District",
            "عمان",
            "ابو علندا",
            "NatHealth",
            null,
            null,
            null,
            null,
            "5",
            null,
            null,
            null,
            null,
            null,
            null,
            0,
            "امراض قلب",
            0,
            null,
            null,
            "عمان",
            32.1f,
            32.1f,
        )

        databaseReference.child("Doctors")
            .child(phoneNumberString.substring(1, phoneNumberString.length))
            .setValue(user).addOnCompleteListener {
                goToHomePage()
                Log.v("setValue", "true")
            }.addOnFailureListener {
                binding.phoneEditText.snackbar(it.localizedMessage)
            }
    }

    private fun registerUser() {
        val phoneNumber = binding.phoneEditText.text.toString()
        this.phoneNumberString = phoneNumber
        viewModel.verifyPhoneNumber(
            phoneNumber,
            requireActivity(),
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    viewModel.register(phoneNumber, phoneNumber, phoneNumber)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    binding.registerButton.snackbar(p0.localizedMessage!!)
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    binding.phoneTextView.text = "Code"
                    binding.phoneEditText.setText("")
                    binding.phoneEditText.setHint("SMS Code")
//                    binding.phoneEditText.setText("123456")
                    binding.registerButton.text = "Confirm"
                    binding.registerButton.tag = "confirm"
                    registerationId = verificationId
                }
            })
    }
}