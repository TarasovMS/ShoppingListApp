package com.persAssistant.shopping_list.di.activity

import com.persAssistant.shopping_list.di.fragment.FragmentModule
import com.persAssistant.shopping_list.di.scopes.ActivityScope
import com.persAssistant.shopping_list.di.viewModel.MainActivityVmModule
import com.persAssistant.shopping_list.presentation.activity.main_activity.MainActivity
import com.persAssistant.shopping_list.presentation.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress(SUPPRESS_UNUSED)
abstract class MainActivityModule {

   @ActivityScope
   @ContributesAndroidInjector(
      modules = [
         MainActivityVmModule::class,
         FragmentModule::class,
      ]
   )
   abstract fun contributeMainActivity(): MainActivity
}
