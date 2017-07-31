package com.yinghuizou.vocabulary_journey;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Itemlist extends AppCompatActivity {
    ListView listView;
    ArrayList<Item> listitem;
    ItemListAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        listView = (ListView)findViewById(R.id.listviewc);
        listitem = new ArrayList<>();
        adapter = new ItemListAdapter(this, R.layout.item, listitem);

        listView.setAdapter(adapter);
        addData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] items  = {"DELETE"};
                AlertDialog.Builder dialog = new  AlertDialog.Builder(Itemlist.this);
                dialog.setTitle("Delete the Vocab");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Delete!", Toast.LENGTH_SHORT).show();
                        Cursor c = UserAreaActivity.getDbHandler().getDataid();
                        ArrayList<Integer> arrID = new ArrayList<Integer>();
                        while (c.moveToNext()){
                            arrID.add(c.getInt(0));
                        }

                        UserAreaActivity.getDbHandler().deleteData(arrID.get(position));
                        addData();
                    }

                });

                dialog.show();


            }
        });


    }

    



    public void addData() {
        Cursor cursor = UserAreaActivity.getDbHandler().getdata();
        listitem.clear();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String definition = cursor.getString(2);
            String example = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            listitem.add(new Item(title, definition, example, image, id));

        }
        adapter.notifyDataSetChanged();
    }







}