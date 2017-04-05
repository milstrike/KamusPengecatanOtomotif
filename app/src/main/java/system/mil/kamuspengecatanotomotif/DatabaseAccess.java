package system.mil.kamuspengecatanotomotif;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milstrike on 23/02/2016.
 */
public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    //Private Constructor to avoid object creation from outside classes

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //return a singleton instance of Database Access
    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //Open Database Connection
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public List<String> getKamusOtomotif(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM db_otomotif", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public List<String> getSearchOtomotif(String keyword){
        List<String> list = new ArrayList<>();
        String queryString = "SELECT * FROM db_otomotif WHERE words LIKE '" + keyword +"%'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public String getSelectedOtomotif(String kataOtomotif){
        String queryString = "SELECT * FROM db_otomotif WHERE words='"+ kataOtomotif +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(2);
        cursor.close();
        return arti;
    }

    public String getSelectedOtomotif2(String kataOtomotif){
        String queryString = "SELECT * FROM db_otomotif WHERE words='"+ kataOtomotif +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(3);
        cursor.close();
        return arti;
    }

    public String getSelectedOtomotif3(String kataOtomotif){
        String queryString = "SELECT * FROM db_otomotif WHERE words='"+ kataOtomotif +"'";
        Cursor cursor = database.rawQuery(queryString, null);
        cursor.moveToFirst();
        String arti = cursor.getString(1);
        cursor.close();
        return arti;
    }

}
