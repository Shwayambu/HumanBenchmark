package traf7.lamsalsamyog.humanbenchmark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class ReactionTime extends AppCompatActivity {

    Button clickArea, start;
    Timer timer;
    long startTime;
    String elapsedTime;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time);

        clickArea = findViewById(R.id.click_area);
        timer = new Timer();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        final Runnable run = new Runnable() {
            @Override
            public void run() {
                clickArea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                clickArea.setEnabled(true);
                vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
                startTime = SystemClock.elapsedRealtime();
            }
        };

        start = findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start.setEnabled(false);
                clickArea.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                clickArea.setText(":(");
                timer.schedule(new TimerTask() {
                    public void run() {
                        runOnUiThread(run);
                    }
                }, (int)(Math.random() * 5000 + 1000));
            }
        });

        clickArea.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                elapsedTime = "Your reaction time is " + (int)(SystemClock.elapsedRealtime() - startTime) + " ms.";
                clickArea.setText(elapsedTime);
                clickArea.setEnabled(false);
                start.setEnabled(true);
            }

        });
    }

}
