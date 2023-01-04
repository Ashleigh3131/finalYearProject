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

        //primary key being autoincrement as an id
        String createTableStatement= "CREATE TABLE " + COMMENT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COMMENT + " TEXT, " + COLUMN_MONEY + " DOUBLE)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    //Database version number change. It prevents pre user
    //app from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // to add a new record and passing in the comment model values such as id, comment and money
    public boolean addOne(CommentModel commentModel){
        //creating sqldatabase and making it writeable as data is being added to it this time
        SQLiteDatabase db = this.getWritableDatabase();

        //Insert wants content values to be added so I am creating it below and assigning the values from the input text boxes to the contentvalues to be inserted
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMMENT, commentModel.getComment());
        contentValues.put(COLUMN_MONEY, commentModel.getMoney());

        //Carrying out the actual insert to comment_table and the values being the input text from users
        long insert = db.insert(COMMENT_TABLE, null, contentValues);

        //The above insert code returns a long, if it is -1 then it means it did not insert correctly so I am returning a boolean based on this.
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<CommentModel> getAllComments(){
        List<CommentModel> returnCommentList = new ArrayList<>();

        //my select * from comment_tables to bring back all my results
        String getAllValuesFromCommentTable = "SELECT * FROM " + COMMENT_TABLE;


        //Making this readable as I am reading the results back in
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        //Carrying out the query and this brings back a cursor
        Cursor cursor = sqLiteDatabase.rawQuery(getAllValuesFromCommentTable, null);

        //Getting the results from the cursor
        if(cursor.moveToFirst()){

            do{
                //assigning the values from the select statement to the commentModel
                int commentInt = cursor.getInt(0);
                String comment = cursor.getString(1);
                double money = cursor.getDouble(2);

                //commentModel is my class that has the getting and setters and constructors so I can easily build my comment model
                CommentModel commentModel = new CommentModel(commentInt, comment, money);
                returnCommentList.add(commentModel);

            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to list
        }

        cursor.close();
        sqLiteDatabase.close();

        //returning the results
        return returnCommentList;
    }

    public boolean deleteOne(CommentModel commentModel){

        //Making it writiable as we are deleting from it
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating the delete statement based on the id that the user selected and was added to the commentmodel
        String deleteComment = "DELETE FROM " + COMMENT_TABLE + " WHERE " + " id " + " = " + commentModel.getId();

        //carrying out the delete statement
        Cursor cursor = db.rawQuery(deleteComment, null);


        //returning true if it worked
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }



    }


    public Cursor getResultsOfOneId(CommentModel commentModel){
        //This is the writeable as we are getting one results back based on id.
        SQLiteDatabase db = this.getWritableDatabase();

        //Making the select statement for it
        String selectOneComment = "SELECT * FROM " + COMMENT_TABLE + " WHERE " + " id " + " = " + commentModel.getId();

        //Calling the actual database to make the call and returning cursor
        Cursor cursor = db.rawQuery(selectOneComment, null);

        return cursor;

    }

    public boolean editOneDo(CommentModel commentModel){
        //Making it writable cause it is writing to the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Update statment requires contentValue as input so creating that below from the input text view
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMMENT, commentModel.getComment());
        contentValues.put(COLUMN_MONEY, commentModel.getMoney());
        
        //using the update method from sqlLiteDatabase with comment_table being the table and where clause being the id
        int editOne = db.update(COMMENT_TABLE, contentValues, "id= " + commentModel.getId(), null);

        //If sucessful the returns true, otherwise false
        if(editOne==0){
            return true;
        }else{
            return false;
        }

    }


}
