package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        //  we define the object of array list to store values in it
        ArrayList<Word> colors = new ArrayList<Word>();

//  Storing values in the array list
        colors.add(new Word("weṭeṭṭi","red"));
        colors.add(new Word("chokokki","green"));
        colors.add(new Word("ṭakaakki","brown"));
        colors.add(new Word("ṭopoppi","gray"));
        colors.add(new Word("kululli","black"));
        colors.add(new Word("kelelli","white"));
        colors.add(new Word("ṭopiisә","dusty yellow"));
        colors.add(new Word("chiwiiṭә","mustard yellow"));

//  Defining the ArrayAdapter object
        WordAdapter color_adapter = new WordAdapter(this, colors);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(color_adapter);
    }
}