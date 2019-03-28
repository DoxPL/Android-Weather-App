package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Room;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.test.core.app.ApplicationProvider;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.persistence.repository.CityRepository;

@RunWith(RobolectricTestRunner.class)
public class TheAppDatabaseTest {


    private TheAppDatabase mDatabase;
    private CityRepository cityRepository;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.databaseBuilder(ApplicationProvider.getApplicationContext(), TheAppDatabase.class, "the-app").build();
        cityRepository = new CityRepository(ApplicationProvider.getApplicationContext());
        populateWithTestData();
        Log.d("TEST", mDatabase.toString());
        for(City c : cityRepository.getAllCities())
        {
            System.out.println(c.toString());
        }
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void onFetchingNotes_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        cityRepository.getAllCities();
    }

    private City addCity(City city) {
        cityRepository.insertCity(city);
        return city;
    }

    private void populateWithTestData() {
        City city = new City();
        city.setCityName("Ajay");
        city.setCountry("Saini");
        city.setId(25);
        addCity(city);
    }


//    @Test
//    public void onInsertingNotes_checkIf_RowCountIsCorrect() throws InterruptedException {
//        List<Note> noteList = FakeNotesSource.getFakeNotes(5);
//        noteList.forEach(note -> {
//            notesDao.insert(note);
//        });
//        assertEquals(5, LiveDataTestUtil.getValue(notesDao.getAllNotes()).size());
//    }
//
//    @Test
//    public void onUpdatingANote_checkIf_UpdateHappensCorrectly() throws InterruptedException {
//        Note note = FakeNotesSource.fetchFakeNote()
//        notesDao.insert(note);
//        note.setNoteTitle(FakeNotesSource.FAKE_NOTE_UPDATED_TITLE);
//        notesDao.update(note);
//        assertEquals(1, LiveDataTestUtil.getValue(notesDao.getAllNotes()).size());
//        assertEquals(FakeNotesSource.FAKE_NOTE_UPDATED_TITLE,
//                LiveDataTestUtil.getValue(notesDao.getNoteById(note.getId())).getNoteTitle());
//    }
//
//    @Test
//    public void onNoteDeletion_CheckIf_NoteIsDeletedFromTable() throws InterruptedException {
//        List<Note> noteList = FakeNotesSource.getFakeNotes(5);
//        noteList.forEach(note -> {
//            notesDao.insert(note);
//        });
//        notesDao.deleteNote(noteList.get(2));
//        assertNull(LiveDataTestUtil.getValue(notesDao.getNoteById(noteList.get(2).getId())));
//    }
}