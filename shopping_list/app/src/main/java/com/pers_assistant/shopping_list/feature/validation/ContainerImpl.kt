package com.pers_assistant.shopping_list.feature.validation

import com.pers_assistant.shopping_list.base.AppBaseFragment

abstract class ContainerImpl(
    containerFragment: Int
) : AppBaseFragment(containerFragment) {

    abstract val components: ArrayList<Component>
}
