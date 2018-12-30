package com.savannahostrowski.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactForm extends AppCompatActivity {

    EditText etName;
    EditText etAddress;
    EditText etPhone;
    EditText etWebsite;
    Button btnAdd;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etWebsite = findViewById(R.id.etWebsite);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = null;
                String address;
                String phone = null;
                String website;
                Intent intent = new Intent();

                if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(ContactForm.this, "Please enter a name!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    name = etName.getText().toString().trim();
                    intent.putExtra("name", name);
                }

                if (etAddress.getText().toString().isEmpty()) {
                    Toast.makeText(ContactForm.this, "Please enter an address!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    address = etAddress.getText().toString().trim();
                    intent.putExtra("address", address);
                }

                if (etPhone.getText().toString().isEmpty()) {
                    Toast.makeText(ContactForm.this, "Please enter a phone number!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    phone = etPhone.getText().toString().trim();
                    intent.putExtra("phone", phone);
                }

                if (etWebsite.getText().toString().isEmpty()) {
                    Toast.makeText(ContactForm.this, "Please enter a website!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    website = etWebsite.getText().toString().trim();
                    intent.putExtra("website", website);
                }

                if (name != null && phone != null) {
                    setResult(RESULT_OK, intent);
                    ContactForm.this.finish();
                }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                ContactForm.this.finish();
            }
        });

    }
}
