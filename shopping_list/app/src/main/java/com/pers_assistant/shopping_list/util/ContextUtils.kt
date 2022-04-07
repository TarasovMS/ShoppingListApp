package com.pers_assistant.shopping_list.util

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.*

class ContextUtils(base: Context) : ContextWrapper(base) {

    // TODO
    // https://medium.com/swlh/android-app-specific-language-change-programmatically-using-kotlin-d650a5392220

    // get chosen language from shread preference
//    val localeToSwitchTo = PreferenceManager(newBase).getAppLanguage()
//    val localeUpdatedContext: ContextWrapper = ContextUtils.updateLocale(newBase, localeToSwitchTo)


    companion object {

        fun updateLocale(context: Context, localeToSwitchTo: Locale): ContextWrapper {
//            var context = c
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            }
            else configuration.locale = localeToSwitchTo

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                context.createConfigurationContext(configuration)
            else
                resources.updateConfiguration(configuration, resources.displayMetrics)

            return ContextUtils(context)
        }
    }
}