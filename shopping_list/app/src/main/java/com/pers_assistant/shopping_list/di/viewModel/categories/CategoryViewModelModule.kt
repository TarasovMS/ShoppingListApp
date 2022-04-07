package com.pers_assistant.shopping_list.di.viewModel.categories

import com.pers_assistant.shopping_list.domain.interactors.CategoryInteractor
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.CreatorCategoryViewModel
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.EditorCategoryViewModel
import com.pers_assistant.shopping_list.ui.fragment.category.view_model.ListOfCategoryViewModel
import dagger.Module
import dagger.Provides

@Module
class CategoryViewModelModule {
    @Provides
    fun provideCreatorCategoryViewModel(categoryInteractor: CategoryInteractor): CreatorCategoryViewModel {
        return CreatorCategoryViewModel(categoryInteractor)
    }
    @Provides
    fun provideEditorCategoryViewModel(categoryInteractor: CategoryInteractor): EditorCategoryViewModel {
        return EditorCategoryViewModel(categoryInteractor)
    }

    @Provides
    fun provideListOfCategoryViewModel(categoryInteractor: CategoryInteractor): ListOfCategoryViewModel {
        return ListOfCategoryViewModel(categoryInteractor)
    }

}