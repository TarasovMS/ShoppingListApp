package com.persAssistant.shopping_list.di.viewModel

import com.persAssistant.shopping_list.domain.interactors.CategoryInteractor
import com.persAssistant.shopping_list.presentation.activity.category.CreatorCategoryViewModel
import com.persAssistant.shopping_list.presentation.activity.category.EditorCategoryViewModel
import com.persAssistant.shopping_list.presentation.fragment.list_of_category_fragment.ListOfCategoryViewModel
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