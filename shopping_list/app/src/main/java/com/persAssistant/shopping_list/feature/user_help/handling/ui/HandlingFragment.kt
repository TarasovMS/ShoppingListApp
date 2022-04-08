package com.persAssistant.shopping_list.feature.user_help.handling.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.FragmentHandlingBinding
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel
import com.persAssistant.shopping_list.util.viewBinding
import android.content.Intent
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.base.*
import com.persAssistant.shopping_list.base.ProgressState.FINISHED
import com.persAssistant.shopping_list.error.ViewError
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel.FieldValidation
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel.FieldValidation.*
import com.persAssistant.shopping_list.util.EMAIL_DEVELOPER
import com.persAssistant.shopping_list.util.getEventProgress
import com.persAssistant.shopping_list.util.hideKeyboard


class HandlingFragment : AppBaseFragment(R.layout.fragment_handling), ViewError {

    private val binding by viewBinding(FragmentHandlingBinding::bind)
    private val viewModel: HandlingViewModel by viewModels { viewModelFactory }

    //TODO избавиться от lateinit
    // убрать validateInput во viewModel
    // РАЗБлокировать отправку как ввел данные

    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun statusBarColor() = R.color.purple_200

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.fragmentHandlingToolbar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { updateProgressEvent(FINISHED) }
    }

    private fun initObservers() {
        viewModel.isActionEnabled.observe(viewLifecycleOwner) { allow ->
            if (allow) sendEmail()
        }

        progressEvent.observe(viewLifecycleOwner) { event ->
            event.getEventProgress { progressState ->
                binding.fragmentHandlingSendButton.isEnabled = progressState.isLoading()
                binding.fragmentHandlingSendAction.isEnabled = progressState.isLoading()
            }
        }

        viewModel.handling.observe(viewLifecycleOwner) {
            if (it != null) uiRouter.navigateUp()
        }

        viewModel.errorName.observe(viewLifecycleOwner) {
            showFieldError(it.getErrorMessageResource(), binding.fragmentHandlingNameInputEditText)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showFieldError(it.getErrorMessageResource(),binding.fragmentHandlingContentInputEditText)
        }
    }

    private fun initViews() {
        binding.fragmentHandlingEmailInputEditText.text = EMAIL_DEVELOPER

        binding.fragmentHandlingNameInputEditText.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus && messageFieldText().isNotEmpty()) validateInputs(NAME)
            }

        binding.fragmentHandlingContentInputEditText.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus && titleFieldText().isNotEmpty()) validateInputs(MESSAGE)
            }

        binding.fragmentHandlingSendButton.setOnClickListener { sendButtonEmail() }
        binding.fragmentHandlingSendAction.setOnClickListener { sendButtonEmail() }
    }

    private fun sendButtonEmail() {
        validateInputs(ALL_FIELDS)
        binding.root.hideKeyboard()
    }

    private fun validateInputs(field: FieldValidation?) {
        when (field) {
            NAME -> viewModel.validateTitle(name = titleFieldText())

            MESSAGE -> viewModel.validateMessage(message = messageFieldText())

            else -> viewModel.validateData(
                name = titleFieldText(),
                message = messageFieldText()
            )
        }
    }

    private fun sendEmail() {
        updateProgressEvent(ProgressState.LOADING)

        val email = Intent(Intent.ACTION_SEND)

        email.apply {
            type = "message/text"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailFieldText()))
            putExtra(Intent.EXTRA_SUBJECT, titleFieldText())
            putExtra(Intent.EXTRA_TEXT, messageFieldText())
        }

        startForResult.launch(Intent.createChooser(email, titleFieldText()))
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
}
