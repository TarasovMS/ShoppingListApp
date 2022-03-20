package com.persAssistant.shopping_list.feature.splash.validation

import androidx.viewbinding.ViewBinding

interface Component {
    fun initialize()
}

interface Binding {
    var binding: ViewBinding
}

interface ExtensionComponent<T> : Component {
    val bindingComponent: BindingComponent<T>
}

//interface ExtensionComponentStingImpl : ExtensionComponent<String> {
//    override val bindingComponent: StringBindingComponent
//}
