package com.example.sqldemo3;

import static android.app.DownloadManager.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String COLUMN_COMMENT = "COMMENT";
    public static final String COMMENT_TABLE = COLUMN_COMMENT + "_TABLE";
    public static final String COLUMN_MONEY = "MONEY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "comment.db", null, 1);
    }

    //FIrst time called when creating a new database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement= "CREATE TABLE " + COMMENT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMMENT + " TEXT, " + COLUMN_MONEY + " DOUBLE)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    //Database version number change. It prevents pre user
    //app from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CommentModel commentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMMENT, commentModel.getComment());
        contentValues.put(COLUMN_MONEY, commentModel.getMoney());
        long insert = db.insert(COMMENT_TABLE, null, contentValues);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<CommentModel> getAllComments(){
        List<CommentModel> returnCommentList = new ArrayList<>();
        String getAllValuesFromCommentTable = "SELECT * FROM " + COMMENT_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(getAllValuesFromCommentTable, null);

        if(cursor.moveToFirst()){

            do{
                int commentInt = cursor.getInt(0);
                String comment = cursor.getString(1);
                double money = cursor.getDouble(2);

                CommentModel commentModel = new CommentModel(commentInt, comment, money);
                returnCommentList.add(commentModel);

            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to list
        }

        cursor.close();
        sqLiteDatabase.close();
        return returnCommentList;
    }

    public boolean deleteOne(CommentModel commentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteComment = "DELETE FROM " + COMMENT_TABLE + " WHERE " + " id " + " = " + commentModel.getId();

        Cursor cursor = db.rawQuery(deleteComment, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }



    }


    public Cursor editOne(CommentModel commentModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectOneComment = "SELECT * FROM " + COMMENT_TABLE + " WHERE " + " id " + " = " + commentModel.getId();

        Cursor cursor = db.rawQuery(selectOneComment, null);


        return cursor;
        //return cursor.m

//        if(cursor.moveToFirst()){
//            return true;
//        }else{
//            return false;
//        }



    }

    public boolean editOneDo(CommentModel commentModel){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMMENT, commentModel.getComment());
        contentValues.put(COLUMN_MONEY, commentModel.getMoney());
        int test = db.update(COMMENT_TABLE, contentValues, "id= " + commentModel.getId(), null);

        if(test==0){
            return true;
        }else{
            return false;
        }



    }


}
