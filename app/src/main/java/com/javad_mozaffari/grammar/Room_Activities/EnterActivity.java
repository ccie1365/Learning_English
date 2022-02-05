package com.javad_mozaffari.grammar.Room_Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import com.javad_mozaffari.grammar.R;
import com.javad_mozaffari.grammar.Room_Database.AppDatabase;
import com.javad_mozaffari.grammar.Room_Database.NameDao;
import com.javad_mozaffari.grammar.Room_Database.NameModel;

public class EnterActivity extends AppCompatActivity {

    private boolean isboolean = false;
    private TextInputLayout nameEdite, familyEdit;
    private NameDao nameDao;
    private NameModel upnameModel;
    private Intent intent;
    private EditText edtname, edtfamily;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        nameDao = AppDatabase.getAppDatabase(this).nameDao();

        intent = getIntent();
        isboolean = intent.getBooleanExtra("ismode", false);
        upnameModel = getIntent().getExtras().getParcelable("name");
        findView();

        if (isboolean) {
            edtname.setText(upnameModel.getName());
            edtfamily.setText(upnameModel.getFamily());
        }
    }

    private void findView() {
        nameEdite = findViewById(R.id.textinputname);
        familyEdit = findViewById(R.id.textinputfamily);
        edtname = findViewById(R.id.edtname);
        edtfamily = findViewById(R.id.edtFamily);
        save=findViewById(R.id.btnSave);
    }

    public void ExtendOnClick(View view) {
        Toast.makeText(this, "اطلاعات با موفقیت ثبت گردید", Toast.LENGTH_SHORT).show();
        String name = nameEdite.getEditText().getText().toString().trim();
        String family = familyEdit.getEditText().getText().toString().trim();

        if (isboolean) {
            updateName(name, family);
        } else {
            if (name.isEmpty() && family.isEmpty()) {
                 nameEdite.setError("Empty");
                 familyEdit.setError("Empty");
            }else {
                saveUser(name,family);
            }
        }
    }

    private void saveUser(String name, String family) {
        NameModel nameModel = new NameModel();
        nameModel.setName(name);
        nameModel.setFamily(family);
        nameDao.addName(nameModel);
        finish();
    }

    private void updateName(String name, String family) {
        upnameModel.setName(name);
        upnameModel.setFamily(family);
        nameDao.updateName(upnameModel);
        finish();
    }

}