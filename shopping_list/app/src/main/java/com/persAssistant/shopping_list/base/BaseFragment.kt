package com.persAssistant.shopping_list.base

import androidx.fragment.app.Fragment
import com.persAssistant.shopping_list.presentation.App

abstract class BaseFragment : Fragment() {

    companion object{
        const val KEY_INDEX_TYPE = "INDEX_TYPE"
        const val KEY_PARENT_ID = "PARENT_ID"
        const val KEY_VISIBILITY_BUTTON = "VISIBILITY_BUTTON"
    }

    protected lateinit var app: App
}