package com.amriksinghpadam.myfileupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayAll extends AppCompatActivity {

    ArrayList<Bitmap> imgList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> priceList = new ArrayList<>();
    RecyclerView recyclerView;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
        getSupportActionBar().hide();

        db = new MyDatabase(this);
        Cursor allData = db.getData();

        while (allData.moveToNext()){

            nameList.add(allData.getString(0));
            priceList.add(allData.getString(1));
        }

        Cursor imgData = db.getImages();
        while (imgData.moveToNext()){
            byte[] imageByte = imgData.getBlob(0);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
            imgList.add(bitmap);
        }

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        MyAdapter adapter = new MyAdapter(this,imgList,nameList,priceList);

        recyclerView.setAdapter(adapter);


    }
}
