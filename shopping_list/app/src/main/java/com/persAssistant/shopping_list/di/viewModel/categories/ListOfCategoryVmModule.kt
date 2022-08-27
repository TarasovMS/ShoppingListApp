package com.persAssistant.shopping_list.di.viewModel.categories

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.feature.category.view_model.ListOfCategoryViewModel
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@[Module Suppress(SUPPRESS_UNUSED)]
interface ListOfCategoryVmModule {

    @[Binds FragmentScope IntoMap ViewModelKey(ListOfCategoryViewModel::class)]
    fun bindListOfCategoryViewModel(viewModel: ListOfCategoryViewModel): ViewModel

}
