package com.panelic.kacau;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Method intent ke button peta lokasi
        Button btn_maps = (Button) findViewById(R.id.btn_maps);

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        // Method intent ke button list lokasi
        Button btn_list_lokasi = (Button)findViewById(R.id.btn_list_lokasi);

        btn_list_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), List.class);
                startActivity(i);
            }
        });

        // Method intent ke button artikel
        Button btn_artikel = (Button)findViewById(R.id.btn_artikel);

        btn_artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainArtikel.class);
                startActivity(i);
            }
        });

        // Method intent ke button tips trik
        Button btn_tips = (Button)findViewById(R.id.btn_tips);

        btn_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TipsActivity.class);
                startActivity(i);
            }
        });

        // Method intent ke button tentang
        Button btn_tentang = (Button)findViewById(R.id.btn_tentang);

        btn_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TentangActivity.class);
                startActivity(i);
            }
        });

        // Method intent ke button edukasi
        Button btn_edukasi = (Button)findViewById(R.id.btn_edukasi);

        btn_edukasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EdukasiActivity.class);
                startActivity(i);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
