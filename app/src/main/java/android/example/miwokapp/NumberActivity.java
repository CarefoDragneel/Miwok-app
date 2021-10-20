package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

//  we define the object of array list to store values in it
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("lutti","one"));
        adapter.add(new Word("otiko","two"));
        adapter.add(new Word("tolookosu","three"));
        adapter.add(new Word("oyyisa","four"));
        adapter.add(new Word("massokka","five"));
        adapter.add(new Word("temmokka","six"));
        adapter.add(new Word("kenekaku","seven"));
        adapter.add(new Word("kawinta","eight"));
        adapter.add(new Word("wo’e","nine"));
        adapter.add(new Word("na’aacha","ten"));

//  Defining the ArrayAdapter object
        WordAdapter number_adapter = new WordAdapter(this, adapter);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(number_adapter);
    }
}