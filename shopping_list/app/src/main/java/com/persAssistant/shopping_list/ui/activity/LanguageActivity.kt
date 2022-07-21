package com.persAssistant.shopping_list.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.persAssistant.shopping_list.R
import java.util.*
import kotlin.collections.ArrayList
import android.content.ContextWrapper
import com.persAssistant.shopping_list.databinding.ActivityLanguageBinding
import com.persAssistant.shopping_list.util.ContextUtils
import com.persAssistant.shopping_list.util.ENGLISH_USA
import com.persAssistant.shopping_list.util.RUSSIAN


class LanguageActivity: AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var locale: Locale
    private var currentLanguage = RUSSIAN
    private var currentLang: String? = null
    private val binding: ActivityLanguageBinding by lazy { ActivityLanguageBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        currentLanguage = intent.getStringExtra(currentLang).toString()
        spinner = findViewById(R.id.spinner)

        val list = ArrayList<String>()
        list.add(resources.getString(R.string.select_language))
        list.add(resources.getString(R.string.english))
        list.add(resources.getString(R.string.russian))

        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

            //TODO обработать минимальную версию и обозначнеие языка в стринг

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
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
                this,
                MainActivity::class.java
            )
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)
        } else {
            Toast.makeText(
                this@LanguageActivity, "Language, , already, , selected)!", Toast.LENGTH_SHORT).show();
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val localeToSwitchTo = Locale(currentLanguage)
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(newBase, localeToSwitchTo)
        super.attachBaseContext(localeUpdatedContext)
    }
}
