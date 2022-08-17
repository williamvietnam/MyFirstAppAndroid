package com.williamnb.readlistenapp.utilities.callback;

import com.williamnb.readlistenapp.data.local.models.Game;
import com.williamnb.readlistenapp.data.local.models.News;
import com.williamnb.readlistenapp.data.local.models.SliderItem;

public interface HomeCallBack {

    void onBannerClicked(SliderItem item);

    void onFeaturedGamesClicked(Game item);

    void onFeaturedNewsClicked(News item);
}
