package br.com.eduardotanaka.androidrecipes.ui.retrofit;

import br.com.eduardotanaka.androidrecipes.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static RetrofitConfig instance;
    private static final String BASE_URL = "http(s)://5e25087e43dea60014404938.mockapi.io/api/";
    private Retrofit retrofit;
    private OkHttpClient client;

    public static RetrofitConfig getInstance() {
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
        client = okHttpBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


}
