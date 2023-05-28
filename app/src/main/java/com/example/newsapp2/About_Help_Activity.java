package com.example.newsapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class About_Help_Activity extends AppCompatActivity {

    LinearLayout ll_help;
    TextView tv_about_text;
    TextView tv_main_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_help);

        tv_about_text=findViewById(R.id.tv_about_text);
        ll_help=findViewById(R.id.ll_help);
        tv_main_title=findViewById(R.id.main_title);

        Intent intent=getIntent();
        String type=intent.getStringExtra("TYPE");
        if(intent.getExtras()!=null)
        {
            if(type.equals("ABOUT"))
            {
                ll_help.setVisibility(View.GONE);
                tv_about_text.setVisibility(View.VISIBLE);
                tv_main_title.setText("About");
            }
            else
            {
                ll_help.setVisibility(View.VISIBLE);
                tv_about_text.setVisibility(View.GONE);
                tv_main_title.setText("Help");
            }
        }

    }
}