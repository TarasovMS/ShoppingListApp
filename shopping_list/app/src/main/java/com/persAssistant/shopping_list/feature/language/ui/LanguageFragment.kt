package com.persAssistant.shopping_list.feature.language.ui

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentLanguageBinding
import com.persAssistant.shopping_list.feature.language.viewmodel.LanguageViewModel
import com.persAssistant.shopping_list.feature.language.viewmodel.LanguageViewModel.Companion.CURRENT_LANGUAGE
import com.persAssistant.shopping_list.ui.activity.MainActivity
import com.persAssistant.shopping_list.util.ENGLISH_USA
import com.persAssistant.shopping_list.util.RUSSIAN
import com.persAssistant.shopping_list.util.delegate.viewBinding
import java.util.*


class LanguageFragment : AppBaseFragment(R.layout.fragment_language) {

    private var currentLanguage: String = RUSSIAN
    private val binding by viewBinding(FragmentLanguageBinding::bind)
    private val viewModel: LanguageViewModel by viewModels { viewModelFactory }

    //TODO заместо toast показывать алерт простой фрагмент диалог


    @ColorRes
    override fun statusBarColor() = R.color.black

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.fragmentLanguageToolbar
    }

    override fun onAttach(context: Context) {
        val localeToSwitchTo = Locale(currentLanguage)
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(context, localeToSwitchTo)
        super.onAttach(localeUpdatedContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    
    private fun init(){
        currentLanguage = requireActivity().intent.getStringExtra(CURRENT_LANGUAGE).toString()

        val list = ArrayList<String>()
        list.add(getString(R.string.select_language))
        list.add(getString(R.string.english))
        list.add(getString(R.string.russian))

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            list
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fragmentLanguageSpinner.adapter = adapter

        binding.fragmentLanguageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            //TODO обработать минимальную версию и обозначнеие языка в стринг c 21 по 24
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> { }
                    1 -> setLocale(ENGLISH_USA)
                    2 -> setLocale(RUSSIAN)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            val locale = Locale(localeName)

            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration

            conf.locale = locale
            res.updateConfiguration(conf, dm)

            val refresh = Intent(
                requireContext(),
                MainActivity::class.java
            )

            refresh.putExtra(CURRENT_LANGUAGE, localeName)
            startActivity(refresh)
            requireActivity().finish()

        } else {
            // toast replase on snackbar
            Toast.makeText(
                requireContext(),
                getString(R.string.language_already_selected),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
