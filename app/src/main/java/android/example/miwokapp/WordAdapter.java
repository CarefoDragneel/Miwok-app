package android.example.miwokapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

// We are overloading the ArrayAdapter<Word> because in here getView() function can print only one textView
// so in order to show all the things in a particular option we have
// it is responsible for recycling of the individual list items in the list View
// it inflates the list_item.xml because this layout shows the structure of each list items

public class WordAdapter extends ArrayAdapter<Word> {

    private int layouts_color_id;


    public WordAdapter (Activity context, ArrayList<Word> wordList,int color_id){
        super(context,0,wordList);
        layouts_color_id = color_id;
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

//  To also accommodate phrases tab we need to use condition statements
            if(currentWord.hasImageResource()){
                //  Setting Image view for the input of images in the miwok app
                ImageView image = (ImageView) listview.findViewById(R.id.all_image_view);
                image.setImageResource(currentWord.getImageResourceId());
            }

//  creating an object for the View class to add color to its text layouts
            View list_text_container = listview.findViewById(R.id.text_container);
//  now adding color using its resource id
            int color = ContextCompat.getColor(getContext(), layouts_color_id);
//  adding color to the layout of text container
            list_text_container.setBackgroundColor(color);

//  we return the new view that will be shown by the ArrayAdapter class
            return listview;
    }
}