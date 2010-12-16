package com.mediaportal.remote.activities.media;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaportal.remote.R;
import com.mediaportal.remote.api.RemoteHandler;
import com.mediaportal.remote.data.MovieFull;

public class TabMovieDetailsActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabmoviedetailsactivity);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int movieId = extras.getInt("movie_id");

			RemoteHandler service = RemoteHandler.getCurrentRemoteInstance();
			MovieFull movie = service.getMovieDetails(movieId);

			if (movie != null) {
				TextView mMovieName = (TextView) findViewById(R.id.TextViewMovieName);
				mMovieName.setText(movie.getName());

				TextView mMovieOverview = (TextView) findViewById(R.id.TextViewOverview);
				mMovieOverview.setText(movie.getSummary());
				
				ImageView moviePoster = (ImageView) findViewById(R.id.ImageViewMoviePoster);
				
				Bitmap bmImg = service.getBitmap(movie.getCoverThumbPath());
				moviePoster.setImageBitmap(bmImg);
			}
		} else {// activity called without movie id (shouldn't happen ;))

		}
	}
}