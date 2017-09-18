package com.example.galpp;

/**
 * Created by Vikram Udyawer on 9/14/2017.
 *
 *  {@link Photo} represents information of the Photos in the phone's gallery.
 *  Each object has 4 properties: Photo ID, Date Added, Orientation and File Path
 */

public class Photo {

    /** Photo ID of the Photo from the MediaStore */
    private String mPhotoId;

    /** Date Added of the Photo from the MediaStore */
    private String mPhotoDateAdded;

    /** Orientation of the Photo from the MediaStore */
    private String mPhotoOrientation;

    /** File Path of the Photo from the MediaStore */
    private String mPhotoFilePath;

    /**
     * Create a new Photo object (Constructor).
     *
     * @param vPhotoId is the ID of the Photo in the phone's gallery
     * @param vPhotoDateAdded is the date added of the photo
     * @param vPhotoOrientation is the orientation of the photo
     * @param vPhotoFilePath is the file path of the photo
     *
     * */
    public Photo(String vPhotoId, String vPhotoDateAdded, String vPhotoOrientation, String vPhotoFilePath){
        mPhotoId = vPhotoId;
        mPhotoDateAdded = vPhotoDateAdded;
        mPhotoOrientation = vPhotoOrientation;
        mPhotoFilePath = vPhotoFilePath;
    }

    /**
     * Get the Photo Id
     * @return mPhotoId
     */
    public String getPhotoId(){
        return mPhotoId;
    }

    /**
     * Get the Date the Photo was Added
     * @return mPhotoDateAdded
     */
    public String getPhotoDateAdded() {
        return mPhotoDateAdded;
    }

    /**
     * Get the orientation of the photo
     * @return mPhotoOrientation
     */
    public String getPhotoOrientation() {
        return mPhotoOrientation;
    }

    /**
     * Get the file path of the photo
     * @return mPhotoFilePath
     */
    public String getPhotoFilePath() {
        return mPhotoFilePath;
    }
}
