package br.com.eduardotanaka.androidrecipes.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Date;
import java.util.List;

import br.com.eduardotanaka.androidrecipes.ui.retrofit.RetrofitConfig;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.service.MockApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockRepository {

    private final String TAG = MockRepository.class.getName();
    private static MockRepository mockRepository;

    public static MockRepository getInstance(){
        if (mockRepository == null){
            mockRepository = new MockRepository();
        }
        return mockRepository;
    }

    private MockApiService mockApiService;

    public MockRepository(){
        mockApiService = RetrofitConfig.getInstance().getService(MockApiService.class);
    }

    public MutableLiveData<List<MockApi>> getAll(){
        MutableLiveData<List<MockApi>> mockApiData = new MutableLiveData<>();
        mockApiService.getAll().enqueue(new Callback<List<MockApi>>() {
            @Override
            public void onResponse(Call<List<MockApi>> call, Response<List<MockApi>> response) {
                if (response.isSuccessful()) {
                    mockApiData.setValue(response.body());
                } else {
                    mockApiData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<MockApi>> call, Throwable t) {
                mockApiData.setValue(null);
            }
        });

        return mockApiData;
    }

    public MutableLiveData<MockApi> getById(String id) {
        MutableLiveData<MockApi> mockApiData = new MutableLiveData<>();
        Call<MockApi> callMockApiGetById = mockApiService.getById(id);
        callMockApiGetById.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    mockApiData.setValue(mockApi);
                } else {
                    mockApiData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                mockApiData.setValue(null);
            }
        });

        return mockApiData;
    }

    public MutableLiveData<MockApi> post() {
        MutableLiveData<MockApi> mockApiData = new MutableLiveData<>();
        MockApi mockApi = new MockApi();
        mockApi.setName("teste name");
        mockApi.setCreatedAt(new Date());
        mockApi.setAvatar("teste avatar");
        Call<MockApi> callMockApiPost = mockApiService.post(mockApi);
        callMockApiPost.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    mockApiData.setValue(mockApi);
                } else {
                    mockApiData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                mockApiData.setValue(null);
            }
        });

        return mockApiData;
    }

    public MutableLiveData<MockApi> update() {
        MutableLiveData<MockApi> mockApiData = new MutableLiveData<>();
        MockApi mockApi = new MockApi();
        mockApi.setName("teste name" + Math.random());
        mockApi.setCreatedAt(new Date());
        mockApi.setAvatar("teste avatar");
        Call<MockApi> callMockApiUpdate = mockApiService.update("1", mockApi);
        callMockApiUpdate.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    mockApiData.setValue(mockApi);
                } else {
                    mockApiData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                mockApiData.setValue(null);
            }
        });

        return mockApiData;
    }

    public MutableLiveData<MockApi> delete(String id) {
        MutableLiveData<MockApi> mockApiData = new MutableLiveData<>();
        Call<MockApi> callMockApiDelete = mockApiService.delete(id);
        callMockApiDelete.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    mockApiData.setValue(mockApi);
                } else {
                    mockApiData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                mockApiData.setValue(null);
            }
        });

        return  mockApiData;
    }
}
