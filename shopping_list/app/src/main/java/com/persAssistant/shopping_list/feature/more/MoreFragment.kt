package com.persAssistant.shopping_list.feature.more

import androidx.fragment.app.viewModels
import com.persAssistant.shopping_list.R
import com.persAssistant.shopping_list.common.AppBaseFragment
import com.persAssistant.shopping_list.databinding.FragmentMoreBinding
import com.persAssistant.shopping_list.feature.category.view_model.ListOfCategoryViewModel
import com.persAssistant.shopping_list.util.delegate.viewBinding
import javax.inject.Inject


class MoreFragment @Inject constructor() : AppBaseFragment(R.layout.fragment_more) {

    // Todo добавить пункты для настроек и заменить иконки

    private val binding: FragmentMoreBinding by viewBinding(FragmentMoreBinding::bind)
    private val viewModel: ListOfCategoryViewModel by viewModels { viewModelFactory }

    override fun initListeners() {
        binding.run {
            fragmentMoreLanguage.setOnClickListener {
                uiRouter.navigateById(R.id.action_select_language)
            }

            fragmentMoreHandling.setOnClickListener {
                uiRouter.navigateById(R.id.action_send_handling)
            }

            fragmentMoreNotifications.setOnClickListener {
                uiRouter.navigateById(R.id.action_connectionUnavailable_fragment)
            }
        }
    }
}
