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
        CSV storage = new CSV(io);
        rec = new TrainingRecord(storage);
        adder = new EntryAdder(rec);
    }

    public void setEntryAdder(EntryAdder newAdder){
        adder = newAdder;
    }

    public void setTrainingRecord(TrainingRecord newRecord){
        rec = newRecord;
    }

    public void addEntry(String exerciseName) {
        adder.addEntry(exerciseName);
    }

    public List<String> listEntries(){
        List<String> formattedEntries = new ArrayList<String>();
        for (ExerciseEntry entry : rec.listEntries()) {
            formattedEntries.add(String.format(
                    "%s %s", entry.getDate(), entry.getExercise()
                    ));
        }
        return formattedEntries;
    }

    public void save() {
        rec.save();
    }
}
