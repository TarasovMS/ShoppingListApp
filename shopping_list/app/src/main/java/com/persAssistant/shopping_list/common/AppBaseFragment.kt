package com.persAssistant.shopping_list.common

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.error.ViewError
import com.persAssistant.shopping_list.util.NavigationViewModel
import com.persAssistant.shopping_list.ui.App
import com.persAssistant.shopping_list.util.UiRouter
import com.persAssistant.shopping_list.util.updateStatusBarColor
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AppBaseFragment(
    @LayoutRes contentLayoutId: Int
) : DaggerFragment(contentLayoutId), ViewError {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var app: App
    private val navViewModel by viewModels<NavigationViewModel> { viewModelFactory }
    internal val uiRouter: UiRouter by lazy { UiRouter(findNavController()) }

    @ColorRes
    open fun statusBarColor(): Int = R.color.teal_700

    open fun initObservers() {}
    open fun initUi() {}
    open fun initListeners() {}
    open fun getToolbarForBackBehavior(): MaterialToolbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        initObservers()
        observeNavData()
        handleToolBarBackPressed()
    }

    override fun onResume() {
        super.onResume()
        if (statusBarColor() != DO_NOT_REPAINT_STATUS_BAR) updateStatusBarColor(statusBarColor())
    }

    override fun showFieldError(
        stringId: Int,
        fieldView: View,
        parentLayout: TextInputLayout?
    ) {
        val messageFromId = getString(stringId)
        showFieldError(messageFromId, fieldView, parentLayout)
    }

    override fun showTextInputFieldError(
        input: EditText,
        parentLayout: TextInputLayout?
    ) {
        parentLayout ?: return
        addErrorClearingEvent(input, parentLayout)
    }

    private fun showFieldError(
        messageFromId: String,
        fieldView: View,
        parentLayout: TextInputLayout? = null
    ) {
        val parent = parentLayout
            ?: (fieldView.parent as? TextInputLayout)
            ?: fieldView.parent.parent as? TextInputLayout

        setErrorToParentLayout(parent, messageFromId)

        when (fieldView) {
            is TextInputEditText -> showTextInputFieldError(fieldView, parent)
            is AutoCompleteTextView -> showTextInputFieldError(fieldView, parent)
        }
    }

    private fun setErrorToParentLayout(parent: TextInputLayout?, msg: String) {
        parent?.let {
            parent.isErrorEnabled = true
            parent.error = msg
            parent.errorIconDrawable = null
        }
    }

    private fun addErrorClearingEvent(input: EditText, parentLayout: TextInputLayout) {
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                parentLayout.error = null
                parentLayout.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
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
