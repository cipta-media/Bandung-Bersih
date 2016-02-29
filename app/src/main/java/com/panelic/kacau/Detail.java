package com.panelic.kacau;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Wahyu on 05/01/2016.
 */
public class Detail extends AppCompatActivity {
    private TextView nama;
    private TextView alamat;
    private TextView jenis;
    private TextView operasional;
    private TextView kontak;
    private TextView deskripsi;

    private String url = "http://panelic.com/bdgbersih/images/";
    private ImageView image;

    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        nama = (TextView) findViewById(R.id.nama);
        alamat = (TextView) findViewById(R.id.alamat);
        image = (ImageView) findViewById(R.id.image);
        jenis = (TextView) findViewById(R.id.jenis);
        operasional = (TextView) findViewById(R.id.operasional);
        kontak = (TextView) findViewById(R.id.kontak);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        pbar = (ProgressBar) findViewById(R.id.pbar);

        Intent i = getIntent();

        nama.setText(i.getStringExtra("nama"));
        alamat.setText(i.getStringExtra("alamat"));
        String gambar = i.getStringExtra("gambar");
        jenis.setText(i.getStringExtra("jenis"));
        operasional.setText(i.getStringExtra("operasional"));
        kontak.setText(i.getStringExtra("kontak"));
        deskripsi.setText(i.getStringExtra("deskripsi"));

        loadImageFromURL(url + gambar);
    }

    private void loadImageFromURL(String url) {

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.loading).cacheInMemory()
                .cacheOnDisc().build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(url, image, options,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        pbar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        pbar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        pbar.setVisibility(View.VISIBLE);
                    }
                });
    }
}
