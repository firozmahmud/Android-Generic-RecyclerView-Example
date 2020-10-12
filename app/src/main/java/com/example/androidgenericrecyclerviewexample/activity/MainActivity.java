package com.example.androidgenericrecyclerviewexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import com.example.androidgenericrecyclerviewexample.R;
import com.example.androidgenericrecyclerviewexample.adapter.ItemListAdapter;
import com.example.androidgenericrecyclerviewexample.databinding.ActivityMainBinding;
import com.example.androidgenericrecyclerviewexample.model.Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemListAdapter.ItemClick {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        List<Model> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Model model = new Model();
            model.setId(i);
            model.setName("Demo Name " + i);

            if (i % 3 == 0) {
                model.setType("chapter");
            } else if (i % 2 == 0) {
                model.setType("audio");
            } else {
                model.setType("video");
            }


            list.add(model);
        }

        ItemListAdapter adapter = new ItemListAdapter(this, list);
        binding.rvItemList.setAdapter(adapter);
    }

    @Override
    public void itemClick(Model model) {

        Toast.makeText(this, model.getName() + " , " + model.getType(), Toast.LENGTH_SHORT).show();
    }
}