package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Create an ArrayList of words
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti","one"));
        words.add(new Word("otiiko","two"));
        words.add(new Word("tolookosu","three"));
        words.add(new Word("oyyisa","four"));
        words.add(new Word("massokka","five"));
        words.add(new Word("temmokka","six"));
        words.add(new Word("kenekaku","seven"));
        words.add(new Word("kawinta","eight"));
        words.add(new Word("wo' e","nine"));
        words.add(new Word("na' aacha","ten"));

        WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
