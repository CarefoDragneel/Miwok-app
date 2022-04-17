package android.example.miwokapp;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment {

    MediaPlayer mediaPlayer;

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

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
        WordAdapter number_adapter = new WordAdapter(getActivity(), adapter,R.color.color_phrases);

//  Initialising the listview
        ListView list_view = (ListView) rootView.findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(number_adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wrd = adapter.get(position);
                releaseMediaPlayer();

                AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                        .setAcceptsDelayedFocusGain(true)
                        .setOnAudioFocusChangeListener(audioFocusChangeListener)
                        .build();

                int res = audioManager.requestAudioFocus(focusRequest);
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(getActivity(),wrd.getAudioResource());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}
