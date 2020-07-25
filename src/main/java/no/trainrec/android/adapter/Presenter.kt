package no.trainrec.android.ui

import no.trainrec.core.use_case.EntryAdder
import no.trainrec.core.domain.ExerciseEntry
import no.trainrec.core.data.TrainingRecord
import no.trainrec.storage.CSV
import no.trainrec.storage.FileIO

import java.io.File

class Presenter(appFilesDir: String) {
    private val rec: TrainingRecord
    private val entryAdder: EntryAdder

    init {
        val data = File(appFilesDir, "data.csv")
        try {
            data.createNewFile()
        } catch(ex: Exception) {
            ex.printStackTrace()
        }
        val io = FileIO(data)
        val storage = CSV(io)
        rec = TrainingRecord(storage)
        entryAdder = EntryAdder(rec)
    }

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
