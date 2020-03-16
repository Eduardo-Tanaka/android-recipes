package br.com.eduardotanaka.androidrecipes.data.datasource;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import br.com.eduardotanaka.androidrecipes.data.model.MockApi;
import br.com.eduardotanaka.androidrecipes.data.retrofit.RetrofitConfig;
import br.com.eduardotanaka.androidrecipes.data.retrofit.service.MockApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagedListDataSource extends PageKeyedDataSource<Integer, MockApi> {

    private MockApiService mockApiService;

    PagedListDataSource() {
        mockApiService = RetrofitConfig.getInstance().getService(MockApiService.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MockApi> callback) {
        try {
            Response<List<MockApi>> response = mockApiService.getPaginated(1).execute();
            callback.onResult(response.body(), null, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MockApi> callback) {
        mockApiService.getPaginated(params.key).enqueue(new Callback<List<MockApi>>() {
            @Override
            public void onResponse(Call<List<MockApi>> call, Response<List<MockApi>> response) {
                if (response.body() != null) {
                    int key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(response.body(), key);
                }
            }

            @Override
            public void onFailure(Call<List<MockApi>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MockApi> callback) {
        mockApiService.getPaginated(params.key).enqueue(new Callback<List<MockApi>>() {
            @Override
            public void onResponse(Call<List<MockApi>> call, Response<List<MockApi>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), params.key + 1);
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<MockApi>> call, Throwable t) {
            }
        });
    }
}
