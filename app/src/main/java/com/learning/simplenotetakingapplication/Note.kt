package com.learning.simplenotetakingapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Note
 * @param content
 * @param uid
 */
@Entity
data class Note(val content:String,@PrimaryKey(autoGenerate=true)val uid:Int=0){}
