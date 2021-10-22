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
        colors.add(new Word("weṭeṭṭi","red",R.drawable.color_red));
        colors.add(new Word("chokokki","green",R.drawable.color_green));
        colors.add(new Word("ṭakaakki","brown",R.drawable.color_brown));
        colors.add(new Word("ṭopoppi","gray",R.drawable.color_gray));
        colors.add(new Word("kululli","black",R.drawable.color_black));
        colors.add(new Word("kelelli","white",R.drawable.color_white));
        colors.add(new Word("ṭopiisә","dusty yellow",R.drawable.color_dusty_yellow));
        colors.add(new Word("chiwiiṭә","mustard yellow",R.drawable.color_mustard_yellow));

//  Defining the ArrayAdapter object
        WordAdapter color_adapter = new WordAdapter(this, colors);

//  Initialising the listview
        ListView number_list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        number_list_view.setAdapter(color_adapter);
    }
}