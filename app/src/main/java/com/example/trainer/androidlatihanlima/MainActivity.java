package com.example.trainer.androidlatihanlima;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.trainer.androidlatihanlima.helpers.BookHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Cursor c;
    SimpleCursorAdapter sca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.lv_list);
        FloatingActionButton fab = findViewById(R.id.fab);


        BookHelper helper = new BookHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        String[] datax = {"_id", "title", "author"};
        c = database.query("samplebooks", datax,
                null,
                null,
                null,
                null,
                null);
        //tambah

        sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                c, new String[]{"title", "author"},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lv.setAdapter(sca);


//        ArrayList<String> data = new ArrayList<>();
//        c.moveToFirst();
//        while (!c.isAfterLast()){
//            String title = c.getString(c.getColumnIndex("title"));
//            data.add(title);
//            c.moveToNext();
//        }
//
//        if (data.isEmpty()){
//            data.add("data belum tersedia");
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, data);
//        lv.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, inserting.class));
            }
        });

        //register menu
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.edit_menu, menu);
    }

    //membuat method menghapus data buku
    public void deletebooks(long id) {
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("samplebooks", "_id=?",
                new String[]{String.valueOf(id)});
        Toast.makeText(this, "data berhasil dihapus", Toast.LENGTH_SHORT).show();

        Cursor x = db.query("samplebooks", new String[]{"_id", "title", "author"},
                null, null, null, null, null);
        sca.changeCursor(x);
        sca.notifyDataSetChanged();

    }

    //membuat method update
    public void updatebooks(long id) {
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor e = db.query("samplebooks", new String[]{"title", "author"},
                "_id=?", new String[]{String.valueOf(id)},
                null, null, null);

        e.moveToFirst();
        Intent pindah = new Intent(MainActivity.this, inserting.class);
        pindah.putExtra("_id", id);
        pindah.putExtra("title", e.getString(e.getColumnIndex("title")));
        pindah.putExtra("author", e.getString(e.getColumnIndex("author")));
        startActivity(pindah);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete: {
                deletebooks(info.id);

            }
            break;
            case R.id.update: {
                updatebooks(info.id);
            }
            break;
            default:
                //
        }

        return (true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Warning")
                        .setCancelable(false)
                        .setMessage("Apakah anda yakin ingin keluar?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent x = new Intent(MainActivity.this, NamaClass.class);
                                startActivity(x);
                                finish();
                                ;
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //
                            }
                        }).show();
            }
            break;

        }
        return super.onOptionsItemSelected(item);
    }
}



