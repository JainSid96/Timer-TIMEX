package com.example.siddhantjain.timex;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    boolean counter = false;
    Button button;
    CountDownTimer timer;

    public void updateTimer(int time) {

        int min = (int) time / 60;
        int sec = time - min * 60;
        String s = Integer.toString(sec);
        if (sec <=9){
            s = "0" + s;
        }
        textView.setText(Integer.toString(min) + ":" + s);
        MediaPlayer tick = MediaPlayer.create(getApplicationContext() , R.raw.tick);
        tick.start();
    }

    public  void finish (){

        textView.setText("0:30");
        seekBar.setProgress(30);
        timer.cancel();
        button.setText("START");
        seekBar.setEnabled(true);
        counter = false;
    }
    public void click (View view) {
        if (counter == false) {
            counter = true;
            seekBar.setEnabled(false);
            button.setText("STOP");

            timer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mediaPlayer.start();
                    finish();
                }
            }.start();
        }else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
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
