package br.com.eduardotanaka.androidrecipes.ui.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.eduardotanaka.androidrecipes.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static RetrofitConfig instance = null;
    private static final String BASE_URL = "https://5e25087e43dea60014404938.mockapi.io/api/";
    private Retrofit retrofit;
    private OkHttpClient client;

    // One of the ways to make the singleton code thread safe is by making the method getInstance() a synchronized one
    public static synchronized RetrofitConfig getInstance() {
        if (instance == null) {
            instance = new RetrofitConfig();
        }

        return instance;
    }

    public RetrofitConfig() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }
        client = okHttpBuilder
                //.readTimeout(15, TimeUnit.SECONDS)
                //.connectTimeout(15, TimeUnit.SECONDS)
                .build();

        // serialize Date from String
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public <T> T getService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
