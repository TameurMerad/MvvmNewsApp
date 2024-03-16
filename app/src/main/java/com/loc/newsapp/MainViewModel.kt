package com.loc.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.Presentation.navgraph.Route
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set
    var splashScreenInitialized by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCases.readEntry().collect {
                startDestination = if (it) Route.NewsNavigation.route else Route.AppStartNavigation.route
            }
            splashCondition = false
            splashScreenInitialized = true

        }
    }


}