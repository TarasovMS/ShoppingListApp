package com.persAssistant.shopping_list.feature.user_help.handling.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.databinding.FragmentHandlingBinding
import com.persAssistant.shopping_list.feature.user_help.handling.viewmodel.HandlingViewModel
import com.persAssistant.shopping_list.util.viewBinding
import android.content.Intent
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.persAssistant.shopping_list.error.ViewError
import com.persAssistant.shopping_list.feature.splash.validation.Component
import com.persAssistant.shopping_list.feature.splash.validation.ContainerImpl
import com.persAssistant.shopping_list.feature.splash.validation.EmailComponents
import com.persAssistant.shopping_list.util.EMAIL_DEVELOPER
import com.persAssistant.shopping_list.util.hideKeyboard
import kotlinx.coroutines.launch


class HandlingFragment : ContainerImpl(R.layout.fragment_handling), ViewError {

    override lateinit var components: ArrayList<Component>
    private val binding by viewBinding(FragmentHandlingBinding::bind)
    private val viewModel: HandlingViewModel by viewModels { viewModelFactory }

    override fun getToolbarForBackBehavior(): MaterialToolbar {
        return binding.fragmentHandlingToolbar
    }

    override fun statusBarColor() = R.color.white

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        viewModel.isActionEnabled.observe(viewLifecycleOwner, {
            binding.fragmentHandlingSendButton.isEnabled = it
            binding.fragmentHandlingSendAction.isEnabled = it

            addColorFilterForView(
                binding.fragmentHandlingSendAction,
                if (it) R.color.black else R.color.unfocused_color
            )
        })

        viewModel.handling.observe(viewLifecycleOwner) {
            if (it != null) {
                uiRouter.navigateUp()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){
            showFieldError(it.getErrorMessageResource(),binding.fragmentHandlingNameInputEditText)
        }
    }

    private fun addColorFilterForView(view: ImageView, @ColorRes color: Int) {
        view.setColorFilter(
            ContextCompat.getColor(view.context, color),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun sendEmail() {
        val email = Intent(Intent.ACTION_SEND)

        email.apply {
            putExtra(
                Intent.EXTRA_EMAIL,
                arrayOf(binding.fragmentHandlingEmailInputEditText.text.toString())
            )

            putExtra(
                Intent.EXTRA_SUBJECT,
                binding.fragmentHandlingNameInputEditText.text.toString()
            )

            putExtra(
                Intent.EXTRA_TEXT,
                binding.fragmentHandlingContentInputEditText.text.toString()
            )

            type = "message/text"
        }

        startActivity(Intent.createChooser(email, "Выберите email клиент :"))
    }

    private fun initViews() {
        components = EmailComponents.getComponents(this)

        binding.fragmentHandlingEmailInputEditText.text = EMAIL_DEVELOPER

        binding.fragmentHandlingNameInputEditText.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->

            if (!hasFocus) {
                validateInputs()
                save()
            }
        }

        binding.fragmentHandlingContentInputEditText.onFocusChangeListener =
            View.OnFocusChangeListener{ _, hasFocus ->
                if (!hasFocus) {
                    validateInputs()
                    save()
                }
            }

        binding.fragmentHandlingSendButton.setOnClickListener { sendButtonEmail() }
        binding.fragmentHandlingSendAction.setOnClickListener { sendButtonEmail() }

        lifecycleScope.launch {
            components.forEach {
                try {
                    it.initialize()
                } catch (e: Exception) {
                    Log.w(it.javaClass.canonicalName, e)
                }
            }
        }
    }

    private fun sendButtonEmail(){
        sendEmail()
        binding.root.hideKeyboard()
    }

    private fun validateInputs() {
        viewModel.validateData(
            name = binding.fragmentHandlingNameInputEditText.text.toString(),
            message = binding.fragmentHandlingContentInputEditText.text.toString()
        )
    }

    private fun save() {
        viewModel.saveData(
            name = binding.fragmentHandlingNameInputEditText.text.toString(),
            message = binding.fragmentHandlingContentInputEditText.text.toString()
        )
    }

    override fun showFieldError(id: Int, fieldView: View, parentLayout: TextInputLayout?) {
        val messageFromId = getString(id)
        showFieldError(messageFromId, fieldView, parentLayout)
    }

    fun showFieldError(
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
