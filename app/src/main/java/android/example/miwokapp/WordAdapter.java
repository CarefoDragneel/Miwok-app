package android.example.miwokapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter (Activity context, ArrayList<Word> wordList){
        super(context,0,wordList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View listview = convertView;
            if(listview == null){
                listview = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
            }
//  we get the Word object which is present at this position in the list
            Word currentWord = getItem(position);

//  We are adding layout elements which will be shown as list_items layout
//  adding miwok words in the textView
            TextView miwok_textview = (TextView) listview.findViewById(R.id.miwok_text);
//  Setting text from the list in the textView
            miwok_textview.setText(currentWord.getMiwokNumbers());
//  adding default words in the textView
            TextView default_textView = (TextView) listview.findViewById(R.id.default_text);
//  Setting translation from the list in the textView
            default_textView.setText(currentWord.getDefaultNumbers());
//  we return the new view that will be shown by the ArrayAdapter class
            return listview;
    }
}