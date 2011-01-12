package com.mediaportal.ampdroid.lists.views;


import java.io.File;

import com.mediaportal.ampdroid.data.Movie;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.Utils;
import com.mediaportal.ampdroid.R;
public class MoviePosterViewAdapter implements ILoadingAdapterItem {

   private Movie mMovie;
   public MoviePosterViewAdapter(Movie _movie){
      mMovie = _movie;
   }
   
   @Override
   public String getImageCacheName() {
      String fileName = Utils.getFileNameWithExtension(mMovie.getCoverThumbPath(), "\\");
      return "Movies" + File.separator + mMovie.getId() + File.separator + fileName;
   }
   
   
   @Override
   public String getImage() {
      return mMovie.getCoverThumbPath();
   }

   @Override
   public String getSubText() {
      return mMovie.getName();
   }

   @Override
   public String getText() {
      return null;
   }

   @Override
   public int getTextColor() {
      return 0;
   }

   @Override
   public String getTitle() {
      return mMovie.getName();
   }


   @Override
   public int getSubTextColor() {
      return 0;
   }


   @Override
   public int getTitleColor() {
      return 0;
   }


   @Override
   public int getType() {
      return ViewTypes.PosterView.ordinal();
   }


   @Override
   public int getXml() {
      return R.layout.listitem_poster;
   }


   @Override
   public Object getItem() {
      return mMovie;
   }




}
