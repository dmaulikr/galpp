package com.example.galpp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    /** Request Code variable for READ_EXTERNAL_STORAGE permissions (Choice of a random integer as long as it is >=0) */
    private static final int READ_EXTERNAL_STORAGE_PERMISSIONS_CODE = 1;

    /** Adapter for the grid of images */
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Generic private method to request Dangerous Permissions (in this case, "READ_EXTERNAL_STORAGE") */
        getUserPermission(READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE_PERMISSIONS_CODE);

        /** Finds reference to the {@link GridView} in the layout */
        GridView imageGridView = (GridView) findViewById(R.id.photo_grid);

        /** Create a new adapter that takes an empty grid of images as input */
        mAdapter = new ImageAdapter(this, new ArrayList<Photo>());

        /** Set the adapter on the {@link GridView}
         *  so the list can be populated in the user interface
         */
        imageGridView.setAdapter(mAdapter);


        /** Ask for user permissions */


        /** Set an item click listener on the GridView, which sends an intent to our third activity
         *  that opens the image to view.
         */
        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /** Find the current photo that was clicked on */
                Photo currentPhoto = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Start the AsyncTask to fetch the earthquake data
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    /** Generic private method to request Dangerous Permissions (in this case, "READ_EXTERNAL_STORAGE") */
    private void getUserPermission(String requestedPerm, Integer requestedCode){
        //  Checking if the Build version is API greater than 22
        if ((Build.VERSION.SDK_INT > 22)){
            //  Checking to see with the Permission(s) is Granted or Not
            //  If Permission(s) is NOT GRANTED
            if (ContextCompat.checkSelfPermission(MainActivity.this, requestedPerm) != PackageManager.PERMISSION_GRANTED){
                Log.v("TAG","Permission is granted");
                // Check to see the user has denied permission(s) previously
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, requestedPerm)){
                    //  Ask permission again in the case the user has denied permissions previously (as it is an important Permission)
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{requestedPerm}, requestedCode);
                }
                //  If Permission was Not Denied previously, a request is made
                else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {requestedPerm}, requestedCode);
                }
            }
            //  If Permission(s) GRANTED we add a message to say the Permission was granted
            else {
                Toast.makeText(this, "" + requestedPerm + " is already granted", Toast.LENGTH_SHORT).show();
            }
        } else {
            //  If build is lower than 23 then Permission requested and granted during app install
            Log.v("TAG","Permission is granted");
        }
    }

    /**  Method to handle a requested permission */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //  Switch case statement is used to handle multiple request permission codes (if needed)
        switch (requestCode){
            //  Permission code for READ_EXTERNAL_STORAGE
            case READ_EXTERNAL_STORAGE_PERMISSIONS_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //  Permission Denied
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }






    /**
     * {@link AsyncTask} to perform the Adapter image loading on a background thread, and then
     * update the UI with the list of images.
     *
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. The task will take a String URL, and return an Earthquake. We won't do
     * progress updates, so the second generic is just Void.
     *
     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code,
     * without interfering with the responsiveness of the app.
     * Then onPostExecute() passes the result of doInBackground() method, but runs on the
     * UI thread, so it can use the produced data to update the UI.
     */
    private class LoadImagesAsyncTask extends AsyncTask<String, Void, List<Photo>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Photo}s as the result.
         */
        @Override
        protected List<Photo> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Photo> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of images data from a previous
         * query to MediaStore. Then we update the adapter with the new list of images,
         * which will trigger the {@link GridView} to re-populate its list items.
         */
        @Override
        protected void onPostExecute(List<Photo> data) {
            /** Clear the adapter of previous image data */
            mAdapter.clear();

            /**
             * If there is a valid list of {@link Photo}s, then add them to the adapter's
             * data set. This will trigger the {@link GridView} to update.
             */
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
