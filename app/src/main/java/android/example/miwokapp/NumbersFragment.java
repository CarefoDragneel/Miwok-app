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

public class NumbersFragment extends Fragment {

    //    HERE THESE ARE ALL GLOBAL VARIABLES

    MediaPlayer mediaPlayer;

    //    This is to set a global reference variable so that we can use its methods easily in all methods
    private AudioManager audioManager;

    //    closely look at the syntax on how to create an object reference variable as a global variable
//    we are defining an event listener which denotes when the audio file has completed playing
//    this is to provide asynchronicity to the audio player so that we can perform other tasks for eg - maybe some other functionality
//    of the app (another app)
    MediaPlayer.OnCompletionListener completion_listener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    //    here we define an event listener which tells us when there is a transition in audio focus in our audio app
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

        //      This is used to create an object to manage audio focus
//        Here, we see that Audio Manager is System Service
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter number_adapter = new WordAdapter(getActivity(), adapter,R.color.color_number);

//  Initialising the listview
        ListView list_view = (ListView) rootView.findViewById(R.id.list_view);

//  connecting listview with ArrayAdapter
        list_view.setAdapter(number_adapter);

//        creating event listener for the entire app view
//        @param: for setOnItemClickListener -> is an object of class of the click listener class that defines it.
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            //            @param: parent -> signifies adapter view on which the item click listener is attached to; here, list.xml
//            @param: view -> signifies the view of the items in the list; here, list_item.xml
//            @param: position -> signifies the position reference of the list item which is clicked
//            @param: id -> signifies the id of the list items; generally not used much
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              Here, get(int index) function returns an object of the Element(E) class defined to store the info of each list item
//              it is defined in the class AdapterList
                Word wrd = adapter.get(position);

//                we release the media player before mediaPlayer object is reallocated also as there can be a case when
//                the user clicks on another item without the completion of the current one and thus, leaving the mediaPlayer object unreleased
                releaseMediaPlayer();

//              here we create an object of AudioFocusRequest class
//                In this object we specify the stream type and the Audio Focus listener which we did earlier in the depricated
//                requestAudioFocus method
//                Along with all of that we define some new things too: (read documentation)
                AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                        .setAcceptsDelayedFocusGain(true)
                        .setOnAudioFocusChangeListener(audioFocusChangeListener)
                        .build();

//                Here we are requesting audio focus from other apps
//                Since the requestAudioFocus method used in the udacity class is depriciated so we use this one
//                @param: object of AudioFocusRequest class
                int res = audioManager.requestAudioFocus(focusRequest);

//                audio focus returns an int value which has fixed values which are given in the AudioManager class
//                so we create these if statement to check if we are granted the audio focus or not
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

//                    this case means that app is granted the audio focus; So we use the media player object to run the audio
                    mediaPlayer = MediaPlayer.create(getActivity(), wrd.getAudioResource());
                    mediaPlayer.start();

//                this is an event listener defined in MediaPlayer class
//                @param: takes object reference of MediaPlayer.OnCompletionListener interface
                    mediaPlayer.setOnCompletionListener(completion_listener);
                }


//                below is not used because whenever we click an item a new MediaPlayer.OnCompletionListener object will
//                be created which will take some space
//                creating a global variable means that we can reuse the object for each item

//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        releaseMediaPlayer();
//                    }
//                });

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    //    THIS IS A METHOD TO RELEASE THE MEDIA RESOURCES
//    this method is used to release the MediaPlayer object so that new data can be stored in the object or simply the memory is freed to
//    perform other tasks.
    private void releaseMediaPlayer(){
//        when mediaPlayer is null then that means the sound has stopped playing
        if(mediaPlayer != null) {
//            release method is used to free the space from MediaPlayer object and push the life cycle of MediaPlayer
//            towards End (see documentation).
//            this stops the sound being played
//            it is defined in the MediaPlayer class
            mediaPlayer.release();
//        we set value to null so that other memory can be allocated to the mediaPlayer object
            mediaPlayer = null;

            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}
