package br.com.eduardotanaka.androidrecipes.ui.retrofit.service;

import java.util.List;

import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MockApiService {

    @GET("retrofit-teste")
    Call<List<MockApi>> getAll();

    @GET("retrofit-teste/{id}")
    Call<MockApi> getById(@Path("id") String id);

    @POST("retrofit-teste")
    Call<MockApi> post(@Body MockApi mockApi);

    @PUT("retrofit-teste/{id}")
    Call<MockApi> update(@Path("id")String id, @Body MockApi mockApi);

    @DELETE("retrofit-teste/{id}")
    Call<MockApi> delete(@Path("id") String id);
}
