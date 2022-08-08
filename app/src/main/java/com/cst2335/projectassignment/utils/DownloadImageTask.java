package com.cst2335.projectassignment.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

// https://stackoverflow.com/a/9288544/17193562
// TODO: Add JavaDoc Comment
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView imageView;

    // TODO: Add JavaDoc Comment
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
