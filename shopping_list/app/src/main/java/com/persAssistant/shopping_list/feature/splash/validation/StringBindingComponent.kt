package com.persAssistant.shopping_list.feature.splash.validation

import android.widget.AutoCompleteTextView
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.TextInputFragmentBinding

open class StringBindingComponent(
    override val fragment: AppBaseFragment,
    @StringRes open val hint: Int,
    open val endIconMode: Int = TextInputLayout.END_ICON_CLEAR_TEXT
) : BindingComponent<String>(fragment), ImeActionClicker {

    override fun initialize() {
        binding = TextInputFragmentBinding.inflate(
            fragment.layoutInflater
        ).apply {
            inputLayout.hint = fragment.getString(hint)
            inputLayout.endIconMode = endIconMode
        }
    }


    override fun getFieldValue(): String {
        return (binding as TextInputFragmentBinding).inputValue.text.toString()
    }

    override fun setFieldValue(value: String?) {
        (binding as TextInputFragmentBinding).inputValue.setText(value)
    }

    override fun getFocusView(): AutoCompleteTextView {
        return (binding as TextInputFragmentBinding).inputValue
    }

    override fun fieldType(): String {
//        return fragment.getParameters()?.fieldType ?: EMPTY_STRING
        return ""
    }
}

//fun String.parseDoubleOrNull(): Double? {
//    return try {
//        this.toDouble()
//    } catch (e: Exception) {
//        null
//    }
//}
