package com.example.newsapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // private RecyclerView recyclerView;
    Adapter adapter;
    String api = "794d8076e50f42e9afd022bd5571ecd8";
    String Country = "in";
    String Catogory = "sports";
    RelativeLayout mMainToolbarLayout;
    RelativeLayout mParent;
    ImageButton mSearch;
    TextView mMyAppText;
    EditText mGetText;
    TabLayout tabLayout;
    TabItem mhome, mscience, mentertainment, mtech, msports, mhealth;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    androidx.appcompat.widget.Toolbar mtoolbar;
    private ArrayList<ModelClass> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();

        mtoolbar = findViewById(R.id.toolbar);
        mMainToolbarLayout = findViewById(R.id.main_toolbar_layout);
        mParent = findViewById(R.id.parent);
        setSupportActionBar(mtoolbar);


        mhome = findViewById(R.id.home);
        mscience = findViewById(R.id.science);
        mtech = findViewById(R.id.technology);
        mhealth = findViewById(R.id.health);
        mentertainment = findViewById(R.id.entertainment);
        msports = findViewById(R.id.sports);
        viewPager = findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.include);
        mMyAppText = findViewById(R.id.myapptext);
        mSearch = findViewById(R.id.search);
        mGetText = findViewById(R.id.get_text);

        //  recyclerView=findViewById(R.id.recyclerview);
        modelClassList = new ArrayList<>();
        //    recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), modelClassList);
        // recyclerView.setAdapter(adapter);


        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        this code is for if we wanna swipe tablayout rather than tapping


        // findNews();


    }

    public void findNews() {


        ApiUtilities.getAPIInterface().getNews(Country, 100, api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {

                //   Log.d("TAG","Done4");
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    modelClassList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Done", Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(),response.body().getStatus(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent=new Intent(MainActivity.this,About_Help_Activity.class);
                intent.putExtra("TYPE","ABOUT");
                startActivity(intent);
                return true;
            case R.id.help:
                Intent intent1=new Intent(MainActivity.this,About_Help_Activity.class);
                intent1.putExtra("TYPE","HELP");
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}