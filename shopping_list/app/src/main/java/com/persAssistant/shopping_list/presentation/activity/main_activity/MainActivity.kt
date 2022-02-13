package com.persAssistant.shopping_list.presentation.activity.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.presentation.util.goneWithOutFade
import com.persAssistant.shopping_list.presentation.util.visibleWithOutFade
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.activity_main_nav_host_fragment)
    }

    private var currentDestination: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        activity_main_bottomNav.setupWithNavController(navController)
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
        if (!activity_main_bottomNav.isVisible) {
            activity_main_bottomNav.visibleWithOutFade()
        }
        activity_main_bottomNav.selectedItemId = menuItem
    }


    private fun hideBottomNav() {
        activity_main_bottomNav.goneWithOutFade()
    }

    override fun onBackPressed() {
        activity_main_bottomNav.run {
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