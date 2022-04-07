package com.pers_assistant.shopping_list.ui.fragment.pref

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.pers_assistant.shopping_list.R
import com.pers_assistant.shopping_list.base.AppBaseFragment
import com.pers_assistant.shopping_list.databinding.FragmentPrefBinding
import com.pers_assistant.shopping_list.ui.activity.LanguageActivity
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.ListOfCategoryViewModel
import com.pers_assistant.shopping_list.util.EMAIL_DEVELOPER
import com.pers_assistant.shopping_list.util.safeGetData
import com.pers_assistant.shopping_list.util.viewBinding
import javax.inject.Inject

class PrefFragment @Inject constructor() : AppBaseFragment(R.layout.fragment_pref) {

    private val binding: FragmentPrefBinding by viewBinding(FragmentPrefBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentPrefLanguage.setOnClickListener {
//            uiRouter.navigateById(R.id.)
            val intent = Intent(requireContext(), LanguageActivity::class.java)
            startActivity(intent)
        }

        binding.fragmentPrefHandling.setOnClickListener {
            uiRouter.navigateById(R.id.action_send_handling)
        }
    }

    private fun sendEmail( subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.apply {
            data = Uri.parse("mailto:") // TODO разобраться что это?
            type = "text/plain" // TODO разобраться что это?
            putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL_DEVELOPER))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        safeGetData {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
    }

}