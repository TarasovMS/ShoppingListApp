package com.persAssistant.shopping_list.feature.validation

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.persAssistant.shopping_list.base.AppBaseFragment


interface IBindingComponent<T> : Component, Binding {
    val fragment: AppBaseFragment
    override fun initialize()
    fun getFieldValue(): T
    fun setFieldValue(value: T?)
}

abstract class BindingComponent<T>(
    override val fragment: AppBaseFragment
) : IBindingComponent<T> {

    override lateinit var binding: ViewBinding

    fun allowEmpty(): Boolean {
        return true
    }

    fun getViewModelFactory(): ViewModelProvider.Factory {
        return fragment.viewModelFactory
    }
}
