package com.example.galpp;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Vikram Udyawer on 9/7/2017.
 *
 * {@link ImageAdapter} is a {@link ArrayAdapter} that can provide the layout for
 * each photo in the grid based on the MediaStore Images which is a list of photo objects.
 */

public class ImageAdapter extends ArrayAdapter<Photo> {

    /** Variables to store states inside onSaveInstanceState */
    private static final int NFRAMES = 256;

    /**
     * This is a custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the photos
     * to populate into the grid.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param images  A List of Image objects to display in a grid
     */
    public ImageAdapter(Context context, ArrayList<Photo> images) {
        /** Initialize the ArrayAdapter's internal storage for the context and the list.
         * The second argument is used when the ArrayAdapter is populating a single ImageView.
         * Because this is a custom adapter, the adapter is not
         * going to use this second argument, so it can be any value. Here, we used 0.
         */
        super(context, 0, images);
    }


    /** Getter to get the number photo frames */
    @Override
    public int getCount() {
        return NFRAMES;
    }
    /** Get the data item associated with the specified position in the data set. */
    @Override
    public Photo getItem(int i) {
        return null;
    }
    /** Get the row id associated with the specified position in the list. */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position              The position in the list of data that should be displayed in the
     *                              grid view.
     * @param convertView           The recycled view to populate.
     * @param parentViewGroup       The parent ViewGroup that is used for inflation.
     * @return                      The View for the position in the AdapterView.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parentViewGroup) {

        /** Check if the existing view is being reused, otherwise inflate the view */
        View gridView = convertView;
        if (gridView == null) {
            gridView = LayoutInflater.from(getContext()).inflate(
                    R.layout.photo_frame, parentViewGroup, false);
        }

        /** Get the {@link Photo} object located at this position in the list */
        Photo currentPhoto = getItem(position);

        return null;
    }
}
