package com.noviceModDev.extrackapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

//import edu.ucdenver.salimlakhani.phonebook.databinding.ActivityMainBinding;
//import com.noviceModDev.extrackapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //  binding = ActivityMainBinding.inflate(getLayoutInflater());
        //  setContentView(binding.getRoot());
        //  setSupportActionBar(binding.toolbar);
    }
}