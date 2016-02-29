package com.panelic.kacau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import android.os.AsyncTask;
import 	android.os.CountDownTimer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Ibni Faishal on 04/01/2016.
 */
public class MapsActivity extends FragmentActivity implements android.location.LocationListener, OnMarkerClickListener {

    final int RQS_GooglePlayServices = 1;
    private GoogleMap googleMap;

    double latitude, longitude;
    ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();


    JSONArray college = null;
    ListView lve;
    Button list, refresh;

    ConnectionDetector cd;
    Boolean isInternetPresent = false;

    AlertDialogManager alert = new AlertDialogManager();

    HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_layout);

        cekInternet();

        SupportMapFragment fm = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);


        googleMap = fm.getMap();
        if(googleMap != null){

        }

        CekGPS();
        LatLng user = new LatLng(latitude, longitude);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                user, 13));

        googleMap.setMyLocationEnabled(true);

        //Toast untukmenampilkan koordinat sekarang
        //if(latitude!=0 && longitude !=0){
            //Toast.makeText(getApplicationContext(), "Latitude : "+latitude+" Longitude : "+longitude, Toast.LENGTH_LONG).show();
        //}
    }

    public class AmbilData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MapsActivity.this);
            pDialog.setMessage("Mencari Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            new CountDownTimer(20000, 1000) {

                public void onTick(long millisUntilFinished) {
                    // You don't need anything here
                }

                public void onFinish() {
                    pDialog.dismiss();
                }
            }.start();

        }

        @Override
        protected String doInBackground(String... params) {
            String url;

            url = "http://panelic.com/bdgbersih/lokasi.php";

            JSONParser jParser = new JSONParser();

            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                college = json.getJSONArray("lokasi");
                Log.e("error", json.getString("success"));

                for (int i = 0; i <= college.length(); i++) {

                    JSONObject c = college.getJSONObject(i);

                    map = new HashMap<String, String>();

                    String id_1 = c.getString("id").trim();
                    String latitude_1 = c.getString("latitude").trim();
                    String longitude_1 = c.getString("longitude").trim();
                    String nama_1 = c.getString("nama").trim();
                    String alamat_1 = c.getString("alamat").trim();
                    String gambar_1 = c.getString("gambar").trim();
                    String jenis_1 = c.getString("jenis").trim();
                    String operasional_1 = c.getString("operasional").trim();
                    String kontak_1 = c.getString("kontak").trim();
                    String deskripsi_1 = c.getString("deskripsi").trim();

                    map.put("id", id_1);
                    map.put("nama", nama_1);
                    map.put("latitude", latitude_1);
                    map.put("longitude", longitude_1);
                    map.put("alamat", alamat_1);
                    map.put("gambar", gambar_1);
                    map.put("jenis", jenis_1);
                    map.put("operasional", operasional_1);
                    map.put("kontak", kontak_1);
                    map.put("deskripsi", deskripsi_1);

                    dataList.add(map);

                }

            } catch (JSONException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            for (int x = 0; x < dataList.size(); x = x + 1) {

                double latasal = Double.parseDouble(dataList.get(x).get(
                        "latitude"));
                double longasal = Double.parseDouble(dataList.get(x).get(
                        "longitude"));
                LatLng posisi = new LatLng(latasal, longasal);
                String nama = dataList.get(x).get("nama");

                googleMap.addMarker(new MarkerOptions()
                        .position(posisi)
                        .title(nama)
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.new_marker)));

            }
        }
    }






    public void CekGPS() {
        try {

                /* pengecekan GPS hidup / tidak */
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Info");
                builder.setMessage("Anda akan mengaktifkan GPS?");

                builder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent i = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(i);
                            }
                        });

                builder.setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                dialog.dismiss();
                            }
                        });
                builder.create().show();

            }

        } catch (Exception e) {

        }

        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getBaseContext());

        // menampilkan status google play service
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
                    requestCode);
            dialog.show();

        } else {
            // Google Play Services tersedia
            Criteria criteria = new Criteria();
            LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String provider = locationmanager.getBestProvider(criteria, true);
            Location location = locationmanager.getLastKnownLocation(provider);

            if (location != null) {
                onLocationChanged(location);
            }

            locationmanager.requestLocationUpdates(provider, 500, 0, this);
            LatLng posisi = new LatLng(latitude, longitude);

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posisi,
                    12));
            googleMap.setOnMarkerClickListener(this);

        }
    }

    @Override
    public void onLocationChanged(Location lokasi) {
        // TODO Auto-generated method stub
        try {
            latitude = lokasi.getLatitude();
            longitude = lokasi.getLongitude();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void cekInternet() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {

            new AmbilData().execute();

        } else {

            alert.showAlertDialog(MapsActivity.this, "Peringatan",
                    "cek koneksi internet.", false);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // TODO Auto-generated method stub

        String id= marker.getId();
        id = id.substring(1);

        Intent x = new Intent(getApplicationContext(), Detail.class);
        x.putExtra("nama", dataList.get(Integer.parseInt(id)).get("nama"));
        x.putExtra("gambar", dataList.get(Integer.parseInt(id)).get("gambar"));
        x.putExtra("alamat", dataList.get(Integer.parseInt(id)).get("alamat"));
        x.putExtra("jenis", dataList.get(Integer.parseInt(id)).get("jenis"));
        x.putExtra("operasional", dataList.get(Integer.parseInt(id)).get("operasional"));
        x.putExtra("kontak", dataList.get(Integer.parseInt(id)).get("kontak"));
        x.putExtra("deskripsi", dataList.get(Integer.parseInt(id)).get("deskripsi"));

        startActivity(x);
        return false;
    }
}
