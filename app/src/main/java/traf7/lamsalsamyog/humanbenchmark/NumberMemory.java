package traf7.lamsalsamyog.humanbenchmark;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NumberMemory extends AppCompatActivity {

    TextView timeLeft, level, number;
    Button start, enter;
    EditText userInput;
    MediaPlayer mp;
    int level_int = 0;
    String numbers = "0123456789";
    StringBuilder sb;
    Random random;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory);

        cdt = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                updateTimeLeft(millisUntilFinished);
                start.setEnabled(false);
            }
            public void onFinish() {
                number.setText("");
                userInput.setEnabled(true);
            }
        };

        mp = MediaPlayer.create(this, R.raw.click);
        sb = new StringBuilder();
        random = new Random();

        timeLeft = findViewById(R.id.timeleft);
        level = findViewById(R.id.level);
        number = findViewById(R.id.number);

        start = findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateGame();
            }
        });

        enter = findViewById(R.id.enter_button);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();

                if(Integer.parseInt(userInput.getText().toString()) == Integer.parseInt(sb.toString())) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
                    userInput.setText("");
                    updateGame();
                }
                else {
                    resetGame();
                }
            }
        });

        userInput = findViewById(R.id.user_input);
    }

    private void resetGame() {
        level_int = 0;
        String text = "Level: 0";
        level.setText(text);

        sb.setLength(0);
        userInput.setEnabled(false);
        start.setEnabled(true);

        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
    }

    private void updateGame() {
        mp.start();
        updateLabels();

        userInput.setEnabled(false);

        cdt.start();
    }

    private void updateLabels() {
        updateLevel();
        updateNumber();
    }

    private void updateTimeLeft(long millisUntilFinished) {
        String tl = millisUntilFinished / 1000 + "s";
        timeLeft.setText(tl);
    }

    private void updateNumber() {
        sb.setLength(0);
        for (int x = 0; x < level_int; x++) {
            sb.append(numbers.charAt(random.nextInt(10)));
        }
        number.setText(sb.toString());
    }

    private void updateLevel() {
        level_int++;
        String temp = "Level: " + level_int;
        level.setText(temp);
    }
}
