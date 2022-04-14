package com.persAssistant.shopping_list.feature.validation

import com.persAssistant.shopping_list.base.AppBaseFragment


interface ComponentsContainer {

    val componentType: String

    fun getComponents(fragment: AppBaseFragment): ArrayList<Component>
}