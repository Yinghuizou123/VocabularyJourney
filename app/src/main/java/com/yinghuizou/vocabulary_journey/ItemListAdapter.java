package com.yinghuizou.vocabulary_journey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class ItemListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Item> itemlist;

    public ItemListAdapter(Context context, int layout, ArrayList<Item> itemlise) {
        this.context = context;
        this.layout = layout;
        this.itemlist = itemlise;
    }

    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView itemimage;
        TextView itemtitle;
        TextView itemdefination;
        TextView itemexample;
    }


    @Override



    public View getView(int position, View view, ViewGroup parent) {

        View row  = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);


            holder.itemtitle = (TextView) row.findViewById(R.id.itemtitle);
            holder.itemdefination = (TextView) row.findViewById(R.id.itemdefinition);
            holder.itemexample = (TextView) row.findViewById(R.id.itemexample);
            holder.itemimage =   (ImageView) row.findViewById(R.id.itemimage);
            row.setTag(holder);

        }else {
            holder = (ViewHolder)row.getTag();

        }

        Item item = itemlist.get(position);
        holder.itemtitle.setText(item.getTitle());
        holder.itemdefination.setText(item.getDefinition());
        holder.itemexample.setText(item.getExmaple());



        byte[] Image = item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        holder.itemimage.setImageBitmap(bitmap);

        return row;
    }
}

