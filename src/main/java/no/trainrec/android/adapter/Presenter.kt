package no.trainrec.android.ui

import no.trainrec.storage.CSV
import no.trainrec.storage.FileIO

import no.trainrec.core.use_case.EntryAdder
import no.trainrec.core.domain.ExerciseEntry
import no.trainrec.core.data.TrainingRecord

class Presenter(appFilesDir: String) {
    private val storage = Storage(appFilesDir)
    private val record = TrainingRecord(storage)
    private val entryAdder = EntryAdder(record)

    fun addEntry(exerciseName: String) {
        entryAdder.addEntry(exerciseName)
    }

    fun listEntries(): List<String> {
        var formattedEntries = ArrayList<String>()
        for (exerciseEntry in record.listEntries()) {
            val exerciseDate = exerciseEntry.getDate()
            val exerciseName = exerciseEntry.getExercise()
            formattedEntries.add("$exerciseDate: $exerciseName")
        }
        return formattedEntries
    }
}
