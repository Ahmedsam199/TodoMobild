package com.example.notbasictodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getSupportActionBar().hide();
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homefrag:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new Home()).commit();

                break;
            case R.id.todofrag:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new Todo()).commit();
                break;
            case R.id.aboutfrag:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new Info()).commit();
                break;
        }
        return  true;
    }
});

       }
    public interface FragmentChangeListener
    {
        public void replaceFragment(Fragment fragment);
    }
}