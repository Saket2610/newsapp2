package com.example.newsapp2;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit=null;
 //   public static final String BAS_URL="https://newsdata.io/api/1/";
   // public static final String API="pub_363136285f581ef86fb945ed2c955961ce7";

    public static com.example.newsapp2.ApiInterface getAPIInterface()
    {
        if(retrofit==null)
        {
//            baseurl is present in the apiinterface so take that from it
//
            retrofit =new Retrofit.Builder().baseUrl(com.example.newsapp2.ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
           // Toast.makeText(getAPIInterface(),"Done",Toast.LENGTH_SHORT).show();

            Log.d("TAG","Done");

        }
        return retrofit.create(com.example.newsapp2.ApiInterface.class);
    }
}
