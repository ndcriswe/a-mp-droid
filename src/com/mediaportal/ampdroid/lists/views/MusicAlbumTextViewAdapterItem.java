package com.mediaportal.ampdroid.lists.views;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.mediaportal.ampdroid.R;
import com.mediaportal.ampdroid.data.MusicAlbum;
import com.mediaportal.ampdroid.lists.ILoadingAdapterItem;
import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;
import com.mediaportal.ampdroid.lists.LazyLoadingImage;
import com.mediaportal.ampdroid.lists.SubtextViewHolder;

public class MusicAlbumTextViewAdapterItem implements ILoadingAdapterItem {
   private MusicAlbum mAlbum;
   private boolean mShowArtist;
   public MusicAlbumTextViewAdapterItem(MusicAlbum _album, boolean _showArtist) {
      super();
      mAlbum = _album;
      mShowArtist = _showArtist;
   }
   @Override
   public LazyLoadingImage getImage() {
      return null;
   }

   @Override
   public int getLoadingImageResource() {
      return 0;
   }

   @Override
   public int getDefaultImageResource() {
      return 0;
   }

   @Override
   public int getType() {
      return 0;
   }

   @Override
   public int getXml() {
      return R.layout.listitem_text;
   }

   @Override
   public Object getItem() {
      return mAlbum;
   }

   @Override
   public ViewHolder createViewHolder(View _view) {
      SubtextViewHolder holder = new SubtextViewHolder();
      holder.text = (TextView) _view.findViewById(R.id.TextViewText);
      return holder;
   }

   @Override
   public void fillViewFromViewHolder(ViewHolder _holder) {
      SubtextViewHolder holder = (SubtextViewHolder)_holder;

      if (holder.text != null) {
         String artistString = "";
         if(mShowArtist && mAlbum.getAlbumArtists() != null){
            if(mAlbum.getAlbumArtists().length == 0){
               artistString = " - ...";
            }
            else if(mAlbum.getAlbumArtists().length == 1)
            {
               artistString = " - " + mAlbum.getAlbumArtists()[0];
            }
            else{
               artistString = " - " + mAlbum.getAlbumArtists()[0] + ", ...";
            }
         }
         holder.text.setText(mAlbum.getTitle() + artistString);
         holder.text.setTextColor(Color.WHITE);
      }
   }

}