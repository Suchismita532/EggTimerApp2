package com.example.eggtimerapp2;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


    public class MainActivity extends AppCompatActivity {
        SeekBar timerSeekBar;
        TextView timerTextView;
        Button controllerButton;
        Boolean counterIsActive = false;
        CountDownTimer countDownTimer;
        private Bundle savedInstanceState;
        public void resetTimer()
        {
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            countDownTimer.cancel();
            controllerButton.setText("Go!");
            timerSeekBar.setEnabled(true);
            counterIsActive = false;
        }

        public void updateTimer(int secondsLeft)
        {
            int minutes = (int) secondsLeft / 60;
            int seconds = secondsLeft - minutes * 60;
            String secondString = Integer.toString(seconds);
            if (seconds <= 9)
            {
                secondString = "0" + secondString;
                timerTextView.setText(Integer.toString(minutes) + ";" + Integer.toString(seconds));

            }
        }
        public void controlTimer(View view) {
            counterIsActive = true;
            if (counterIsActive == false) {
                timerSeekBar.setEnabled(false);
                controllerButton.setText("stop");

                countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                    @Override
                    public void onTick(long millisUntilfinished) {
                        updateTimer((int) millisUntilfinished / 1000);
                    }

                    @Override
                    public void onFinish() {
                        resetTimer();
                        MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                        mplayer.start();
                    }

                }.start();


            } else {
                resetTimer();
            }
        }
            @Override
            protected void onCreate (Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                SeekBar timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
                timerTextView = (TextView) findViewById(R.id.timerTextView);
                controllerButton = (Button) findViewById(R.id.controllerButton);
                timerSeekBar.setMax(600);
                timerSeekBar.setProgress(30);
                timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                    {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                });


            }


        }