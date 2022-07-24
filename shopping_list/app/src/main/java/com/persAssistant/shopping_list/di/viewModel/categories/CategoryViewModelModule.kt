package com.persAssistant.shopping_list.di.viewModel.categories

import com.persAssistant.shopping_list.domain.interactors_impl.CategoryInteractorImpl
import com.persAssistant.shopping_list.ui.fragment.category.view_model.CreatorCategoryViewModel
import com.persAssistant.shopping_list.ui.fragment.category.view_model.EditorCategoryViewModel
import com.persAssistant.shopping_list.ui.fragment.category.view_model.ListOfCategoryViewModel
import dagger.Module
import dagger.Provides

@Module
class CategoryViewModelModule {

    @Provides
    fun provideCreatorCategoryViewModel(categoryInteractor: CategoryInteractorImpl): CreatorCategoryViewModel {
        return CreatorCategoryViewModel(categoryInteractor)
    }
    @Provides
    fun provideEditorCategoryViewModel(categoryInteractor: CategoryInteractorImpl): EditorCategoryViewModel {
        return EditorCategoryViewModel(categoryInteractor)
    }

    @Provides
    fun provideListOfCategoryViewModel(categoryInteractor: CategoryInteractorImpl): ListOfCategoryViewModel {
        return ListOfCategoryViewModel(categoryInteractor)
    }

}
