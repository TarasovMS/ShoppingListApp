package com.persAssistant.shopping_list.base

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.R
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

    @ColorRes
    open fun statusBarColor(): Int = R.color.teal_700

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

    fun addErrorClearingEvent(input: EditText, parentLayout: TextInputLayout) {
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                parentLayout.error = null
                parentLayout.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    companion object{
        const val KEY_INDEX_TYPE = "INDEX_TYPE"
        const val KEY_PARENT_ID = "PARENT_ID"
        const val KEY_VISIBILITY_BUTTON = "VISIBILITY_BUTTON"
    }
}
