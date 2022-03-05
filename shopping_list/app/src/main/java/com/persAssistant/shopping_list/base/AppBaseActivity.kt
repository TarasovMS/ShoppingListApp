package com.persAssistant.shopping_list.base

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.ui.App
import com.persAssistant.shopping_list.util.UiRouter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AppBaseActivity: DaggerAppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val navController: NavController by lazy { findNavController(R.id.activity_main_nav_host_fragment) }

    protected val uiRouter by lazy { UiRouter(navController) }

    companion object{
        const val KEY_INDEX_TYPE = "INDEX_TYPE"
        const val KEY_PARENT_ID = "PARENT_ID"
        const val KEY_VISIBILITY_BUTTON = "VISIBILITY_BUTTON"
    }

    protected lateinit var app: App
}