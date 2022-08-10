package com.cst2335.projectassignment.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Used to set the image on a ImageView outside of the XML
 * <br>
 * Class created by <a href="https://stackoverflow.com/a/9288544/17193562">Android Developer on StackOverflow</a>
 *
 * @author <a href="https://stackoverflow.com/a/9288544/17193562">Android Developer on StackOverflow</a>
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView imageView;

    /**
     * Constructor for the DownloadImageTask class
     * @param imageView The {@link ImageView} to apply the image to
     */
    public DownloadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmapIcon = null;

        try {
            InputStream inputStream = new URL(urls[0]).openStream();
            bitmapIcon = BitmapFactory.decodeStream(inputStream);
        } catch (Exception exception) { exception.printStackTrace(); }

        return bitmapIcon;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
