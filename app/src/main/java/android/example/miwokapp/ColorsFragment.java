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

public class ColorsFragment extends Fragment {

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

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //  we define the object of array list to store values in it
        ArrayList<Word> adapter = new ArrayList<Word>();

//  Storing values in the array list
        adapter.add(new Word("weṭeṭṭi", "red", R.drawable.color_red, R.raw.audio_color_red));
        adapter.add(new Word("chokokki", "green", R.drawable.color_green, R.raw.audio_color_green));
        adapter.add(new Word("ṭakaakki", "brown", R.drawable.color_brown, R.raw.audio_color_brown));
        adapter.add(new Word("ṭopoppi", "gray", R.drawable.color_gray, R.raw.audio_color_gray));
        adapter.add(new Word("kululli", "black", R.drawable.color_black, R.raw.audio_color_black));
        adapter.add(new Word("kelelli", "white", R.drawable.color_white, R.raw.audio_color_white));
        adapter.add(new Word("ṭopiisә", "dusty yellow", R.drawable.color_dusty_yellow, R.raw.audio_color_dusty_yellow));
        adapter.add(new Word("chiwiiṭә", "mustard yellow", R.drawable.color_mustard_yellow, R.raw.audio_color_mustard_yellow));

//  Defining the ArrayAdapter object
        WordAdapter color_adapter = new WordAdapter(getActivity(), adapter, R.color.color_color);

//  Initialising the listview
        ListView list_view = (ListView) rootView.findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(color_adapter);

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
                    mediaPlayer = MediaPlayer.create(getActivity(), wrd.getAudioResource());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });

        return rootView;
    }

    //    releases the resources when the user closes the activity
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
