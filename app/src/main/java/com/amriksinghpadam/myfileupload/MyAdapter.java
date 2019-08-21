package com.amriksinghpadam.myfileupload;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VHolder> {
    private ArrayList imgList = new ArrayList();
    private List<String> text1List = new ArrayList<>();
    private List<String> text2List = new ArrayList<>();
    private Context context;
    MyAdapter(Context context,ArrayList imgList,ArrayList<String> text1List,ArrayList<String> text2List){
        this.context = context;
        this.imgList.addAll(imgList);
        this.text1List.addAll(text1List);
        this.text2List.addAll(text2List);
    }
    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        VHolder holder = new VHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, final int position) {

       Bitmap imgBitmap = (Bitmap) imgList.get(position);
        Glide.with(context).asBitmap().load(imgBitmap).into(holder.image);
       //holder.image.setImageBitmap(imgBitmap);

        holder.text1.setText( text1List.get(position));
        holder.text2.setText( "Rs. "+text2List.get(position)+"/-");

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Product: "+text1List.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return text1List.size();
    }

    public class VHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView text1,text2;
        ImageView image;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.linearLayoutId);
            image = itemView.findViewById(R.id.imgId);
            text1 = itemView.findViewById(R.id.nameId);
            text2 = itemView.findViewById(R.id.priceId);
        }
    }
}
