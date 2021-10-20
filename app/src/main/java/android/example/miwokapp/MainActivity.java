package android.example.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       initialising textview obeject for Numbers miwok words
        TextView number = (TextView) findViewById(R.id.number_tab);
//        setting event listener on the number textview
        number.setOnClickListener(new View.OnClickListener() {

//            overriding the click listener interface to call an explicit intent
            @Override
                    public void onClick(View view){
                        Intent number_intent = new Intent(MainActivity.this, NumberActivity.class);
                        startActivity(number_intent);
            }
        });

//        intialising textview object for Family miwok words
        TextView family = (TextView) findViewById(R.id.family_tab);
//        setting event listener on the family textview
        family.setOnClickListener(new View.OnClickListener(){

//          Overriding the click listener interface to call an explicit intent
            @Override
                public void onClick(View view){
                Intent family_intent = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(family_intent);
            }
        });

        //        intialising textview object for Color miwok words
        TextView Color = (TextView) findViewById(R.id.color_tab);
//        setting event listener on the color textview
        Color.setOnClickListener(new View.OnClickListener(){

            //          Overriding the click listener interface to call an explicit intent
            @Override
            public void onClick(View view){
                Intent color_intent = new Intent(MainActivity.this,ColorActivity.class);
                startActivity(color_intent);
            }
        });

        //        intialising textview object for Miwok phrases
        TextView Phrases = (TextView) findViewById(R.id.phrases_tab);
//        setting event listener on the phrases textview
        Phrases.setOnClickListener(new View.OnClickListener(){

            //          Overriding the click listener interface to call an explicit intent
            @Override
            public void onClick(View view){
                Intent phrases_intent = new Intent(MainActivity.this,PhasesActivity.class);
                startActivity(phrases_intent);
            }
        });


    }

}