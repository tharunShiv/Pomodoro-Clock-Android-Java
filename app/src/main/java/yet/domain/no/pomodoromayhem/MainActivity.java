package yet.domain.no.pomodoromayhem;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView myTV;
    int minutes=25;
    CountDownTimer myCT;
    boolean startTimer = false;

    public void playMusic(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.success);
        mp.start();
    }

    public void stopTimer(View view){
        SeekBar mySB = findViewById(R.id.seekBar);
        mySB.setVisibility(View.VISIBLE);

        Button startButton = (Button) findViewById(R.id.button);
        startButton.setVisibility(View.VISIBLE);

        Button stopButton = (Button) findViewById(R.id.button2);
        stopButton.setVisibility(View.INVISIBLE);
        myCT.cancel();

    }

    public void startTimer(View view){
        startTimer = true;
        SeekBar mySB = findViewById(R.id.seekBar);
        mySB.setVisibility(View.INVISIBLE);

        Button startButton = (Button) findViewById(R.id.button);
        startButton.setVisibility(View.INVISIBLE);

        Button stopButton = (Button) findViewById(R.id.button2);
        stopButton.setVisibility(View.VISIBLE);

        myCT = new CountDownTimer(minutes*60*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                long minutesLeft = millisUntilFinished / (60 * 1000);
                long secondsLeft = millisUntilFinished - (minutesLeft * 60 * 1000);
                if (secondsLeft < 10000) {
                    myTV.setText(Long.toString(minutesLeft) + ":0" + Long.toString(secondsLeft / 1000));
                } else {
                    myTV.setText(Long.toString(minutesLeft) + ":" + Long.toString(secondsLeft / 1000));
                }
            }

            public void onFinish() {
                myTV.setText("Hurray..! done!");
                myTV.setTextSize(25);
                playMusic();
                SeekBar mySB = findViewById(R.id.seekBar);
                mySB.setVisibility(View.VISIBLE);

                Button startButton = (Button) findViewById(R.id.button);
                startButton.setVisibility(View.VISIBLE);

                Button stopButton = (Button) findViewById(R.id.button2);
                stopButton.setVisibility(View.INVISIBLE);
                myCT.cancel();
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get ref to the seekbar
        SeekBar mySB = findViewById(R.id.seekBar);

        int progress=300;

        // set the max and the initial progress
        mySB.setMax(1440);
        mySB.setProgress(progress);

        // get the ref to the Timer display
        myTV = (TextView) findViewById(R.id.textView2);

        String timer = myTV.getText().toString();

        mySB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Log.i("Hooli", Integer.toString(i));
                // steps of 12
                // 1440/12 = 120 minutes is the max
                // so 12 is the step to 1 minute

                int min = 12;
                int need; // How much is needed by the user

                // To set the minimum to 1
                if(i<min){
                    need = 12;
                } else {
                    need = i;
                }

                minutes = need/12;
                myTV.setTextSize(70);
                myTV.setText(Integer.toString(minutes)+":00");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
