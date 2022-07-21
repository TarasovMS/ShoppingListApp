package com.persAssistant.shopping_list.feature.validation

import androidx.viewbinding.ViewBinding

interface Component {
    fun initialize()
}

interface Binding {
    var binding: ViewBinding
}

//interface ExtensionComponent<T> : Component {
//    val bindingComponent: BindingComponent<T>
//}
