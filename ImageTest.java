package com.ykai.asncytasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

/**
 * Created by yangyankai on 2015/11/28.
 */
//http://img2.3lian.com/2014/f6/178/d/32.jpg
public class ImageTest extends Activity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private static String URL = "http://img2.3lian.com/2014/f6/178/d/32.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image);
        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        new MyAsyncTask().execute(URL);//传递进入参数

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // 取出参数 params
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try {
                //访问网络的操作
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                //解析输入流
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回 bitmap
            return bitmap;
        }
    }
}
