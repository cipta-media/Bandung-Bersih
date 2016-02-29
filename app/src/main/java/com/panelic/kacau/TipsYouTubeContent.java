package com.panelic.kacau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wahyu on 10/01/2016.
 */
public class TipsYouTubeContent {

    /**
     * An array of YouTube videos
     */
    public static List<YouTubeVideo> ITEMS = new ArrayList<>();

    /**
     * A map of YouTube videos, by ID.
     */
    public static Map<String, YouTubeVideo> ITEM_MAP = new HashMap<>();

    static {
        addItem(new YouTubeVideo("vikYRIb7Z4o", "Cara Membuat Cover Hp Minion Dari Kain Flanel"));
        addItem(new YouTubeVideo("YEwwIwoOatE", "Cara Membuat Penyangga HP dari Stik Es Krim"));
        addItem(new YouTubeVideo("msC-sqrGV5U", "Cara Membuat Tas Dari Celana Jean Bekas"));
        addItem(new YouTubeVideo("g5VW5r0MhHA", "Cara Membuat Kapal Mainan Dari Botol Bekas"));
        addItem(new YouTubeVideo("HN0BrkEK-s4", "Cara Membuat Kotak Pensil Minion"));
        addItem(new YouTubeVideo("uM37RY_h4ig", "Cara Membuat Lampion Dari Kertas Kardus"));
        addItem(new YouTubeVideo("eptK6iyTtU0", "Dsign - Handcraft - Meja dari ban bekas"));
        addItem(new YouTubeVideo("dk7yn3aFJxg", "Dsign - Handcraft - Gantungan Ranting"));
        addItem(new YouTubeVideo("kKIsT16pAZw", "Dsign - DIY - Tempat Duduk Majalah"));
    }

    private static void addItem(final YouTubeVideo item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A POJO representing a YouTube video
     */
    public static class YouTubeVideo {
        public String id;
        public String title;

        public YouTubeVideo(String id, String content) {
            this.id = id;
            this.title = content;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
