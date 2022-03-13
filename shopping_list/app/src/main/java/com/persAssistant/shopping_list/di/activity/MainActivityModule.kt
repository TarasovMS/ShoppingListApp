package com.persAssistant.shopping_list.di.activity

import com.persAssistant.shopping_list.di.contributes.ContributePref
import com.persAssistant.shopping_list.di.fragment.FragmentModule
import com.persAssistant.shopping_list.di.scopes.ActivityScope
import com.persAssistant.shopping_list.di.viewModel.MainActivityVmModule
import com.persAssistant.shopping_list.feature.splash.di.ContributeSplash
import com.persAssistant.shopping_list.ui.activity.MainActivity
import com.persAssistant.shopping_list.util.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress(SUPPRESS_UNUSED)
abstract class MainActivityModule {

   //TODO разделить все фрагменты поотдельности

   @ActivityScope
   @ContributesAndroidInjector(
      modules = [
         MainActivityVmModule::class,
         FragmentModule::class,

         ContributeSplash::class,
         ContributePref::class
      ]
   )
   abstract fun contributeMainActivity(): MainActivity
}
