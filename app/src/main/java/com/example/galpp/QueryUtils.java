package com.example.galpp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import java.util.List;
import static android.content.ContentValues.TAG;

/**
 * Created by witwiki on 9/15/2017.
 */

public final class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * A private constructor is created so no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /** Make a Media Store request to fetch images (default resolution) */
    private static void fetchImages(Context context){
        String[] mProjection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.ORIENTATION
        };
        Cursor mCursor = context.getContentResolver()
                .query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        mProjection,
                        null,
                        null,
                        MediaStore.Images.Media.DATE_ADDED + " DESC"); // Most Recently Added

        mCursor.moveToFirst();
        while(!mCursor.isAfterLast()) {
            Log.d(TAG, " - _ID : " + mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media._ID)));
            Log.d(TAG, " - File Name : " + mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
            Log.d(TAG, " - Image Orientation: " + mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION)));
            mCursor.moveToNext();
        }
        mCursor.close();
    }

    /*
    // Make a Media Store request to fetch thumbnails (Don't Think I need this method)
    private static void fetchThumbnails(Context context){
        String[] mProjection = {MediaStore.Images.Thumbnails.DATA}
        Cursor mCursor = context.getContentResolver()
                .query(
                        MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                        mProjection,
                        MediaStore.Images.Thumbnails.IMAGE_ID + "=?" ,
                        new String[]{id},
                        null);

        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()){
            Log.d(TAG, "  - Thumbnail File Path : " + mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
            Log.d(TAG, "  - Thumbnail Type : " + mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Thumbnails.KIND));
            mCursor.moveToNext();
        }
        mCursor.close();
    } */


    /** Load low resolution bitmaps from the MediaStore request */
    private static Bitmap decodeSampledBitmap(String image_file_path, int reqWidth, int reqHeight) {
        Log.v(TAG, "creating bitmap for " + image_file_path);

        long start = android.os.SystemClock.uptimeMillis();

        final BitmapFactory.Options options = getBitmapBounds(image_file_path);

        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        Bitmap sampled = BitmapFactory.decodeFile(image_file_path, options);
        long sampled_time = android.os.SystemClock.uptimeMillis();
        Log.v(TAG, "created sampled bitmap for " + image_file_path + ", took " + (sampled_time-start) + "ms");

        Bitmap oriented = fixOrientation(sampled, image_file_path);
        long orientation_fixed_time = android.os.SystemClock.uptimeMillis();
        Log.v(TAG, "fixed orientation for " + image_file_path + ", took " + (orientation_fixed_time-sampled_time) + "ms");

        Bitmap final_bitmap = ThumbnailUtils.extractThumbnail(oriented, reqWidth, reqHeight);
        long end = android.os.SystemClock.uptimeMillis();
        Log.v(TAG, "finished resizing bitmap for " + image_file_path + ", took " + (end-orientation_fixed_time) + "ms");
        Log.v(TAG, "finished creating bitmap for " + image_file_path + ", took " + (end-start) + "ms");

        return final_bitmap;
    }

    /** Create reasonable size thumbnails while orienting them properly */

    /** To speed up the process use a Cache of Thumbnails */

    /** Wrapper method encasing all the steps above */
    public static List<Photo> fetchImages(){

    }

}
