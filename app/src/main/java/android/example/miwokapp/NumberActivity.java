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

//    closely look at the syntax on how to create an object reference variable as a global variable
    MediaPlayer.OnCompletionListener completion_listener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

//  we define the object of array list to store values in it
        final ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("lutti","one",R.drawable.number_one,R.raw.audio_number_one));
        adapter.add(new Word("otiko","two",R.drawable.number_two,R.raw.audio_number_two));
        adapter.add(new Word("tolookosu","three",R.drawable.number_three,R.raw.audio_number_three));
        adapter.add(new Word("oyyisa","four",R.drawable.number_four,R.raw.audio_number_four));
        adapter.add(new Word("massokka","five",R.drawable.number_five,R.raw.audio_number_five));
        adapter.add(new Word("temmokka","six",R.drawable.number_six,R.raw.audio_number_six));
        adapter.add(new Word("kenekaku","seven",R.drawable.number_seven,R.raw.audio_number_seven));
        adapter.add(new Word("kawinta","eight",R.drawable.number_eight,R.raw.audio_number_eight));
        adapter.add(new Word("wo’e","nine",R.drawable.number_nine,R.raw.audio_number_nine));
        adapter.add(new Word("na’aacha","ten",R.drawable.number_ten,R.raw.audio_number_ten));

//  Defining the ArrayAdapter object
        WordAdapter number_adapter = new WordAdapter(this, adapter,R.color.color_number);

//  Initialising the listview
        ListView list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(number_adapter);

//        creating event listener for the entire app view
//        @param: for setOnItemClickListener -> is an object of class of the click listener class that defines it.
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

//            @param: parent -> signifies adapter view on which the item click listener is attached to; here, list.xml
//            @param: view -> signifies the view of the items in the list; here, list_item.xml
//            @param: position -> signifies the position reference of the list item which is clicked
//            @param: id -> signifies the id of the list items; generally not used much
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              Here, get(int index) function returns an object of the Element(E) class defined to store the info of each list item
//              it is defined in the class AdapterList
                Word wrd = adapter.get(position);

//                we release the media player before mediaPlayer object is reallocated also as there can be a case when
//                the user clicks on another item without the completion of the current one and thus, leaving the mediaPlayer object unreleased
                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(NumberActivity.this, wrd.getAudioResource());
                mediaPlayer.start();

//                below is not used because whenever we click an item a new MediaPlayer.OnCompletionListener object will
//                be created which will take some space
//                creating a global variable means that we can reuse the object for each item

//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        releaseMediaPlayer();
//                    }
//                });

//                this is an event listener defined in MediaPlayer class
//                @param: takes object reference of MediaPlayer.OnCompletionListener interface
                mediaPlayer.setOnCompletionListener(completion_listener);
            }
        });
    }
//    this method is used to release the MediaPlayer object so that new data can be stored in the object or simply the memory is freed to
//    perform other tasks.
    private void releaseMediaPlayer(){
//        when mediaPlayer is null then that means the sound has stopped playing
        if(mediaPlayer != null)
//            release method is used to free the space from MediaPlayer object and push the life cycle of MediaPlayer
//            towards End (see documentation).
//            this stops the sound being played
//            it is defined in the MediaPlayer class
            mediaPlayer.release();
//        we set value to null so that other memory can be allocated to the mediaPlayer object
        mediaPlayer = null;
    }
}