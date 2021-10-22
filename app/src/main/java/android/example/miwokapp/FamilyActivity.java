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
        adapter.add(new Word("epe","father",R.drawable.family_father));
        adapter.add(new Word("eta","mother",R.drawable.family_mother));
        adapter.add(new Word("angsi","son",R.drawable.family_son));
        adapter.add(new Word("tune","daughter",R.drawable.family_daughter));
        adapter.add(new Word("taachi","older brother",R.drawable.family_older_brother));
        adapter.add(new Word("chalitti","younger brother",R.drawable.family_younger_brother));
        adapter.add(new Word("tete","older sister",R.drawable.family_older_sister));
        adapter.add(new Word("killiti","younger sister",R.drawable.family_younger_sister));
        adapter.add(new Word("ama","grandmother",R.drawable.family_grandmother));
        adapter.add(new Word("paapa","grandfather",R.drawable.family_grandfather));

//  Defining the ArrayAdapter object
        WordAdapter family_adapter = new WordAdapter(this, adapter);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(family_adapter);
    }
}        
        