package android.example.miwokapp;

import android.content.Context;
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


public class FamilyFragment extends Fragment {

    MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

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

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
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
        WordAdapter family_adapter = new WordAdapter(getActivity(), adapter,R.color.color_family);

//  Initialising the listview
        ListView list_view = (ListView) rootView.findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(family_adapter);

//        setting the item click listener for family activity
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wrd = adapter.get(position);
                releaseMediaPlayer();
//                AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
//                        .setAcceptsDelayedFocusGain(true)
//                        .setOnAudioFocusChangeListener(audioFocusChangeListener)
//                        .build();
                int res = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

//                    this case means that app is granted the audio focus; So we use the media player object to run the audio
                    mediaPlayer = MediaPlayer.create(getActivity(), wrd.getAudioResource());
                    mediaPlayer.start();

//                this is an event listener defined in MediaPlayer class
//                @param: takes object reference of MediaPlayer.OnCompletionListener interface
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
