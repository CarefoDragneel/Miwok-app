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

//       creating overriding class to get make an onClickListener for calling NumberActivity
        TextView number = (TextView) findViewById(R.id.number_tab);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View view){
                        Intent number_intent = new Intent(MainActivity.this, NumberActivity.class);
                        startActivity(number_intent);
            }
        });

    }

}