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
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentLanguageBinding
import com.persAssistant.shopping_list.feature.language.viewmodel.LanguageViewModel
import com.persAssistant.shopping_list.ui.activity.MainActivity
import com.persAssistant.shopping_list.util.ContextUtils
import com.persAssistant.shopping_list.util.ENGLISH_USA
import com.persAssistant.shopping_list.util.RUSSIAN
import com.persAssistant.shopping_list.util.viewBinding
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class LanguageFragment @Inject constructor() : AppBaseFragment(R.layout.fragment_language) {

    // TODO переделать переменные

    private lateinit var locale: Locale
    private var currentLanguage = RUSSIAN
    private var currentLang: String? = null
    private val binding by viewBinding(FragmentLanguageBinding::bind)
    private val viewModel: LanguageViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentLanguage = requireActivity().intent.getStringExtra(currentLang).toString()

        val list = ArrayList<String>()
        list.add(resources.getString(R.string.select_language))
        list.add(resources.getString(R.string.english))
        list.add(resources.getString(R.string.russian))

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            list
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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
            locale = Locale(localeName)

            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration

            conf.locale = locale
            res.updateConfiguration(conf, dm)

            val refresh = Intent(
                requireContext(),
                MainActivity::class.java
            )

            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)

        } else {

            Toast.makeText(
                requireContext(), "Language, , already, , selected)!", Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun onAttach(context: Context) {
        val localeToSwitchTo = Locale(currentLanguage)
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(context, localeToSwitchTo)
        super.onAttach(localeUpdatedContext)
    }
}