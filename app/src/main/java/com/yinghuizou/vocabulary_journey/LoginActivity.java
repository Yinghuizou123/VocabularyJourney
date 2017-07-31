package com.yinghuizou.vocabulary_journey;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationManager;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by yinghuizou on 7/17/17.
 */

public class LoginActivity extends AppCompatActivity {
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        final Button button = (Button) findViewById(R.id.etlogin);


        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent UserPage= new Intent(LoginActivity.this,UserAreaActivity.class);
                startActivity(UserPage);

            }


        } );



    }
}
