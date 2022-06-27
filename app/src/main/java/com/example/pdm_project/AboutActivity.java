package com.example.pdm_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    private Button mail,github;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mail = (Button) findViewById(R.id.btn_mail);
        github = (Button) findViewById(R.id.btn_github);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailto = "mailto:lucaruffolo24@gmail.com" +
                        "?cc=" +
                        "&subject=" + Uri.encode("[App ALLMYGYM]") +
                        "&body=" + Uri.encode("");
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(AboutActivity.this, "Errore app mail", Toast.LENGTH_SHORT).show();
                }
            }
        });


        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://github.com/lucaruffolo");
            }
        });
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}