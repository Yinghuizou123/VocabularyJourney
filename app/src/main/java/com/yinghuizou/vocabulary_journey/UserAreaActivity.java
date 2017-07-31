package com.yinghuizou.vocabulary_journey;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UserAreaActivity extends AppCompatActivity {


    private DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    private int  iyear, imonth,iday,ihour,iminute;
    private  Calendar cal = Calendar.getInstance();
    private Button buttondate;
    private Button buttontime;
    private  int number = 5;
    private String date3;
    private final int _id = (int) System.currentTimeMillis();
    private static final String TAG = "Caleder";
    private static MyDBHandler dbHandler;
    private NotificationCompat.Builder notification;
    private static final int uniqueID= 45612;
    private  int intyear, intmonth, intday, inthour, intminute;
    private static EditText Entertext ;
    private  EditText Enterdefination;
    private EditText Enterexample;
    private TextView inputtext ;
    private TextView date ;
    private ImageView imageView ;
    private int REQUEST_CODE_GALLERY=999;
    private static byte[] image ;
    private ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
    private String datecalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        // final TextView x = (TextView)findViewById(R.id.tvWelcome);
        Entertext = (EditText)findViewById(R.id.ettitleinput);
        Enterdefination = (EditText)findViewById(R.id.etdefinationinput);
        Enterexample = (EditText)findViewById(R.id.etexampleinput);
        imageView  = (ImageView)findViewById(R.id.ivuserimage);

        dbHandler = new MyDBHandler(this,"VocabDB.sqlite",null,1);




        final Button button3 = (Button) findViewById(R.id.btInserttext);
        final Button button4 = (Button) findViewById(R.id.btchooseimage);
        buttondate = (Button) findViewById(R.id.btpickDate);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        Intent incomeintent = getIntent();

        String time  = incomeintent.getStringExtra("year") + "/" + incomeintent.getStringExtra("month")   + "/" + incomeintent.getStringExtra("day");
        datecalender = time;

        String stringyear  = incomeintent.getStringExtra("year");
        String stringmonth  = incomeintent.getStringExtra("month");
        String stringyday  = incomeintent.getStringExtra("day");


        if(stringyear!=null){
            intyear = Integer.parseInt(stringyear);
            intmonth = Integer.parseInt(stringmonth);
            intday = Integer.parseInt(stringyday);

        }



    }



    public void pickdate(View view){

        new TimePickerDialog(this, t, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
        new DatePickerDialog(this,d,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();


    }



    public void confirm(View view){

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        date3 = df.format(Calendar.getInstance().getTime());



        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();



        //cal.set(iyear,(imonth-1),iday,15,31);

        DateFormat dfs = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = dfs.format(Calendar.getInstance().getTime());
        Log.d(TAG, "calender" + date );
        Long c =  cal.getTimeInMillis();
        Log.d(TAG, "calender" + c );


        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();
        Log.d(TAG, "calender" + iyear +" " + (imonth) + " "+  iday+ " "+ ihour + " "+ iminute );

        cal.set(iyear,(imonth),iday,ihour,iminute);

        Intent intent = new Intent(this, AlertReceiver.class);
        final int alarmId = (int) System.currentTimeMillis();
        PendingIntent senders = PendingIntent.getBroadcast(this, alarmId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager ams = (AlarmManager) getSystemService(this.ALARM_SERVICE);
        ams.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), senders);


        Toast.makeText(getApplicationContext(), "Set Alarm successfully!", Toast.LENGTH_SHORT).show();


    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            iyear = year;
            imonth = monthOfYear;
            iday = dayOfMonth;
        }
    };



    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


            ihour = hourOfDay;
            iminute = minute;


        }
    };











    public void addButtonClick(View view){

        try{
            String name = Entertext.getText().toString();
            String place = Enterdefination.getText().toString();
            String time  = Enterexample.getText().toString();
            byte[] image  =  imageviewToByte(imageView);
            image = imageviewToByte(imageView);
            dbHandler.addProduct(name,place,time,image);
            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

            Entertext.setText("");
            Enterdefination.setText("");
            Enterexample.setText("");
            imageView.setImageResource(R.mipmap.ic_launcher);
        } catch (Exception e){
            e.printStackTrace();


        }


    }





    public void viewtext(View view){

        Intent intent = new Intent(UserAreaActivity.this, Itemlist.class);
        startActivity(intent);
    }











    public void chooseimageclick(View view){
        ActivityCompat.requestPermissions(
                UserAreaActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }







    public static MyDBHandler getDbHandler() {
        return dbHandler;
    }




    public void setText(String name){

        Entertext.setText(name);
    }



    ///Convert image in byte array
    public  byte[] imageviewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    //add the permission to the device to using the Camera

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if(requestCode == REQUEST_CODE_GALLERY){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_GALLERY);

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//First if prevent crush
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}