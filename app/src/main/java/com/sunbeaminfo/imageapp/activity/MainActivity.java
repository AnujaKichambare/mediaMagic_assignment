package com.sunbeaminfo.imageapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.sunbeaminfo.imageapp.R;
import com.sunbeaminfo.imageapp.adapter.PhotoAdapter;
import com.sunbeaminfo.imageapp.model.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PhotoAdapter adapter;
    ArrayList<Photo> photos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        adapter= new PhotoAdapter(this,photos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        new FetchData().execute();


    }
    public class FetchData extends AsyncTask<Void,Void,Void> {
        //        ArrayList<Integer> idarr=new ArrayList<>();
        String data = "";


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url1 = new URL("https://picsum.photos/list");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                InputStream inputStream1 = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1));


                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }


                JSONArray JA = new JSONArray(data);
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject JO = (JSONObject) JA.get(i);

                    Photo photo = new Photo();
                    photo.setId(JO.getInt("id"));
                    photo.setAuthor(JO.get("author").toString());



                    int id=JO.getInt("id");
                    String url = "https://picsum.photos/300/300?image=";
                    String imagemageUrl = url.concat(Integer.toString(id));

                    Bitmap bitmap;

                    bitmap=BitmapFactory.decodeStream((InputStream) new URL(imagemageUrl).getContent());
                    photo.setImage(bitmap);

                    photos.add(photo);

                    Log.e("author", JO.get("author").toString());
                    Log.e("imageurl",imagemageUrl);
                    Log.e("bitmap",bitmap.toString());



                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }


}
