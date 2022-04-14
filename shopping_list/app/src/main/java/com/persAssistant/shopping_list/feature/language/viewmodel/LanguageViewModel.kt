package com.persAssistant.shopping_list.feature.language.viewmodel

import com.persAssistant.shopping_list.base.AppBaseViewModel
import javax.inject.Inject

class LanguageViewModel @Inject constructor() : AppBaseViewModel() {

    companion object{
        const val CURRENT_LANGUAGE = "currentLanguage"
    }

}
