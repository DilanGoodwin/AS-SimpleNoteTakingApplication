package com.learning.simplenotetakingapplication.f_notetaking.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Note
 *
 * Data class storing note information
 *
 * @param content Value storing what is within the note
 * @param uid Unique identifier for the note
 */
@Entity
data class Note(val content:String="",@PrimaryKey(autoGenerate=false)val uid:Int=0)
