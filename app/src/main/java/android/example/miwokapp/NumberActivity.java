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
        ArrayList<Word> numbers = new ArrayList<Word>();

//  Storing values in the array list
        numbers.add(new Word("lutti","one"));
        numbers.add(new Word("otiko","two"));
        numbers.add(new Word("tolookosu","three"));
        numbers.add(new Word("oyyisa","four"));
        numbers.add(new Word("massokka","five"));
        numbers.add(new Word("temmokka","six"));
        numbers.add(new Word("kenekaku","seven"));
        numbers.add(new Word("kawinta","eight"));
        numbers.add(new Word("wo’e","nine"));
        numbers.add(new Word("na’aacha","ten"));

//  Defining the ArrayAdapter object
        WordAdapter number_adapter = new WordAdapter(this, numbers);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.number_list);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(number_adapter);
    }
}