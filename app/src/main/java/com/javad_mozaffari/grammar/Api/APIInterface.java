package com.javad_mozaffari.grammar.Api;

import com.javad_mozaffari.grammar.Model.Grammar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("getData.php")
    Call<List<Grammar>> getData();
}
