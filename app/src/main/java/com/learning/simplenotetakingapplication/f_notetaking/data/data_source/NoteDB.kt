package com.learning.simplenotetakingapplication.f_notetaking.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learning.simplenotetakingapplication.f_notetaking.domain.Note

/**
 * NoteDB
 *
 * Access Note Database
 *
 * @property dao Access the database SQL commands
 */
@Database(entities=[Note::class],version=1)
abstract class NoteDB:RoomDatabase(){
    abstract val dao: NoteDao

    companion object{
        private const val DB_NAME="note.db"
        private var currentInstance: NoteDB?=null

        /**
         * getInstance
         *
         * Get the database instance if current instances is null
         *
         * @param context The application context so the Room databaseBuilder can be deployed
         */
        fun getInstance(context:Context): NoteDB {
            synchronized(this){
                var instance= currentInstance

                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,
                        NoteDB::class.java,
                        DB_NAME
                    ).build()
                    currentInstance =instance
                }
                return instance
            }
        }
    }
}