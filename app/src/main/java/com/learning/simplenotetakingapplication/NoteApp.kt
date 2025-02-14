package com.learning.simplenotetakingapplication

import android.app.Application
import com.learning.simplenotetakingapplication.di.NoteModule
import com.learning.simplenotetakingapplication.di.NoteModuleImplementation

/**
 * NoteApp
 */
class NoteApp:Application(){
    companion object{
        lateinit var appModule:NoteModule
    }

    /**
     * onCreate
     */
    override fun onCreate(){
        super.onCreate()
        appModule=NoteModuleImplementation(this)
    }
}