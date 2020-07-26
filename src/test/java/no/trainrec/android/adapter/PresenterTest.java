package no.trainrec.android.adapter;

import java.util.List;
import java.util.ArrayList;

import no.trainrec.core.data.TrainingRecord;
import no.trainrec.core.domain.ExerciseEntry;
import no.trainrec.core.use_case.EntryAdder;
import no.trainrec.storage.FileIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.mockito.Mockito;

public class PresenterTest {
    private TrainingRecord rec;
    private EntryAdder adder;
    private Presenter presenter;

    @BeforeEach
    public void setUp() {
        presenter = new Presenter(Mockito.mock(FileIO.class));
        rec = Mockito.mock(TrainingRecord.class);
        adder = Mockito.mock(EntryAdder.class);
        presenter.setEntryAdder(adder);
        presenter.setTrainingRecord(rec);
    }
    
    @Test
    public void testAddCallsEntryAdder() {
        presenter.addEntry("Squat");
        Mockito.verify(adder).addEntry("Squat");
    }

    @Test
    public void testListEntries() {
        ExerciseEntry entry = Mockito.mock(ExerciseEntry.class);
        Mockito.when(entry.getDate()).thenReturn("2020-10-01");
        Mockito.when(entry.getExercise()).thenReturn("Squat");
        List<ExerciseEntry> entryList = new ArrayList<ExerciseEntry>();
        entryList.add(entry);
        Mockito.when(rec.listEntries()).thenReturn(entryList);

        List<String> entries = presenter.listEntries();

        Assertions.assertEquals(1, entries.size());
        Assertions.assertEquals("2020-10-01 Squat", entries.get(0));
    }
}
