package org.osoro.project10;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.app.Service;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private String number="";
    private final int STORAGE_PERMISSION_REQUEST=2017;
    private EditText editText;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private ImageButton star;
    private ImageButton sharp;
    private ImageButton erase;
    private ImageButton call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);
        star=(ImageButton)findViewById(R.id.star);
        sharp=(ImageButton)findViewById(R.id.sharp);
        erase=(ImageButton)findViewById(R.id.erase);
        call=(ImageButton)findViewById(R.id.call);
        String[] permission={Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(this,permission,STORAGE_PERMISSION_REQUEST);
        button0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="0";
                editText.setText(number);
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="1";
                editText.setText(number);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="2";
                editText.setText(number);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="3";
                editText.setText(number);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="4";
                editText.setText(number);
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="5";
                editText.setText(number);
            }
        });
        button6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="6";
                editText.setText(number);
            }
        });
        button7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="7";
                editText.setText(number);
            }
        });
        button8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="8";
                editText.setText(number);
            }
        });
        button9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="9";
                editText.setText(number);
            }
        });
        star.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="*";
                editText.setText(number);
            }
        });
        sharp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                number+="#";
                editText.setText(number);
            }
        });
        call.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent it= new Intent(Intent.ACTION_CALL);
                it.setData(Uri.parse("tel:"+number));
                startActivity(new Intent(MainActivity.this, FloatingService.class));
                try{
                    startActivity(it);
                    }
                catch(Exception e){
                    e.printStackTrace();
                }
        editText.setText("");
                number="";
            }
        });
        erase.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                isback();
            }
        });

    }
    private void isback(){
        CharSequence cc=editText.getText();
        if(cc!=null&&cc.length()>0){
            editText.setText("");
            String pt;
            editText.append(cc.subSequence(0,cc.length()-1));
            pt=number.substring(0,number.length()-1);
            number="";
            number=pt;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_PERMISSION_REQUEST:
                if(grantResults[0]== PermissionChecker.PERMISSION_GRANTED){
                }
        }
    }
}
