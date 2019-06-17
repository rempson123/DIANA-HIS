package company.geodata.diana.Repository;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jcmate on 9/4/2017.
 */

public class Repository {
    protected SQLiteDBContext dbContext;
    protected SQLiteDatabase database;

    protected Repository(Context context){
        dbContext = new SQLiteDBContext(context);
    }

    public void open() throws SQLException {
        try {
            database = dbContext.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close()
    {
        dbContext.close();
        database.close();
    }
}

