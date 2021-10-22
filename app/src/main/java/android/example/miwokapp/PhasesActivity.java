package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
//  we define the object of array list to store values in it
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("minto wuksus","Where are you going?"));
        adapter.add(new Word("tinnә oyaase'nә","What is your name?"));
        adapter.add(new Word("oyaaset...","My name is..."));
        adapter.add(new Word("michәksәs?","How are you feeling?"));
        adapter.add(new Word("kuchi achit","I’m feeling good"));
        adapter.add(new Word("әәnәs'aa?","Are you coming?"));
        adapter.add(new Word("hәә’ әәnәm","Yes, I’m coming."));
        adapter.add(new Word("әәnәm","I’m coming."));
        adapter.add(new Word("yoowutis","Let’s go."));
        adapter.add(new Word("әnni'nem","Come here."));

//  Defining the ArrayAdapter object
        WordAdapter number_adapter = new WordAdapter(this, adapter,R.color.color_phrases);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(number_adapter);
    }
}