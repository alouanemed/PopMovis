package com.malouane.popularmovies.ui.detail;

import com.malouane.popularmovies.data.entity.MovieTrailerEntity;

interface TrailerListCallback {
    void onTrailerClicked(MovieTrailerEntity trailerEntity);
}
