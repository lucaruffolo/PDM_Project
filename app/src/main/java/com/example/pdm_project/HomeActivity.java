package com.example.pdm_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{



    public static boolean NIGHT_MODE = false;
    /*
    From a Fragment:

    Intent intent = new Intent(getActivity(), mFragmentFavorite.class);
    startActivity(intent);

    From an Activity

    Intent intent = new Intent(this, mFragmentFavorite.class);
    startActivity(intent);
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.FragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.menuRightMode:
                if (getDelegate().getLocalNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    showMessage("Tema Light impostato!");
                    NIGHT_MODE = false;
                } else{
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    showMessage("Tema Night impostato!");
                    NIGHT_MODE = true;
                }
                return true;
            case R.id.menuRightInfo:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                return true;
            case R.id.deleteAll:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Eliminare tutti i DATI?");
                b.setMessage("Sei sicuro di voler eliminare l'intero database?");
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showMessage("Database Eliminato");
                        MainActivity.db.deleteAllData();
                        finish();
                        startActivity(getIntent());
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                b.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}