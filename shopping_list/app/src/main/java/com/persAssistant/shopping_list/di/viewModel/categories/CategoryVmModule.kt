package com.persAssistant.shopping_list.di.viewModel.categories

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.FragmentScope
import com.persAssistant.shopping_list.di.viewModel.ViewModelKey
import com.persAssistant.shopping_list.ui.fragment.category.view_model.CreatorCategoryViewModel
import com.persAssistant.shopping_list.ui.fragment.category.view_model.EditorCategoryViewModel
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress(SUPPRESS_UNUSED)
interface CategoryVmModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(CreatorCategoryViewModel::class)
    fun bindCreatorCategoryViewModel(viewModel: CreatorCategoryViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(EditorCategoryViewModel::class)
    fun bindEditorCategoryViewModel(viewModel: EditorCategoryViewModel): ViewModel

}