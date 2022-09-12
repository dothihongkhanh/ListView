 package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
     ListView lvMonHoc;
     ArrayList<String> arrayCourse;
     ImageButton imbtnThem;
     EditText edtMonHoc;
     ImageButton imbtnSua;
     ImageButton imbtnXoa;

     int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMonHoc = (ListView) findViewById(R.id.listviewMonHoc);
        imbtnThem = (ImageButton) findViewById(R.id.imabuttonThem);
        edtMonHoc = (EditText) findViewById(R.id.etxtAddMH);
        imbtnSua = (ImageButton) findViewById(R.id.imabuttonSua);
        imbtnXoa = (ImageButton) findViewById(R.id.imabuttonXoa);


        arrayCourse = new ArrayList<>();
        arrayCourse.add("Java");
        arrayCourse.add("Android");
        arrayCourse.add("iOS");
        arrayCourse.add("PHP");
        arrayCourse.add("ASP.NET");
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayCourse);
        lvMonHoc.setAdapter(adapter);
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = lvMonHoc.getAdapter().getItem(i).toString();
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("name", str);
                startActivity(intent);
                lvMonHoc.setSelector(R.color.purple_500);
                return false;
            }
        });
        imbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monhoc = edtMonHoc.getText().toString();
                arrayCourse.add(monhoc);
                adapter.notifyDataSetChanged();
            }
        });
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtMonHoc.setText(arrayCourse.get(i));
                vitri = i;
            }
        });
        imbtnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.set(vitri, edtMonHoc.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        imbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.remove(vitri);
                edtMonHoc.setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }
}