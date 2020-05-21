package com.example.registration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class backgroundWorker extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context context;
    backgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String name = params[1];
        String college = params[2];
        String phone = params[3];
        String gmail = params[4];

        String type = params[0];
        String RegisterURL = "https://uruguayan-beginner.000webhostapp.com/login.php";
        if(type.equals("Register")) {
            try {
                URL url = new URL(RegisterURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                URLEncoder.encode("college","UTF-8")+"="+URLEncoder.encode(college,"UTF-8")+"&"+
                URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                URLEncoder.encode("gmail","UTF-8")+"="+URLEncoder.encode(gmail,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Registered");
    }

    protected void onPostExecute (String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    protected void onProgressUpdate () {
        super.onProgressUpdate();
    }
}
