package com.persAssistant.shopping_list.feature.user_help.handling.di

import com.persAssistant.shopping_list.data.handler.ValidateHandler
import com.persAssistant.shopping_list.feature.user_help.handling.usecase.HandlingUseCase
import com.persAssistant.shopping_list.common.SUPPRESS_UNUSED
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@[Module Suppress(SUPPRESS_UNUSED)]
class HandlingModule  {

    @[Provides Singleton]
    fun provideHandlingUseCase (validateHandler: ValidateHandler): HandlingUseCase {
        return HandlingUseCase(validateHandler)
    }

}
