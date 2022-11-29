package com.example.todolist.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.todolist.R
import com.example.todolist.ui.splash.SplashActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    var testPref: SwitchPreference? = null


    private val viewModel: SettingsViewModel by viewModels()
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        testPref = findPreference("key")
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)

        findPreference<Preference>("developer")?.onPreferenceClickListener =
            Preference.OnPreferenceClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/ynsyldz2022")
                    )
                )
                true
            }
        findPreference<Preference>("source_code")?.onPreferenceClickListener =
            Preference.OnPreferenceClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/yunusyildiz096")
                    )
                )
                true
            }

        findPreference<Preference>("delete_all")?.onPreferenceClickListener =
            Preference.OnPreferenceClickListener {
                //findNavController().navigate(SettingsFragmentDirections.actionSettingsToHome())
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle(getString(R.string.delete_data))
                builder.setMessage(getString(R.string.are_you_sure_delete_all_data))
                builder.setPositiveButton(getString(R.string.yes)) { b, _ ->

                    viewModel.deleteAll()
                    startActivity(Intent(requireActivity(), SplashActivity::class.java))
                    requireActivity().finish()

                }
                builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
                builder.show()
                true
            }


    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val darkMode = getString(R.string.dark_mode)

        key.let {
            if (it == darkMode) sharedPreferences.let { pref ->
                val darkModeValues = resources.getStringArray(R.array.entry_values)
                when (pref?.getString(darkMode, darkModeValues[0])) {
                    darkModeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    darkModeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    darkModeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
    }
}