package com.persAssistant.shopping_list.di.viewModel

import androidx.lifecycle.ViewModel
import com.persAssistant.shopping_list.di.scopes.ActivityScope
import com.persAssistant.shopping_list.ui.activity.MainActivityViewModel
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress(SUPPRESS_UNUSED)
@Module
interface MainActivityVmModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityVmModule(vm: MainActivityViewModel): ViewModel

}
