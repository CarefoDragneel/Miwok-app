package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

//  we define the object of array list to store values in it
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("lutti","one",R.drawable.number_one));
        adapter.add(new Word("otiko","two",R.drawable.number_two));
        adapter.add(new Word("tolookosu","three",R.drawable.number_three));
        adapter.add(new Word("oyyisa","four",R.drawable.number_four));
        adapter.add(new Word("massokka","five",R.drawable.number_five));
        adapter.add(new Word("temmokka","six",R.drawable.number_six));
        adapter.add(new Word("kenekaku","seven",R.drawable.number_seven));
        adapter.add(new Word("kawinta","eight",R.drawable.number_eight));
        adapter.add(new Word("wo’e","nine",R.drawable.number_nine));
        adapter.add(new Word("na’aacha","ten",R.drawable.number_ten));

//  Defining the ArrayAdapter object
        WordAdapter number_adapter = new WordAdapter(this, adapter,R.color.color_number);

//  Initialising the listview
        ListView list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(number_adapter);

//        creating event listener for the entire app view
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @parent: signifies adapter view on which the item click listener is attached to; here, list.xml
//            @view: signifies the view of the items in the list; here, list_item.xml
//            @position: signifies the position reference of the list item
//            @id: signifies the id of the list items; generally not used much

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaPlayer = MediaPlayer.create(NumberActivity.this,R.raw.audio_number_one);
                mediaPlayer.start();
            }
        });
    }
}