package com.persAssistant.shopping_list.feature.splash.validation

import com.persAssistant.shopping_list.base.AppBaseFragment

abstract class ContainerImpl(
    containerFragment: Int
) : AppBaseFragment(containerFragment) {

    abstract val components: ArrayList<Component>
}
