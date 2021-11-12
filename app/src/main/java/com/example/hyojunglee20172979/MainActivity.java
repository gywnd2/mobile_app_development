package com.example.hyojunglee20172979;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hyojunglee20172979.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Movie Information");
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table groupTBL (mName char(30), mDirector char(20), mYear char(20));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists groupTBL;");
            onCreate(db);
        }
    }

    public void initializeDB(View view) {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        myHelper.onUpgrade(sqlDB, 1, 2);
        sqlDB.close();
        Toast.makeText(getApplicationContext(), "Initialized", Toast.LENGTH_LONG).show();
        ;
    }

    public void insertDB(View view) {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        sqlDB.execSQL("insert into groupTBL values('" + binding.textinputMovietitle.getText().toString() + "','" + binding.textinputDirector.getText().toString() + "','" + binding.textinputReleasedyear.getText().toString() + "');");
        sqlDB.close();
        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
    }

    public void searchDB(View view) {
        myDBHelper myHelper = new myDBHelper(this);
        SQLiteDatabase sqlDB = myHelper.getWritableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from groupTBL;", null);

        String string1 = "Movie Title" + System.lineSeparator();
        String string2 = "Director" + System.lineSeparator();
        String string3 = "Released Year" + System.lineSeparator();

        string1 += "-----------" + System.lineSeparator();
        string2 += "-----------" + System.lineSeparator();
        string3 += "-----------" + System.lineSeparator();

        while (cursor.moveToNext()) {
            string1 += cursor.getString(0) + System.lineSeparator();
            string2 += cursor.getString(1) + System.lineSeparator();
            string3 += cursor.getString(2) + System.lineSeparator();
        }

        binding.textTitleColumnOne.setText(string1);
        binding.textTitleColumnTwo.setText(string2);
        binding.textTitleColumnThree.setText(string3);

        cursor.close();
        sqlDB.close();
    }
}