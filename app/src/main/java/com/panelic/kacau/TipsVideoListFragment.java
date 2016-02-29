package com.panelic.kacau;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.panelic.kacau.TipsYouTubeContent;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;


/**
 * Created by Wahyu on 10/01/2016.
 */
public class TipsVideoListFragment extends ListFragment {

    /**
     * Empty constructor
     */
    public TipsVideoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new TipsVideoListAdapter(getActivity()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        final Context context = getActivity();
        final String DEVELOPER_KEY = getString(R.string.DEVELOPER_KEY);
        final TipsYouTubeContent.YouTubeVideo video = TipsYouTubeContent.ITEMS.get(position);

        switch (position) {
            case 0:
                //Check whether we can actually open YT
                if (YouTubeIntents.canResolvePlayVideoIntent(context)) {
                    //Opens the video in the YouTube app
                    startActivity(YouTubeIntents.createPlayVideoIntent(context, video.id));
                }
                break;
            case 1:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent1 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent1.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent1);
                break;
            case 2:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent2 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent2.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent2);
                break;
            case 3:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent3 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent3.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent3);
                break;
            case 4:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent4 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent4.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent4);
                break;
            case 5:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent5 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent5.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent5);
                break;
            case 6:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent6 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent6.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent6);
                break;
            case 7:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent7 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent7.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent7);
                break;
            case 8:
                //Opens in the the custom Lightbox activity
                final Intent lightboxIntent8 = new Intent(context, TipsCustomLightboxActivity.class);
                lightboxIntent8.putExtra(TipsCustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(lightboxIntent8);
                break;


        }
    }
}
