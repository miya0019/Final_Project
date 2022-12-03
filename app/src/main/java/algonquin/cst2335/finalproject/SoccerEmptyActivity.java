package algonquin.cst2335.finalproject;

//import android.media.session.MediaController;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.finalproject.SoccerActivity.Match;

import java.util.ArrayList;

public class SoccerEmptyActivity extends AppCompatActivity {
    TextView title;
    TextView urlText;
    String url;
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soccer_empty_activity_layout);
        Bundle dataToPass = new Bundle();
        ArrayList<Match> favourites = (ArrayList<Match>) getIntent().getSerializableExtra("favourites");
        dataToPass.putSerializable("favourites", favourites);
        SoccerDetailsFragment fragment = new SoccerDetailsFragment();
        fragment.setArguments(dataToPass);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, fragment).commit();

    }
}