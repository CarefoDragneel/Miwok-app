package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasePlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
//  we define the object of array list to store values in it
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("epe","father",R.drawable.family_father,R.raw.audio_family_father));
        adapter.add(new Word("eta","mother",R.drawable.family_mother,R.raw.audio_family_mother));
        adapter.add(new Word("angsi","son",R.drawable.family_son,R.raw.audio_family_son));
        adapter.add(new Word("tune","daughter",R.drawable.family_daughter,R.raw.audio_family_daughter));
        adapter.add(new Word("taachi","older brother",R.drawable.family_older_brother,R.raw.audio_family_older_brother));
        adapter.add(new Word("chalitti","younger brother",R.drawable.family_younger_brother,R.raw.audio_family_younger_brother));
        adapter.add(new Word("tete","older sister",R.drawable.family_older_sister,R.raw.audio_family_older_sister));
        adapter.add(new Word("killiti","younger sister",R.drawable.family_younger_sister,R.raw.audio_family_younger_sister));
        adapter.add(new Word("ama","grandmother",R.drawable.family_grandmother,R.raw.audio_family_grandmother));
        adapter.add(new Word("paapa","grandfather",R.drawable.family_grandfather,R.raw.audio_family_grandfather));

//  Defining the ArrayAdapter object
        WordAdapter family_adapter = new WordAdapter(this, adapter,R.color.color_family);

//  Initialising the listview
        ListView list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(family_adapter);

//        setting the item click listener for family activity
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wrd = adapter.get(position);
                releasePlayer();
                mediaPlayer = MediaPlayer.create(FamilyActivity.this,wrd.getAudioResource());
                mediaPlayer.start();
//                releasing the mediaPlayer object
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });

    }
    private void releasePlayer(){
        if(mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = null;
    }
}        
        