package com.example.practica_7_ej1;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Handler handler;
    private Runnable actualizar;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.moondoesntmind);

        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView tvTiempo = findViewById(R.id.tvTiempo);
        TextView tvMax = findViewById(R.id.tvMax);

        mediaPlayer.setOnPreparedListener(mp -> {
            seekBar.setMax(mediaPlayer.getDuration());
            tvTiempo.setText("00:00");

            int duracion = mediaPlayer.getDuration();
            int min = duracion / 1000 / 60;
            int seg = duracion / 1000 % 60;
            String max = String.format("%02d:%02d", min, seg);
            tvMax.setText(max);
        });

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPausa = findViewById(R.id.btnPausa);

        handler = new Handler();
        actualizar = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                int min = mediaPlayer.getCurrentPosition() / 1000 / 60;
                int seg = mediaPlayer.getCurrentPosition() / 1000 % 60;
                String tiempo = String.format("%02d:%02d", min, seg);
                tvTiempo.setText(tiempo);

                handler.postDelayed(this, 1000);
            }
        };
        handler.post(actualizar);

        btnPlay.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        btnPausa.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                handler.removeCallbacks(actualizar);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button btnMsg = findViewById(R.id.btnMsg);
        Button btnMoneda = findViewById(R.id.btnMoneda);
        Button btnPuerta = findViewById(R.id.btnPuerta);
        Button btnComer = findViewById(R.id.btnComer);

        soundPool = new SoundPool.Builder().setMaxStreams(4).build();

        int sonido1 = soundPool.load(this, R.raw.msg, 1);
        int sonido2 = soundPool.load(this, R.raw.moneda, 1);
        int sonido3 = soundPool.load(this, R.raw.puerta, 1);
        int sonido4 = soundPool.load(this, R.raw.comer, 1);

        btnMsg.setOnClickListener(v -> {
            soundPool.play(sonido1, 1, 1, 1, 0, 1);
        });

        btnMoneda.setOnClickListener(v -> {
            soundPool.play(sonido2, 1, 1, 1, 0, 1);
        });

        btnPuerta.setOnClickListener(v -> {
            soundPool.play(sonido3, 1, 1, 1, 0, 1);
        });

        btnComer.setOnClickListener(v -> {
            soundPool.play(sonido4, 1, 1, 1, 0, 1);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(actualizar);
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}