package pl.pzjapp.project.persistence;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {
    private Context mCtx;
    private static DatabaseClient mInstance;
    private TheAppDatabase theAppDatabase;

    private DatabaseClient(Context context) {
        this.mCtx = context;
        theAppDatabase = Room.databaseBuilder(mCtx, TheAppDatabase.class, "the-app").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public TheAppDatabase getTheAppDatabase() {
        return theAppDatabase;
    }
}

