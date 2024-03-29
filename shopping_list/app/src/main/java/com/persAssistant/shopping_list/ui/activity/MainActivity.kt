package com.persAssistant.shopping_list.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.*
import androidx.navigation.NavDestination
import androidx.navigation.ui.setupWithNavController
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseActivity
import com.persAssistant.shopping_list.databinding.ActivityMainBinding
import com.persAssistant.shopping_list.util.UiRouter
import com.persAssistant.shopping_list.util.gone
import com.persAssistant.shopping_list.util.visible

open class MainActivity : AppBaseActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }
    private var currentDestination: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        setUpBottomNavigation()
        destinationListener()
    }

    override fun initNetworkObserver(uiRouter: UiRouter) {
        initNoInternetShower(viewModel, uiRouter, this)
        observeNetworkStatus(applicationContext)
    }

    private fun setUpBottomNavigation() {
        binding.apply {
            activityMainBottomNav.itemIconTintList = null
            activityMainBottomNav.setupWithNavController(navController)
        }

        binding.activityMainBottomNav.setOnItemReselectedListener {
            Log.d("BottomNav", "menuItem = $it")
        }
    }

    private fun destinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleDestination(destination)
        }
    }

    private fun handleDestination(destination: NavDestination) {
        if (destination.id != currentDestination) {
            currentDestination = destination.id
            switchBottomMenu(destination)
        }
    }

    private fun switchBottomMenu(destination: NavDestination) {
        when (destination.id) {
            R.id.shoppingList -> switchBottomNav(R.id.shoppingList)
            R.id.categoryList -> switchBottomNav(R.id.categoryList)
            R.id.prefFragment -> switchBottomNav(R.id.prefFragment)
            else -> hideBottomNav()
        }
    }

    private fun switchBottomNav(menuItem: Int) {
        if (!binding.activityMainBottomNav.isVisible) binding.activityMainBottomNav.visible()
        binding.activityMainBottomNav.selectedItemId = menuItem
    }

    private fun hideBottomNav() {
        binding.activityMainBottomNav.gone()
    }

    override fun onBackPressed() {
        binding.activityMainBottomNav.run {
            if (isVisible) {
                when {
                    selectedItemId != R.id.shoppingList -> switchBottomNav(R.id.shoppingList)
                    selectedItemId == R.id.shoppingList -> finish()
                    else -> super.onBackPressed()
                }
            } else super.onBackPressed()
        }
    }
}
