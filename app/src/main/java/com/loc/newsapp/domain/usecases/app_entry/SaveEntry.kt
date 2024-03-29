package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manger.LocalUserManger

class SaveEntry(
    private val localUserManger: LocalUserManger
) {

suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }

}