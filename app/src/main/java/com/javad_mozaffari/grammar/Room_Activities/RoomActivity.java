package com.javad_mozaffari.grammar.Room_Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.javad_mozaffari.grammar.R;
import com.javad_mozaffari.grammar.Room_Adapter.Adapter;

import com.javad_mozaffari.grammar.Room_Database.AppDatabase;
import com.javad_mozaffari.grammar.Room_Database.NameDao;
import com.javad_mozaffari.grammar.Room_Database.NameModel;

import java.util.List;

public class RoomActivity extends AppCompatActivity implements Adapter.HandleNames {

    RecyclerView recyclerView;
    ExtendedFloatingActionButton addButton;
    List<NameModel> nameModels;

    Adapter adapter;
    NameDao nameDao;

    final int requestAddCode = 1000;
    final int requestUpCode = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        inite();
        initeRecyclerview();
        loadDatabase();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomActivity.this, EnterActivity.class);
                intent.putExtra("is mode", false);
                startActivityForResult(intent, requestAddCode);
            }
        });

    }

    private void inite() {
        recyclerView = findViewById(R.id.recyclermain);
        addButton = findViewById(R.id.extendedFloating);
    }

    private void loadDatabase() {
        nameDao = AppDatabase.getAppDatabase(this).nameDao();
        nameModels = nameDao.getName();
        adapter.setAdapter(nameModels);
    }

    private void initeRecyclerview() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void removeItem(NameModel nameModel) {
        nameDao.deleteName(nameModel);
        loadDatabase();

    }

    @Override
    public void editItem(NameModel nameModel) {

        Intent intent = new Intent(RoomActivity.this, EnterActivity.class);
        intent.putExtra("ismode", true);
        intent.putExtra("name", nameModel);
        startActivityForResult(intent, requestUpCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == requestAddCode) {
            loadDatabase();
        } else if (requestCode == requestUpCode) {
            loadDatabase();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}