package com.learning.simplenotetakingapplication.f_notetaking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learning.simplenotetakingapplication.f_notetaking.domain.Note

@Database(entities=[Note::class],version=1)
abstract class NoteDB:RoomDatabase(){
    abstract val dao: NoteDao

    companion object{
        const val DB_NAME="note.db"
        private var currentInstance:NoteDB?=null

        fun getInstance(context: Context):NoteDB{
            synchronized(this){
                var instance=currentInstance

                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,NoteDB::class.java,NoteDB.DB_NAME).fallbackToDestructiveMigration().build()
                    currentInstance=instance
                }
                return instance
            }
        }
    }
}