package com.loc.newsapp.Presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingVM @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :ViewModel() {


    fun saveEntryAndNavigateToHome(){
        saveEntry()
        //navigateToHome()
    }

    private fun saveEntry(){
        viewModelScope.launch {
            appEntryUseCases.saveEntry()
        }
    }


}