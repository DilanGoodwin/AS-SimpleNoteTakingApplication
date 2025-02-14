package com.learning.simplenotetakingapplication

import android.app.Application
import com.learning.simplenotetakingapplication.di.NoteModule
import com.learning.simplenotetakingapplication.di.NoteModuleImplementation
import com.learning.simplenotetakingapplication.di.NoteWidgetModule
import com.learning.simplenotetakingapplication.di.NoteWidgetModuleImplementation

/**
 * NoteApp
 */
class NoteApp:Application(){
    companion object{
        lateinit var appModule:NoteModule
        lateinit var widgetModule:NoteWidgetModule
    }

    /**
     * onCreate
     */
    override fun onCreate(){
        super.onCreate()
        appModule=NoteModuleImplementation(this)
        widgetModule=NoteWidgetModuleImplementation(this)
    }
}