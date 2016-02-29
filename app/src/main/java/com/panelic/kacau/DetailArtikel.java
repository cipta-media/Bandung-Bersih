package com.panelic.kacau;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.panelic.kacau.parser_artikel.RSSFeed;

/**
 * Created by Wahyu on 08/01/2016.
 */
public class DetailArtikel extends FragmentActivity {

    RSSFeed feed;
    int pos;
    private DescAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_artikel);

        // Get the feed object and the position from the Intent
        feed = (RSSFeed) getIntent().getExtras().get("feed");
        pos = getIntent().getExtras().getInt("pos");

        // Initialize the views
        adapter = new DescAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager);

        // Set Adapter to pager:
        pager.setAdapter(adapter);
        pager.setCurrentItem(pos);

    }

    public class DescAdapter extends FragmentStatePagerAdapter {
        public DescAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return feed.getItemCount();
        }

        @Override
        public Fragment getItem(int position) {

            ArtikelFragment frag = new ArtikelFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("feed", feed);
            bundle.putInt("pos", position);
            frag.setArguments(bundle);

            return frag;

        }

    }
}
