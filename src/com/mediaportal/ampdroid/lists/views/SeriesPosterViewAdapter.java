package com.mediaportal.ampdroid.lists.views;

import java.io.File;

import com.mediaportal.ampdroid.data.Series;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.Utils;

public class SeriesPosterViewAdapter implements ILoadingAdapterItem {
   private Series mSeries;

   public SeriesPosterViewAdapter(Series _series) {
      super();
      this.mSeries = _series;
   }

   @Override
   public String getImage() {
      return mSeries.getCurrentPosterUrl();
   }
   
   @Override
   public String getImageCacheName() {
      String fileName = Utils.getFileNameWithExtension(mSeries.getCurrentPosterUrl(), "\\");
      return "Series" + File.separator + mSeries.getId() + File.separator + fileName;
   }

   @Override
   public String getSubText() {
      return "";
   }

   @Override
   public String getText() {
      return "";
   }

   @Override
   public int getTextColor() {
      return 0;
   }

   @Override
   public String getTitle() {
      return mSeries.getPrettyName();
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
      return ViewTypes.ThumbView.ordinal();
   }

   @Override
   public int getXml() {
      return 0;
   }
   
   @Override
   public Object getItem() {
      return mSeries;
   }



}