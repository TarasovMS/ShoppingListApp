package com.persAssistant.shopping_list.ui.fragment.more

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.base.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentPrefBinding
import com.persAssistant.shopping_list.ui.fragment.category.view_model.ListOfCategoryViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding
import javax.inject.Inject


class MoreFragment @Inject constructor() : AppBaseFragment(R.layout.fragment_more) {

    // Todo добавить пункты для настроек и заменить иконки

    private val binding: FragmentPrefBinding by viewBinding(FragmentPrefBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.fragmentPrefLanguage.setOnClickListener {
            uiRouter.navigateById(R.id.action_select_language)
        }

        binding.fragmentPrefHandling.setOnClickListener {
            uiRouter.navigateById(R.id.action_send_handling)
        }

        binding.fragmentPrefNotifications.setOnClickListener {
            uiRouter.navigateById(R.id.action_connectionUnavailable_fragment)
        }
    }
}