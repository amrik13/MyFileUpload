package com.amriksinghpadam.myfileupload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.preference.PreferenceManager.*;

public class MainActivity extends AppCompatActivity {

    private EditText name,price;
    private ImageView img;
    private Button uploadBtn,submitBtn,viewBtn;
    private String nameStr,priceStr;
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        name = findViewById(R.id.nameTextId);
        price = findViewById(R.id.priceTextId);
        uploadBtn = findViewById(R.id.uploadImgBtnId);
        submitBtn = findViewById(R.id.submitBtnId);
        viewBtn = findViewById(R.id.viewBtnId);
        img = findViewById(R.id.imageId);

        //Submit btn Listener
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString().trim();
                priceStr = price.getText().toString().trim();
                if(nameStr.isEmpty() || priceStr.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please Fill All Section!",Toast.LENGTH_SHORT).show();
                }else{
                    int priceInt = Integer.parseInt(priceStr);

                    byte[] byteImg = imageTOByteArray(img);
                    db = new MyDatabase(MainActivity.this);
                    boolean flag = db.insertData(nameStr,priceInt,byteImg);
                    if (flag==true){
                        Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Not Inserted",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        //Upload Img Listener
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });

        //View all btn Listener

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new MyDatabase(MainActivity.this);
                Cursor allData = db.getData();
                if(allData.getCount()==0) {
                    Toast.makeText(MainActivity.this,"There Is No Item!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,DisplayAll.class);
                    startActivity(intent);
                }
            }
        });
    }

    public byte[] imageTOByteArray(ImageView img) {
        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,30,stream);
        byte[] bytearray = stream.toByteArray();
        return bytearray;

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Uri uri = imageReturnedIntent.getData();
        img.setImageURI(uri);

    }


}
