package com.example.sqldemo3;

import androidx.appcompat.app.AppCompatActivity;

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

    Button btn_view;
    Button btn_add;
    Button btn_edit;
    Button btn_delete;
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

        btn_view = findViewById(R.id.btn_ViewAll);
        btn_add = findViewById(R.id.btn_Add);
        btn_edit = findViewById(R.id.btn_editButton);
        btn_delete = findViewById(R.id.btn_delete);
        et_comment = findViewById(R.id.et_Comment);
        et_money = findViewById(R.id.et_moneyPrice);
        lv_comment = findViewById(R.id.lv_CommentList);
        tv_id = findViewById(R.id.tv_idHidden);
        db = new DatabaseHelper(MainActivity.this);

       tv_id.setVisibility(View.GONE);

        showCommentListView(db);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                //List<CommentModel> commentModelList = db.getAllComments();
                //Toast.makeText(MainActivity.this, commentModelList.toString(), Toast.LENGTH_SHORT).show();
                showCommentListView(db);

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentModel commentModel;
                try{
                     commentModel = new CommentModel(-1, et_comment.getText().toString(), Double.parseDouble(et_money.getText().toString()));
                    Toast.makeText(MainActivity.this, commentModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                    commentModel = new CommentModel(-1, "error",0);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean b = databaseHelper.addOne(commentModel);
                showCommentListView(databaseHelper);
                // Toast.makeText(MainActivity.this, "test" + b, Toast.LENGTH_SHORT).show();
            }
        });


        lv_comment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CommentModel commentModel = (CommentModel) adapterView.getItemAtPosition(i);
              //  db.deleteOne(commentModel);

                Cursor c = db.editOne(commentModel);

                c.moveToFirst();
                int commentInt = c.getInt(0);
                String comment = c.getString(1);
                double money = c.getDouble(2);

                tv_id.setText(String.valueOf(commentInt));
                et_comment.setText(comment);
                et_money.setText(String.valueOf(money));


                showCommentListView(db);
                Toast.makeText(MainActivity.this, "delete record " + commentModel.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
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
                boolean b = databaseHelper.editOneDo(commentModel);
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
                boolean b = databaseHelper.deleteOne(commentModel);
                showCommentListView(databaseHelper);

            }
        });

    }

    private void showCommentListView(DatabaseHelper db) {
        commentArrayAdapter = new ArrayAdapter<CommentModel>(MainActivity.this, android.R.layout.simple_list_item_1, db.getAllComments());
        lv_comment.setAdapter(commentArrayAdapter);
    }




}