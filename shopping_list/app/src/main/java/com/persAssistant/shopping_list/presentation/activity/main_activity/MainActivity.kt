package com.persAssistant.shopping_list.presentation.activity.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseActivity
import com.persAssistant.shopping_list.databinding.ActivityMainBinding
import com.persAssistant.shopping_list.presentation.util.goneWithOutFade
import com.persAssistant.shopping_list.presentation.util.visibleWithOutFade
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppBaseActivity() {


    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }
    private var currentDestination: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        binding.activityMainBottomNav.setupWithNavController(navController)
        destinationListener()
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

            else -> hideBottomNav()
        }
    }

    private fun switchBottomNav(menuItem: Int) {
        if (!binding.activityMainBottomNav.isVisible) {
            binding.activityMainBottomNav.visibleWithOutFade()
        }
        binding.activityMainBottomNav.selectedItemId = menuItem
    }


    private fun hideBottomNav() {
        binding.activityMainBottomNav.goneWithOutFade()
    }

    override fun onBackPressed() {
        binding.activityMainBottomNav.run {
            if (isVisible) {
                when {
                    selectedItemId != R.id.shoppingList -> {
                        switchBottomNav(R.id.shoppingList)
                    }
                    selectedItemId == R.id.shoppingList -> {
                        finish()
                    }
                    else -> {
                        super.onBackPressed()
                    }
                }
            } else
                super.onBackPressed()
        }
    }

}