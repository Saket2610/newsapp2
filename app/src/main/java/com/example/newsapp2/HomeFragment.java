package com.example.newsapp2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class HomeFragment extends Fragment {

    ArrayList<ModelClass> modelClassList;
    Adapter adapter;
    String api = "794d8076e50f42e9afd022bd5571ecd8";
    String Country = "in";
    String Catogory = "sports";
    MainActivity mainActivity;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefragment, null);
        recyclerView = v.findViewById(R.id.recyclerofhome);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getContext(), modelClassList);
        recyclerView.setAdapter(adapter);
        mainActivity = (MainActivity) getActivity();


        final int[] state = new int[1];

        findNews();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state[0] = newState;

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.d("TAG_SCROLL", "" + dy);


                if (dy > 0 && (state[0] == 0 || state[0] == 2)) {
                    hideToolbar();
                } else if (dy < -10) {
                    showToolbar();
                }
            }
        });


        final int[] m = {1};

        mainActivity.mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m[0] == 1) {
                    mainActivity.mMyAppText.setVisibility(View.GONE);
                    mainActivity.mGetText.setVisibility(View.VISIBLE);
                    mainActivity.mSearch.setImageResource(R.drawable.ic_baseline_add_24);
                    mainActivity.mSearch.setRotation(45);
                    m[0] = 2;
                } else {
                    mainActivity.mMyAppText.setVisibility(View.VISIBLE);
                    mainActivity.mGetText.setVisibility(View.GONE);
                    mainActivity.mSearch.setImageResource(R.drawable.ic_baseline_search_24);
                    mainActivity.mSearch.setRotation(0);
                    m[0] = 1;
                }
            }
        });

        mainActivity.mGetText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        return v;

    }

    private void filter(String text) {

        Log.d("TAG_TEXT", text);
        ArrayList<ModelClass> filteredList;
        filteredList = new ArrayList<>();


        for (ModelClass item : modelClassList) {

            String str = item.getTitle();

            str = str.substring(0, 6);
            Log.d("TAG_TEXT", str);
            // checking if the entered string matched with any item of our recycler view.
            if (str.contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item);

            }
        }
        modelClassList.addAll(filteredList);
        adapter.notifyDataSetChanged();


    }

//    findNews fetch all news data from our api
    private void findNews() {

//        apiutilities to get the object of the retrofit
//        passing only 3 arguments
        ApiUtilities.getAPIInterface().getNews(Country, 100, api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {

                Log.d("TAG", "Done4");
                if (response.isSuccessful()) {
                    //  Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
                    modelClassList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Not Done", Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(),response.body().getStatus(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }


    private void hideToolbar() {
        mainActivity.mMainToolbarLayout.setVisibility(View.GONE);
    }


    private void showToolbar() {

        mainActivity.mMainToolbarLayout.setVisibility(View.VISIBLE);

    }
}
