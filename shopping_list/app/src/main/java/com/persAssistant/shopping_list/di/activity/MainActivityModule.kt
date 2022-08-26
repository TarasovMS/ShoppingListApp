package com.persAssistant.shopping_list.di.activity

import com.persAssistant.shopping_list.di.contributes.ContributeMore
import com.persAssistant.shopping_list.di.fragment.FragmentModule
import com.persAssistant.shopping_list.di.scopes.ActivityScope
import com.persAssistant.shopping_list.di.viewModel.MainActivityVmModule
import com.persAssistant.shopping_list.feature.connection_unavailable.di.ContributeConnection
import com.persAssistant.shopping_list.feature.language.di.ContributeLanguage
import com.persAssistant.shopping_list.feature.splash.di.ContributeSplash
import com.persAssistant.shopping_list.feature.user_help.handling.di.ContributeHandling
import com.persAssistant.shopping_list.ui.activity.MainActivity
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Module
import dagger.android.ContributesAndroidInjector

@[Module Suppress(SUPPRESS_UNUSED)]
abstract class MainActivityModule {

   //TODO разделить все фрагменты поотдельности

   @[ActivityScope ContributesAndroidInjector(
      modules = [
         MainActivityVmModule::class,
         FragmentModule::class,

         ContributeSplash::class,
         ContributeMore::class,
         ContributeHandling::class,

         ContributeLanguage::class,
         ContributeConnection::class
      ]
   )]
   abstract fun contributeMainActivity(): MainActivity

}
