package com.app.foodapp.Logic.OrderConfirmed;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.foodapp.Logic.MainActivityMenu.Menu;
import com.app.foodapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class OrderConfirmed extends AppCompatActivity {

    static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=3";
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);

        name = findViewById(R.id.tvOrderConfirmedBook);
        GetBooksAsync task = new GetBooksAsync();
        task.execute(REQUEST_URL);

        final Button button = findViewById(R.id.btnOrderConfirmedBackToMenu);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Menu.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null)
            return jsonResponse;

        HttpURLConnection urlConnection = null;
        InputStream is = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                is = urlConnection.getInputStream();
                jsonResponse = readFromStream(is);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (is != null)
                is.close();
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream is) throws IOException {
        StringBuilder output = new StringBuilder();
        if (is != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(is, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private class GetBooksAsync extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            String jsonResponse = "";
            try {
                url = new URL(strings[0]);
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject root = null;
            try {
                root = new JSONObject(s);
                JSONArray books = root.getJSONArray("items");
                JSONObject firstBook = books.getJSONObject(0);
                JSONObject volumeInfo = firstBook.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                String author = volumeInfo.getJSONArray("authors").getString(0);
                name.setText("Title: " + title + ", Author: " + author);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}


