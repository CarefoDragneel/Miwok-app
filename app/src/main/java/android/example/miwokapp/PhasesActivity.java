package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhasesActivity extends AppCompatActivity {

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
        adapter.add(new Word("minto wuksus","Where are you going?",R.raw.audio_phrase_where_are_you_going));
        adapter.add(new Word("tinnә oyaase'nә","What is your name?",R.raw.audio_phrase_what_is_your_name));
        adapter.add(new Word("oyaaset...","My name is...",R.raw.audio_phrase_my_name_is));
        adapter.add(new Word("michәksәs?","How are you feeling?",R.raw.audio_phrase_how_are_you_feeling));
        adapter.add(new Word("kuchi achit","I’m feeling good",R.raw.audio_phrase_im_feeling_good));
        adapter.add(new Word("әәnәs'aa?","Are you coming?",R.raw.audio_phrase_are_you_coming));
        adapter.add(new Word("hәә’ әәnәm","Yes, I’m coming.",R.raw.audio_phrase_yes_im_coming));
        adapter.add(new Word("әәnәm","I’m coming.",R.raw.audio_phrase_im_coming));
        adapter.add(new Word("yoowutis","Let’s go.",R.raw.audio_phrase_lets_go));
        adapter.add(new Word("әnni'nem","Come here.",R.raw.audio_phrase_come_here));

//  Defining the ArrayAdapter object
        WordAdapter number_adapter = new WordAdapter(this, adapter,R.color.color_phrases);

//  Initialising the listview
        ListView list_view = (ListView) findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(number_adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wrd = adapter.get(position);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(PhasesActivity.this,wrd.getAudioResource());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });
    }


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