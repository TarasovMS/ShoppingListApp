package com.persAssistant.shopping_list.feature.splash.validation

import android.widget.AutoCompleteTextView
import com.persAssistant.shopping_list.base.AppBaseFragment

abstract class DaDataBindingComponent<T>(
    fragment: AppBaseFragment,
    override val hint: Int
) : StringBindingComponent(fragment, hint){
    open  lateinit var inputValue: AutoCompleteTextView
}
