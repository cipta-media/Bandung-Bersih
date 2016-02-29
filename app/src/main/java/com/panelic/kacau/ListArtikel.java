package com.panelic.kacau;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.panelic.kacau.image_artikel.ImageLoader;
import com.panelic.kacau.parser_artikel.RSSFeed;

/**
 * Created by Wahyu on 08/01/2016.
 */
public class ListArtikel extends AppCompatActivity {

    Application myApp;
    RSSFeed feed;
    ListView lv;
    CustomListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.feed_list_artikel);

        myApp = getApplication();

        // Get feed form the file
        feed = (RSSFeed) getIntent().getExtras().get("feed");

        // Initialize the variables:
        lv = (ListView) findViewById(R.id.listView);
        lv.setVerticalFadingEdgeEnabled(true);

        // Set an Adapter to the ListView
        adapter = new CustomListAdapter(this);
        lv.setAdapter(adapter);

        // Set on item click listener to the ListView
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // actions to be performed when a list item clicked
                int pos = arg2;

                Bundle bundle = new Bundle();
                bundle.putSerializable("feed", feed);
                Intent intent = new Intent(ListArtikel.this,
                        DetailArtikel.class);
                intent.putExtras(bundle);
                intent.putExtra("pos", pos);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.imageLoader.clearCache();
        adapter.notifyDataSetChanged();
    }

    class CustomListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        public ImageLoader imageLoader;

        public CustomListAdapter(ListArtikel activity) {

            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageLoader = new ImageLoader(activity.getApplicationContext());
        }

        @Override
        public int getCount() {

            // Set the total list item count
            return feed.getItemCount();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Inflate the item layout and set the views
            View listItem = convertView;
            int pos = position;
            if (listItem == null) {
                listItem = layoutInflater.inflate(R.layout.list_item_artikel, null);
            }

            // Initialize the views in the layout
            ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
            // set the ImageView opacity to 50%
            TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
            TextView tvDate = (TextView) listItem.findViewById(R.id.date);

            // Set the views in the layout
            imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
            tvTitle.setText(feed.getItem(pos).getTitle());
            tvDate.setText(feed.getItem(pos).getDate());

            return listItem;
        }

    }
}
