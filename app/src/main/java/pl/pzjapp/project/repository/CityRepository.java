//package pl.pzjapp.project.repository;
//
//import android.arch.persistence.room.Room;
//import android.content.Context;
//
//import io.reactivex.annotations.NonNull;
//import pl.pzjapp.project.TheAppDatabase;
//import pl.pzjapp.project.utils.UtilsForApp;
//
//public class CityRepository {
//    private static final UtilsForApp CONFIG = UtilsForApp.fromTestConfigFile();
//    private static final String DB_NAME = CONFIG.get("db.name");
//
//    private TheAppDatabase theAppDatabase;
//
//    public CityRepository(@NonNull Context context) {
//        theAppDatabase = Room.databaseBuilder(context, theAppDatabase.getClass(), DB_NAME).build();
//    }
//
//
////    public void insertCity(String title,
////                           String description) {
////
////        insertCity(title, description, false, null);
////    }
////
////    public void insertCity(City city) {
////
////        City dbCity = new City.CityBuilder()
////                .setCity()
////                .setCityId()
////                .setCountry()
////                .setDate()
////                .setHumidity()
////                .setIconRef()
////                .setPressure()
////                .setTemperature()
////                .setWindSpeed()
////
////
////        if (encrypt) {
////            city.setPassword(AppUtils.generateHash(password));
////        } else city.setPassword(null);
////
////        insertTask(city);
////    }
////
////    public void insertTask(final Note note) {
////        new AsyncTask<Void, Void, Void>() {
////            @Override
////            protected Void doInBackground(Void... voids) {
////                noteDatabase.daoAccess().insertTask(note);
////                return null;
////            }
////        }.execute();
////    }
//}
