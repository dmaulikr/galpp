package com.example.galpp;

import android.database.Cursor;

/**
 * Created by witwiki on 9/15/2017.
 */

public class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * A private constructor is created so no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /** Make a Media Store request */
    private static void getAllImages(){
        Cursor mCursor = getContentResolver.query
    }

    /** Load low resolution bitmaps from the MediaStore request */

    /** Create reasonable size thumbnails while orienting them properly */

    /** To speed up the process use a Cache of Thumbnails */

    /** Wrapper method encasing all the steps above */

}
