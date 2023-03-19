package com.example.fypdu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fypdu.databinding.ActivityDashboardBinding;
import com.example.fypdu.databinding.ActivityMainBinding;

public class dashboard extends AppCompatActivity implements View.OnClickListener {


    ActivityDashboardBinding binding;
    private Button createGroup;

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainor,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {


        createGroup = (Button) findViewById(R.id.createGroupBtn);
        createGroup.setOnClickListener(this);
        switch (v.getId()){
            case R.id.createGroupBtn:
                createGroup();
                break;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        replaceFragment(new fragments.groups());


        binding.navBar.setOnItemSelectedListener(item -> {

        switch (item.getItemId()){
            case R.id.ic_home:
                replaceFragment(new fragments.groups());
                break;
            case R.id.ic_group:
                replaceFragment(new fragments.home());
                break;
            case R.id.ic_settings:
                replaceFragment(new fragments.settings());
                break;
            case R.id.ic_profile:
                replaceFragment(new fragments.profile());
                break;

        }
            return true;
        });





    }

    private void createGroup() {

        Toast.makeText(dashboard.this, "testtttttttt", Toast.LENGTH_LONG).show();
    }
}