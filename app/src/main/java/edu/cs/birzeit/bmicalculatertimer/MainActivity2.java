package edu.cs.birzeit.bmicalculatertimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
//   / private EditText edtminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

//        edtminute=findViewById(R.id.edtminute);
        runTimer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle bundle){
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }

    public void btnStartOnClick(View view) {
        running = true;
    }

    public void btnStopOnClick(View view) {
        running=false;
    }

    private void runTimer(){
        final TextView txtView = (TextView) findViewById(R.id.txtTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = seconds % 3600 /60;
                int snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);
                txtView.setText(time);
                if(running){
                    ++seconds;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    public void btnResetOnClick(View view) {
        seconds=0;
        running=false;
    }

    public void btngoOnClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
