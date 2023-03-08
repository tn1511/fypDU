package com.example.fypdu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fypdu.databinding.ActivityDashboardBinding;
import com.example.fypdu.databinding.ActivityMainBinding;

public class dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainor,fragment);
        fragmentTransaction.commit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new fragments.dashboard());


        binding.navBar.setOnItemSelectedListener(item -> {

        switch (item.getItemId()){
            case R.id.ic_home:
                replaceFragment(new fragments.dashboard());
                break;
            case R.id.ic_group:
                replaceFragment(new fragments.groups());
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
}