package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

/**
 * NoteUseCases
 * @param getNotes UseCase GetNotes call
 * @param upsertNote UseCase UpsertNote call
 */
data class NoteUseCases(val getNotes:GetNotes, val upsertNote:UpsertNote)