package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Jashan_Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jashan_main);

        Button soccerButton = findViewById(R.id.soccerButton);



        //takes user to soccer highlights page
        soccerButton.setOnClickListener(btn -> {
            Intent goToSoccer = new Intent(Jashan_Main_Activity.this, SoccerList.class);
            startActivity(goToSoccer);
        });


    }
}