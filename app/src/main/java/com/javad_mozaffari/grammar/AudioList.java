package com.javad_mozaffari.grammar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.javad_mozaffari.grammar.Adapters.AudioAdapter;
import com.javad_mozaffari.grammar.Api.APIClient;
import com.javad_mozaffari.grammar.Api.APIInterface;
import com.javad_mozaffari.grammar.Model.Grammar;
import com.javad_mozaffari.grammar.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudioList extends AppCompatActivity {

    RecyclerView recyclerView;
    AudioAdapter adapter;
    List<Grammar> list = new ArrayList<>();

    APIInterface request;
    String url = "http://192.168.2.1:8083/english/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        request = APIClient.getApiClient(url).create(APIInterface.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        request.getData().enqueue(new Callback<List<Grammar>>() {
            @Override
            public void onResponse(Call<List<Grammar>> call, Response<List<Grammar>> response) {
                list = response.body();
                adapter=new AudioAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Grammar>> call, Throwable t) {
                Toast.makeText(AudioList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}