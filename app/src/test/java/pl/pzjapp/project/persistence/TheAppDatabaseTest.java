package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Room;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import androidx.test.core.app.ApplicationProvider;
import pl.pzjapp.project.persistence.dao.DaoAccess;
import pl.pzjapp.project.persistence.model.City;
import pl.pzjapp.project.util.LiveDataTestUtil;

import static org.junit.Assert.assertTrue;

public class TheAppDatabaseTest {


    private TheAppDatabase mDatabase;
    private DaoAccess notesDao;

    @Before
    public void initDb() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                TheAppDatabase.class)
                .build();
        Log.d("TEST", mDatabase.toString());
        notesDao = mDatabase.daoAccess();
    }

    @After
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void onFetchingNotes_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List<City> noteList = LiveDataTestUtil.getValue(notesDao.getAllCities());
        assertTrue(noteList.isEmpty());
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
//        Note note = FakeNotesSource.fetchFakeNote();
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