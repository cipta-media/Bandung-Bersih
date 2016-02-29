package com.panelic.kacau;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import android.widget.TextView;
import android.view.View;

import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by Wahyu on 05/01/2016.
 */

public class List extends AppCompatActivity {

    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    AlertDialogManager alert = new AlertDialogManager();

    ProgressDialog pDialog;
    String status = "1";

    JSONArray college = null;
    ListView lve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        lve = (ListView) findViewById(R.id.list);

        cekInternet();

        lve.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                String nama = ((TextView) view.findViewById(R.id.nama))
                        .getText().toString();
                String alamat = ((TextView) view.findViewById(R.id.alamat))
                        .getText().toString();
                String gambar = ((TextView) view.findViewById(R.id.gambar))
                        .getText().toString();
                String jenis = ((TextView) view.findViewById(R.id.jenis))
                        .getText().toString();
                String operasional = ((TextView) view.findViewById(R.id.operasional))
                        .getText().toString();
                String kontak = ((TextView) view.findViewById(R.id.kontak))
                        .getText().toString();
                String deskripsi = ((TextView) view.findViewById(R.id.deskripsi))
                        .getText().toString();

                Intent x = new Intent(getApplicationContext(), Detail.class);
                x.putExtra("nama", nama);
                x.putExtra("alamat", alamat);
                x.putExtra("gambar", gambar);
                x.putExtra("jenis", jenis);
                x.putExtra("operasional", operasional);
                x.putExtra("kontak", kontak);
                x.putExtra("deskripsi", deskripsi);

                startActivity(x);
            }
        });
    }

    public class AmbilData extends AsyncTask<String, String, String> {

        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(List.this);
            pDialog.setMessage("Mencari Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String url;
            url = "http://panelic.com/bdgbersih/lokasi.php";

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("lokasi");

                String success = json.getString("success");

                if (success.equals("1")) {

                    for (int i = 0; i < college.length(); i++) {
                        JSONObject c = college.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();

                        String id = c.getString("id").trim();
                        String latitude = c.getString("latitude");
                        String longitude = c.getString("longitude");
                        String nama = c.getString("nama");
                        String alamat = c.getString("alamat");
                        String gambar = c.getString("gambar");
                        String jenis = c.getString("jenis");
                        String operasional = c.getString("operasional");
                        String kontak = c.getString("kontak");
                        String deskripsi = c.getString("deskripsi");

                        map.put("id", id);
                        map.put("nama", nama);
                        map.put("latitude", latitude);
                        map.put("longitude", longitude);
                        map.put("alamat", alamat);
                        map.put("gambar", gambar);
                        map.put("jenis", jenis);
                        map.put("operasional", operasional);
                        map.put("kontak", kontak);
                        map.put("deskripsi", deskripsi);

                        dataList.add(map);
                    }
                } else {

                    pDialog.dismiss();
                    status = "0";

                }

            } catch (JSONException e) {

                pDialog.dismiss();

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (status.equals("0")) {
                Toast.makeText(getApplicationContext(), "data tidak ada",
                        Toast.LENGTH_SHORT).show();

            }

            ListAdapter adapter = new SimpleAdapter(getApplicationContext(),
                    dataList, R.layout.list_item, new String[] { "nama", "id",
                    "latitude", "longitude", "alamat", "gambar", "jenis", "operasional", "kontak", "deskripsi" },
                    new int[] { R.id.nama, R.id.id, R.id.latitude,
                            R.id.longitude, R.id.alamat, R.id.gambar, R.id.jenis, R.id.operasional, R.id.kontak,
                            R.id.deskripsi });

            lve.setAdapter(adapter);
        }
    }

    public void cekInternet() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {

            new AmbilData().execute();

        } else {

            alert.showAlertDialog(List.this, "Peringatan",
                    "cek koneksi internet.",
                    false);
        }
    }

}
