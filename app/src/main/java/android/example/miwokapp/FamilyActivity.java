package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
//  we define the object of array list to store values in it
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("epe","father"));
        adapter.add(new Word("eta","mother"));
        adapter.add(new Word("angsi","son"));
        adapter.add(new Word("tune","daughter"));
        adapter.add(new Word("taachi","older brother"));
        adapter.add(new Word("chalitti","younger brother"));
        adapter.add(new Word("tete","older sister"));
        adapter.add(new Word("killiti","younger sister"));
        adapter.add(new Word("ama","grandmother"));
        adapter.add(new Word("paapa","grandfather"));

//  Defining the ArrayAdapter object
        WordAdapter family_adapter = new WordAdapter(this, adapter);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(family_adapter);
    }
}        
        