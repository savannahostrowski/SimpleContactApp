package com.savannahostrowski.contactapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    TextView tvResult;
    LinearLayout linLayoutIcons;
    ImageView ivPhone;
    ImageView ivMap;
    ImageView ivWeb;

    final int CONTACT_FORM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        tvResult = findViewById(R.id.tvResult);
        ivPhone = findViewById(R.id.ivPhone);
        ivMap = findViewById(R.id.ivMap);
        ivWeb = findViewById(R.id.ivWeb);
        linLayoutIcons = findViewById(R.id.linLayoutIcons);

        linLayoutIcons.setVisibility(View.GONE);
        tvResult.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        com.savannahostrowski.contactapp.ContactForm.class);
                startActivityForResult(intent, CONTACT_FORM);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            assert data != null;

            final String name = data.getStringExtra("name");
            final String address = data.getStringExtra("address");
            final String phone = data.getStringExtra("phone");
            final String website = data.getStringExtra("website");

            tvResult.setText(name);

            ivPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    startActivity(intent);
                }
            });

            ivMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0.0?q=" + address));
                    startActivity(intent);
                }
            });

            ivWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + website));
                    startActivity(intent);
                }
            });


            tvResult.setVisibility(View.VISIBLE);
            linLayoutIcons.setVisibility(View.VISIBLE);

        }

        if (resultCode == RESULT_CANCELED) {
            tvResult.setText(R.string.no_data);
            tvResult.setVisibility(View.GONE);
            linLayoutIcons.setVisibility(View.GONE);
        }
    }
}
