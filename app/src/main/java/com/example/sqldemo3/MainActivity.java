package com.example.sqldemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //creating the objects such as buttons and editviews and adding a name to them
    Button btn_view;
    Button btn_add;
    Button btn_edit;
    Button btn_delete;
    Button btn_toWeb;
    EditText et_comment;
    EditText et_money;
    TextView tv_id;
    ListView lv_comment;
    ArrayAdapter commentArrayAdapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking them to the xml file e.g. btn_ViewAll is the name in the xml file
        btn_view = findViewById(R.id.btn_ViewAll);
        btn_add = findViewById(R.id.btn_Add);
        btn_edit = findViewById(R.id.btn_editButton);
        btn_delete = findViewById(R.id.btn_delete);
        btn_toWeb = findViewById(R.id.btn_TakeToWeb);
        et_comment = findViewById(R.id.et_Comment);
        et_money = findViewById(R.id.et_moneyPrice);
        lv_comment = findViewById(R.id.lv_CommentList);
        tv_id = findViewById(R.id.tv_idHidden);
        db = new DatabaseHelper(MainActivity.this);

       tv_id.setVisibility(View.GONE);

        showCommentListView(db);


        //creating logic to show all the values in listview when view button has been created
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper db = new DatabaseHelper(MainActivity.this);

                //calling the method to get all the comments
                showCommentListView(db);

            }
        });


        //to add a new record when button is clicked
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a new comment model and getting the values from the input fields, as you can see that money is a double and I am parsing it to a string
                CommentModel commentModel;

                //the catch is there so a default value will be entered as error as comment and money is 0.0 as showed in the demo
                try{
                     commentModel = new CommentModel(-1, et_comment.getText().toString(), Double.parseDouble(et_money.getText().toString()));
                    Toast.makeText(MainActivity.this, commentModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                    commentModel = new CommentModel(-1, "error",0);
                }
                //creating the databaseHelper class to allow me to call in my add one method and assigning it to this main activity
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                //calling my addOne method to add the new record.
                boolean b = databaseHelper.addOne(commentModel);

                //The below is calling the select * from comment_table to show the newly added record.
                showCommentListView(databaseHelper);
            }
        });

        //clicking on one of the listview
        lv_comment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //This is getting the information of the one that the user selected
                CommentModel commentModel = (CommentModel) adapterView.getItemAtPosition(i);

                //This is calling the database for a select * from comment_table based on id
                Cursor c = db.getResultsOfOneId(commentModel);
                //assiging the cursor results to the comment model and the input objects e.g. assigning the comment to the textview comment box
                c.moveToFirst();
                int commentInt = c.getInt(0);
                String comment = c.getString(1);
                double money = c.getDouble(2);

                tv_id.setText(String.valueOf(commentInt));
                et_comment.setText(comment);
                et_money.setText(String.valueOf(money));


                showCommentListView(db);

            }
        });


        //editing a record
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentModel commentModel;

                //creating the comment model and getting the id, comment and model from the input values
                //Created a hidden textview box to get the id which user has no access to, nor can see
                //Can see that I am pasring int and double for money and id

                try{
                    commentModel = new CommentModel(Integer.parseInt(tv_id.getText().toString()), et_comment.getText().toString(), Double.parseDouble(et_money.getText().toString()));
                    Toast.makeText(MainActivity.this, commentModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                    commentModel = new CommentModel(-1, "error",0);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                //Creating the databaseHelper call to call in the edit one record method
                boolean b = databaseHelper.editOneDo(commentModel);

                //showing the new edit record being added into the view
                showCommentListView(databaseHelper);

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentModel commentModel;
                try{
                    commentModel = new CommentModel(Integer.parseInt(tv_id.getText().toString()), et_comment.getText().toString(), Double.parseDouble(et_money.getText().toString()));
                    Toast.makeText(MainActivity.this, commentModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                    commentModel = new CommentModel(-1, "error",0);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                //Calling the delete one method
                boolean b = databaseHelper.deleteOne(commentModel);

                //Showing all the records in which you can see that the record has been deleted
                showCommentListView(databaseHelper);

            }
        });


        //this is to take you to the new activity when clicked on
        btn_toWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Taking you from main activity to mainactivity 2
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

    }

    private void showCommentListView(DatabaseHelper db) {

        //setting a new arrayAdapter to the main activity and to the list view with the results of getAllComments which is the select * from comment_table
        commentArrayAdapter = new ArrayAdapter<CommentModel>(MainActivity.this, android.R.layout.simple_list_item_1, db.getAllComments());
        //setting the results got from commentArrayAdapter
        lv_comment.setAdapter(commentArrayAdapter);
    }


}