package com.williamnb.readlistenapp.features.home;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.models.Game;
import com.williamnb.readlistenapp.data.models.News;
import com.williamnb.readlistenapp.data.models.SliderItem;

import java.util.ArrayList;

public class HomeViewModel extends BaseViewModel {
    /**
     *
     **/
    public ArrayList<SliderItem> getDataSlide(){
        ArrayList<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.slide_1));
        sliderItems.add(new SliderItem(R.drawable.slide_2));
        sliderItems.add(new SliderItem(R.drawable.slide_3));
        sliderItems.add(new SliderItem(R.drawable.slide_4));

        return sliderItems;
    }

    /**
     *
     **/
    public ArrayList<Game> mockDataGames() {
        ArrayList<Game> gameArrayList = new ArrayList<>();

        gameArrayList.add(new Game(R.drawable.co_vua, R.string.co_vua));
        gameArrayList.add(new Game(R.drawable.co_tuong, R.string.co_tuong));
        gameArrayList.add(new Game(R.drawable.de_che, R.string.de_che));
        gameArrayList.add(new Game(R.drawable.ban_sung, R.string.csgo));
        gameArrayList.add(new Game(R.drawable.dat_bom, R.string.dat_bom));

        return gameArrayList;
    }

    /**
     *
     **/
    public ArrayList<News> mockDataNews() {
        ArrayList<News> newsArrayList = new ArrayList<>();

        newsArrayList.add(new News(R.drawable.thumbnail_app, R.string.content_sap_ra_mat, R.string.date_01042022, R.string.numberView_10_2k));
        newsArrayList.add(new News(R.drawable.thumbnail_kysu_vietnam, R.string.content_giao_duc, R.string.date_05062023, R.string.numberView_88k));
        newsArrayList.add(new News(R.drawable.thumbnail_nasa, R.string.content_tuyen_dung, R.string.date_08062026, R.string.numberView_88k));
        newsArrayList.add(new News(R.drawable.thumbnail_ronaldo_messi, R.string.content_bong_da, R.string.date_18062028, R.string.numberView_10_2k));
        newsArrayList.add(new News(R.drawable.thumbnail_vietnam_worlcup, R.string.content_the_thao, R.string.date_01042022, R.string.numberView_56k));

        return newsArrayList;
    }
}
