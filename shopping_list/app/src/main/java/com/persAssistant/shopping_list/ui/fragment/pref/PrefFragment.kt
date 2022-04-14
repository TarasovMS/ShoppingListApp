package com.persAssistant.shopping_list.ui.fragment.pref

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentPrefBinding
import com.persAssistant.shopping_list.ui.fragment.category.view_model.ListOfCategoryViewModel
import com.persAssistant.shopping_list.util.viewBinding
import javax.inject.Inject

class PrefFragment @Inject constructor() : AppBaseFragment(R.layout.fragment_pref) {

    private val binding: FragmentPrefBinding by viewBinding(FragmentPrefBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentPrefLanguage.setOnClickListener {
            uiRouter.navigateById(R.id.action_select_language)
        }

        binding.fragmentPrefHandling.setOnClickListener {
            uiRouter.navigateById(R.id.action_send_handling)
        }
    }

}
