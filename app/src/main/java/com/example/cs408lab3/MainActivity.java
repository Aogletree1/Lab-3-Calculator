package com.example.cs408lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.cs408lab3.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}