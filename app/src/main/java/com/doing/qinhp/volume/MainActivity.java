package com.doing.qinhp.volume;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;

    private TextView pb_tv;
    private TextView v_tv;
    private TextView min;
    private TextView max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.sb_progress);
        pb_tv = findViewById(R.id.sb_progress_view);
        v_tv = findViewById(R.id.tv_volume);
        min = findViewById(R.id.tv_minvolume);
        max = findViewById(R.id.tv_maxvolume);


        final AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekBar.setProgress(currentVolume);
        pb_tv.setText("当前音量" + currentVolume);
        v_tv.setText("当前progress" + currentVolume);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            min.setText("最小音量" + mAudioManager.getStreamMinVolume(AudioManager.FLAG_PLAY_SOUND));
        }
        max.setText("最大音量" + mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //第一个参数是调节的类型，第二个是调节的大小，第三个是显不显示调节标志
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
                pb_tv.setText("当前音量" + mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
                v_tv.setText("当前progress" + progress);
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
