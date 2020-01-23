package traf7.lamsalsamyog.humanbenchmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button reaction, number, verbal, profile;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.click);

        reaction = findViewById(R.id.reaction_button);
        reaction.setOnClickListener(new Listener(0));

        number = findViewById(R.id.number_button);
        number.setOnClickListener(new Listener(1));

        verbal = findViewById(R.id.verbal_button);
        verbal.setOnClickListener(new Listener(2));

        profile = findViewById(R.id.profile_button);
        profile.setOnClickListener(new Listener(3));
    }

    class Listener implements View.OnClickListener {

        private int id;

        public Listener(int id) {
            this.id = id;
        }

        public void onClick(View v) {
            mp.start();

            if (id == 0) {
                startActivity(new Intent(MainActivity.this, ReactionTime.class));
            }
            else if (id == 1) {
                startActivity(new Intent(MainActivity.this, NumberMemory.class));
            }
            else if (id == 2) {
                startActivity(new Intent(MainActivity.this, VerbalMemory.class));
            }
            else if (id == 3) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        }

    }
}
