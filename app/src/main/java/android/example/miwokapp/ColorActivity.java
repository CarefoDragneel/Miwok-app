package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
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
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("weṭeṭṭi","red",R.drawable.color_red,R.raw.audio_color_red));
        adapter.add(new Word("chokokki","green",R.drawable.color_green,R.raw.audio_color_green));
        adapter.add(new Word("ṭakaakki","brown",R.drawable.color_brown,R.raw.audio_color_brown));
        adapter.add(new Word("ṭopoppi","gray",R.drawable.color_gray,R.raw.audio_color_gray));
        adapter.add(new Word("kululli","black",R.drawable.color_black,R.raw.audio_color_black));
        adapter.add(new Word("kelelli","white",R.drawable.color_white,R.raw.audio_color_white));
        adapter.add(new Word("ṭopiisә","dusty yellow",R.drawable.color_dusty_yellow,R.raw.audio_color_dusty_yellow));
        adapter.add(new Word("chiwiiṭә","mustard yellow",R.drawable.color_mustard_yellow,R.raw.audio_color_mustard_yellow));

//  Defining the ArrayAdapter object
        WordAdapter color_adapter = new WordAdapter(this, adapter,R.color.color_color);

//  Initialising the listview
        ListView list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(color_adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wrd = adapter.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(ColorActivity.this,wrd.getAudioResource());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });
    }

    //    releases the resources when the user closes the activity
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = null;
    }
}