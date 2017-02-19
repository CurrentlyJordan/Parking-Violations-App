package nyc.c4q.jordansmith.finefree.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.jordansmith.finefree.model.Car;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by dannylui on 2/18/17.
 */

public class SqlHelper {
    public static List<Car> selectAllCars(SQLiteDatabase db) {
        List<Car> prisoners = new ArrayList<>();
        try {
            QueryResultIterable<Car> itr = cupboard().withDatabase(db).query(Car.class).query();
            for (Car car : itr) {
                prisoners.add(car);
            }
            itr.close();
        } catch (Exception e) {
            Log.e("CellBlock", "selectAllCats: ", e);
        }
        return prisoners;
    }
}
