package com.example.galpp;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Vikram Udyawer on 9/7/2017.
 *
 * {@link ImageAdapter} is a {@link BaseAdapter} that can provide the layout for
 * each photo in a grid based on a data source which is a list of photo objects.
 */

public class ImageAdapter extends BaseAdapter {

    // Variables to store states inside onSaveInstanceState
    private static final int NFRAMES = 256;
    private Context mContext;

    //  Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    // Getter to get the number photo frames
    @Override
    public int getCount() {
        return NFRAMES;
    }
    // Get the data item associated with the specified position in the data set.
    @Override
    public Object getItem(int i) {
        return null;
    }
    // Get the row id associated with the specified position in the list.
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
        return null;
    }
}
