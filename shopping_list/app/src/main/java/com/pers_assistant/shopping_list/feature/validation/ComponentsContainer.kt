package com.pers_assistant.shopping_list.feature.validation

import com.pers_assistant.shopping_list.base.AppBaseFragment


interface ComponentsContainer {

    val componentType: String

    fun getComponents(fragment: AppBaseFragment): ArrayList<Component>
}
