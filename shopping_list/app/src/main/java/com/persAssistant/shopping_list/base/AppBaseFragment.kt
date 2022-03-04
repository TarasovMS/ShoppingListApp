package com.persAssistant.shopping_list.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.util.NavigationViewModel
import com.persAssistant.shopping_list.ui.App
import com.persAssistant.shopping_list.util.UiRouter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AppBaseFragment(
    @LayoutRes contentLayoutId: Int
) : DaggerFragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val navViewModel by viewModels<NavigationViewModel> { viewModelFactory }

    internal val uiRouter: UiRouter by lazy { UiRouter(findNavController()) }

    companion object{
        const val KEY_INDEX_TYPE = "INDEX_TYPE"
        const val KEY_PARENT_ID = "PARENT_ID"
        const val KEY_VISIBILITY_BUTTON = "VISIBILITY_BUTTON"
    }

    open fun getToolbarForBackBehavior(): MaterialToolbar? = null

    protected lateinit var app: App

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavData()
        handleToolBarBackPressed()
    }

    private fun observeNavData() {
        navViewModel.directionData.observe(this.viewLifecycleOwner) {
            uiRouter.navigateByDirection(it)
        }

        navViewModel.idData.observe(this.viewLifecycleOwner) {
            uiRouter.navigateById(it)
        }
    }

    private fun handleToolBarBackPressed() {
        getToolbarForBackBehavior()?.let {
            it.setNavigationOnClickListener {
                uiRouter.navigateUp()
            }
        }
    }


}