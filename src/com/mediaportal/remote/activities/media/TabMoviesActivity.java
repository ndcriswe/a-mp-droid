package com.mediaportal.remote.activities.media;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.mediaportal.remote.R;
import com.mediaportal.remote.activities.lists.ILoadingAdapterItem;
import com.mediaportal.remote.activities.lists.LazyLoadingAdapter;
import com.mediaportal.remote.activities.lists.views.MoviePosterViewAdapter;
import com.mediaportal.remote.api.DataHandler;
import com.mediaportal.remote.data.Movie;

public class TabMoviesActivity extends Activity {
	private ListView m_listView;
	private LazyLoadingAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabmoviesactivity);

		DataHandler service = DataHandler.getCurrentRemoteInstance();
		List<Movie> movies = service.getAllMovies();

		adapter = new LazyLoadingAdapter(this, R.layout.listitem_thumb);

		if (movies != null) {
			for (Movie m : movies) {
				adapter.AddItem(new MoviePosterViewAdapter(m));
			}
		}

		m_listView = (ListView) findViewById(R.id.ListViewVideos);
		m_listView.setAdapter(adapter);

		m_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Movie selectedMovie = (Movie) ((ILoadingAdapterItem) m_listView
						.getItemAtPosition(position)).getItem();

				Intent myIntent = new Intent(v.getContext(),
						TabMovieDetailsActivity.class);
				myIntent.putExtra("movie_id", selectedMovie.getId());
				startActivity(myIntent);

			}
		});
	}
}
