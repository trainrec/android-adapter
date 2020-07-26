package no.trainrec.android.adapter;

import no.trainrec.core.use_case.EntryAdder;
import no.trainrec.core.domain.ExerciseEntry;
import no.trainrec.core.data.TrainingRecord;
import no.trainrec.storage.CSV;
import no.trainrec.storage.FileIO;

import java.util.List;
import java.util.ArrayList;

public class Presenter {
    private EntryAdder adder;
    private TrainingRecord rec;

    public Presenter(FileIO io) {
        CSV storage = CSV(io);
        rec = TrainingRecord(storage);
        adder = EntryAdder(rec);
    }

    public void addEntry(String exerciseName) {
        adder.addEntry(exerciseName);
    }

    public void List<String> listEntries(){
        List<String> formattedEntries = ArrayList<String>();
        for (exerciseEntry in rec.listEntries()) {
            String exerciseDate = exerciseEntry.getDate();
            String exerciseName = exerciseEntry.getExercise();
            formattedEntries.add("$exerciseDate: $exerciseName");
        }
        return formattedEntries;
    }
}
