package com.example.newsapp2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnologyFragment extends Fragment {

    ArrayList<ModelClass> modelClassList;
    private RecyclerView recyclerViewoftech;
    Adapter adapter;
    String api="794d8076e50f42e9afd022bd5571ecd8";
    String Country="in";
    String Catogory="technology";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.technologyfragment,null);
        recyclerViewoftech=v.findViewById(R.id.recycleroftech);

        modelClassList=new ArrayList<>();
        recyclerViewoftech.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewoftech.setHasFixedSize(true);
        adapter=new Adapter(getContext(),modelClassList);
        recyclerViewoftech.setAdapter(adapter);


        findNews();



        return v;

    }

    private void findNews() {

        ApiUtilities.getAPIInterface().getcategoryNews(Country,Catogory,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {

                Log.d("TAG","Done4");
                if(response.isSuccessful())
                {
                    //  Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
                    modelClassList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

                else
                {
                    Toast.makeText(getContext(),"Not Done",Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(),response.body().getStatus(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }
}
