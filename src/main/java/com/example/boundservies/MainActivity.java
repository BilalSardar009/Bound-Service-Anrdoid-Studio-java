package com.example.boundservies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button Start,Stop;
    static TextView text;
    boolean bound;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Start=findViewById(R.id.button);
        Stop=findViewById(R.id.button2);
        text=findViewById(R.id.textView);
        text.setText("Current Time: "+ new Date());
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,BoundService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                //startService(new Intent(MainActivity.this,MyService.class));
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bound) {
                    unbindService(connection);
                    bound=false;
                }
                //stopService(new Intent(MainActivity.this,MyService.class));
            }
        });

    }

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound=false;
        }
    };
}