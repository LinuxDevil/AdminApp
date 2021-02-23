package com.aligmohammad.doctorappclient.ui.fragments.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aligmohammad.doctorappclient.R
import com.aligmohammad.doctorappclient.databinding.FragmentSettingsBinding
import com.aligmohammad.doctorappclient.helpers.PreferencesStore
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var userPreferencesStore: PreferencesStore

    private fun saveLanguage(lang: String) = GlobalScope.async {
        userPreferencesStore.saveLanguage(lang)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        userPreferencesStore = PreferencesStore(requireContext())
        initUI()

        binding.languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> {
                            binding.languageSpinner.setSelection(0)
                            saveLanguage("en")
                            val locale = Locale("en")
                            Locale.setDefault(locale)
                            val config: Configuration = requireContext().resources.configuration
                            config.locale = locale
                            requireContext().resources.updateConfiguration(
                                config,
                                requireContext().resources.displayMetrics
                            )
                        }
                        1 -> {
                            binding.languageSpinner.setSelection(1)
                            val locale = Locale("ar")
                            Locale.setDefault(locale)
                            saveLanguage("ar")
                            val config: Configuration = requireContext().resources.configuration
                            config.locale = locale
                            requireContext().resources.updateConfiguration(
                                config,
                                requireContext().resources.displayMetrics
                            )
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

        userPreferencesStore.lanuage.asLiveData()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it != null) {
                    when (it) {
                        "en" -> {
                            binding.languageSpinner.setSelection(0)
                            val locale = Locale("en")
                            Locale.setDefault(locale)
                            val config: Configuration = requireContext().resources.configuration
                            config.locale = locale
                            requireContext().resources.updateConfiguration(
                                config,
                                requireContext().resources.displayMetrics
                            )
                        }
                        "ar" -> {
                            binding.languageSpinner.setSelection(1)
                            val locale = Locale("ar")
                            Locale.setDefault(locale)
                            val config: Configuration = requireContext().resources.configuration
                            config.locale = locale
                            requireContext().resources.updateConfiguration(
                                config,
                                requireContext().resources.displayMetrics
                            )
                        }
                    }
                } else {
                    val locale = Locale("en")
                    Locale.setDefault(locale)
                    saveLanguage("en")
                    val config: Configuration = requireContext().resources.configuration
                    config.locale = locale
                    requireContext().resources.updateConfiguration(
                        config,
                        requireContext().resources.displayMetrics
                    )
                }
            })

        return binding.root
    }

    private fun initUI() {
        val appBarConfiguration =
            AppBarConfiguration(
                setOf(R.id.homeFragment, R.id.governmentHospitalsFragment),
                binding.root.drawerLayout
            )
        binding.root.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        setHasOptionsMenu(true)
    }

}