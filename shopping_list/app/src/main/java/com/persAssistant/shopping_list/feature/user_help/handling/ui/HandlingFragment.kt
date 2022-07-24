package com.persAssistant.shopping_list.feature.user_help.handling.ui

import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.FragmentHandlingBinding
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding
import android.content.Intent
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.common.*
import com.persAssistant.shopping_list.common.AppBaseViewModel.ProgressState.FINISHED
import com.persAssistant.shopping_list.common.AppBaseViewModel.ProgressState.LOADING
import com.persAssistant.shopping_list.error.ViewError
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel.FieldValidation
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel.FieldValidation.*
import com.persAssistant.shopping_list.util.EMAIL_DEVELOPER
import com.persAssistant.shopping_list.util.getEventProgress
import com.persAssistant.shopping_list.util.hideKeyboard

class HandlingFragment : AppBaseFragment(R.layout.fragment_handling), ViewError {

    private val binding by viewBinding(FragmentHandlingBinding::bind)
    private val viewModel: HandlingViewModel by viewModels { viewModelFactory }

    private val startForResult by lazy {
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { viewModel.updateProgressEvent(FINISHED) }
    }

    @ColorRes
    override fun statusBarColor() = R.color.purple_200

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.fragmentHandlingToolbar
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startForResult
    }

    override fun initObservers() {
        viewModel.run {

            progressEvent.observe(viewLifecycleOwner) { event ->
                event.getEventProgress { progressState ->
                    binding.run {
                        fragmentHandlingSendButton.isEnabled = progressState.isFinished()
                        fragmentHandlingSendAction.isEnabled = progressState.isFinished()
                    }
                }
            }

            isActionEnabled.observe(viewLifecycleOwner) { allow ->
                if (allow) sendEmail()
            }

            handling.observe(viewLifecycleOwner) {
                if (it != null) uiRouter.navigateUp()
            }

            errorTitle.observe(viewLifecycleOwner) {
                showFieldError(
                    it.getErrorMessageResource(),
                    binding.fragmentHandlingNameInputEditText
                )
            }

            errorMessage.observe(viewLifecycleOwner) {
                showFieldError(
                    it.getErrorMessageResource(),
                    binding.fragmentHandlingContentInputEditText
                )
            }
        }
    }

    override fun initUi() {
        binding.run {
            fragmentHandlingNameInputEditText.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus && messageFieldText().isNotEmpty()) validation(TITLE)
                }

            fragmentHandlingContentInputEditText.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus && titleFieldText().isNotEmpty()) validation(MESSAGE)
                }

            fragmentHandlingEmailInputEditText.text = EMAIL_DEVELOPER
        }
    }

    override fun initListeners() {
        binding.apply {
            fragmentHandlingSendAction.setOnClickListener { validation(ALL_FIELDS) }
            fragmentHandlingSendButton.setOnClickListener { validation(ALL_FIELDS) }
        }
    }

    private fun validation(field: FieldValidation) {
        viewModel.validateInputs(
            field = field,
            title = titleFieldText(),
            message = messageFieldText()
        )

        if (field.isAllFields()) binding.root.hideKeyboard()
    }

    private fun sendEmail() {
        viewModel.updateProgressEvent(LOADING)

        startForResult.launch(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    type = TYPE_EMAIL
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(emailFieldText()))
                    putExtra(Intent.EXTRA_SUBJECT, titleFieldText())
                    putExtra(Intent.EXTRA_TEXT, messageFieldText())
                }, titleFieldText()
            )
        )
    }

    private fun titleFieldText(): String {
        return binding.fragmentHandlingNameInputEditText.text.toString()
    }

    private fun messageFieldText(): String {
        return binding.fragmentHandlingContentInputEditText.text.toString()
    }

    private fun emailFieldText(): String {
        return binding.fragmentHandlingEmailInputEditText.text.toString()
    }

    override fun showFieldError(id: Int, fieldView: View, parentLayout: TextInputLayout?) {
        val messageFromId = getString(id)
        showFieldError(messageFromId, fieldView, parentLayout)
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

    override fun showTextInputFieldError(
        input: EditText,
        parentLayout: TextInputLayout?
    ) {
        parentLayout ?: return
        addErrorClearingEvent(input, parentLayout)
    }

    private companion object {
        const val TYPE_EMAIL = "message/text"
    }
}
