package com.persAssistant.shopping_list.util

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import javax.inject.Inject

/**
 * Navigation in the app.
 */
class UiRouter @Inject constructor(val navController: NavController) {

    fun navigateByDirection(direction: NavDirections, navOptions: NavOptions? = null) {
        navController.navigateSafe(direction, navOptions)
    }

    fun navigateByIdWithPopUpNavOptions(id: Int, popUpId: Int, args: Bundle? = null): Boolean {
        return navController.navigateSafe(id, args, popUpToInclusiveNavOptions(popUpId))
    }

    fun navigateById(id: Int, args: Bundle? = null): Boolean {
        return navController.navigateSafe(id, args)
    }

    fun navigateByDirectionWithPopUpTo(direction: NavDirections) {
        navController.navigateSafeWithOutNavOptions(direction)
    }

//    fun navigateToNoInternetFragment() {
//        navController.navigateSafe(R.id.noInternetFragment)
//    }


    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun getCurrentState() = navController.currentBackStackEntry?.savedStateHandle

    fun getPreviousState() = navController.previousBackStackEntry?.savedStateHandle

    private fun NavController.navigateSafeWithOutNavOptions(navDirections: NavDirections? = null) {
        try {
            navDirections?.let {
                this.navigate(navDirections)
            }
        } catch (e: IllegalArgumentException) {
            Log.e(javaClass.simpleName, "navigateSafeWithOutNavOptions: ", e)
        }
    }

    private fun NavController.navigateSafe(
        navDirections: NavDirections? = null,
        navOptions: NavOptions? = null
    ) {
        try {
            val options = navOptions ?: defaultNavOptions()
            navDirections?.let {
                this.navigate(navDirections, options)
            }
        } catch (e: IllegalArgumentException) {
            Log.e(javaClass.simpleName, "navigateSafe: ", e)
        }
    }

    private fun NavController.navigateSafe(
        id: Int? = null,
        args: Bundle? = null,
        navOptions: NavOptions? = null
    ): Boolean {
        return try {
            id?.let {
                this.navigate(it, args, navOptions ?: defaultNavOptions())
            }
            true
        } catch (e: IllegalArgumentException) {
            Log.e(javaClass.simpleName, "navigateSafe: ", e)
            false
        }
    }

    private fun defaultNavOptions() = navOptions { // Use the Kotlin DSL for building NavOptions
        anim {
            enter = android.R.animator.fade_in
            exit = android.R.animator.fade_out
        }
    }

    private fun popUpToInclusiveNavOptions(@IdRes popUpTo: Int) = navOptions {
        popUpTo(popUpTo) {
            inclusive = true
        }

        anim {
            enter = android.R.animator.fade_in
            exit = android.R.animator.fade_out
        }
    }
}
