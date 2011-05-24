package com.mediaportal.ampdroid.lists;

import com.mediaportal.ampdroid.lists.LazyLoadingAdapter.ViewHolder;

import android.view.View;


public interface ILoadingAdapterItem {
   /**
    * The url of the image that should be loaded on demand
    * @return Url to the image
    */
   LazyLoadingImage getImage();
	
	/**
	 * The resource image that will be shown during loading of the url 
	 * specified in getImage()
	 * @return Resource id of the loading image
	 */
	int getLoadingImageResource();
	
	  /**
    * The resource image that will be shown if no url is
    * specified in getImage() or loading fails
    * @return Resource id of the default image
    */
	int getDefaultImageResource();
	
	/**
	 * Type of the adapter item, needed for handling multiple items
	 * in a ListView. Has to be unique within the context of a single
	 * ILazyLoadingAdapter instance
	 * @return unique type of this adapter item
	 */
	int getType();
	
	/**
	 * The XML layout of the item
	 * @return Resource id of the XML from which the item is inflated
	 */
	int getXml();
	
	/**
	 * The object which fills the data for this adapter item
	 * @return
	 */
	Object getItem();
	
	/**
	 * Create a ViewHolder for this item
	 * @param _view The view for which a ViewHolder is created
	 * @return A ViewHolder which holds references for the relevant controls
	 */
	ViewHolder createViewHolder(View _view);
	
	/**
	 * Fills the view (through a ViewHolder) with the information of the object
	 * @param _holder The ViewHolder that holds the references to the controls of the view
	 */
	void fillViewFromViewHolder(ViewHolder _holder);
}