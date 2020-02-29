package br.com.eduardotanaka.androidrecipes.ui.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.CustomAdapterRV;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.service.MockApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.eduardotanaka.androidrecipes.ui.retrofit.RetrofitConfig.getInstance;

public class RetrofitActivity extends AppCompatActivity implements CustomAdapterRV.ItemClickListener {

    private final String TAG = RetrofitActivity.class.getName();
    private String id;
    private RecyclerView recyclerView;
    private CustomAdapterRV customAdapterRV;
    private List<MockApi> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        customAdapterRV = new CustomAdapterRV(this);
        customAdapterRV.setData(new ArrayList<>());
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapterRV);
    }

    public void getAll(View view) {
        MockApiService mockApiService = RetrofitConfig.getInstance().getMockApiService();
        Call<List<MockApi>> callMockApiGetAll = mockApiService.getAll();
        callMockApiGetAll.enqueue(new Callback<List<MockApi>>() {
            @Override
            public void onResponse(Call<List<MockApi>> call, Response<List<MockApi>> response) {
                if (response.isSuccessful()) {
                    lista = new ArrayList<>();
                    lista = response.body();
                    Log.i(TAG, lista.toString());

                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyDataSetChanged();
                } else {
                    Toast.makeText(RetrofitActivity.this, "ERRO GET ALL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MockApi>> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "FAILURE GET ALL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getById(View view) {
        MockApiService mockApiService = RetrofitConfig.getInstance().getMockApiService();
        Call<MockApi> callMockApiGetById = mockApiService.getById("1");
        callMockApiGetById.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    lista = new ArrayList<>();
                    lista.add(mockApi);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyDataSetChanged();
                } else {
                    Toast.makeText(RetrofitActivity.this, "ERRO GET BY ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "FAILURE GET BY ID", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void post(View view) {
        MockApiService mockApiService = RetrofitConfig.getInstance().getMockApiService();
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
                    id = mockApi.getId();
                    Log.i(TAG, mockApi.toString());

                    lista.add(mockApi);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyItemInserted(Integer.valueOf(id) - 1);
                } else {
                    Toast.makeText(RetrofitActivity.this, "ERRO POST", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "FAILURE POST", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void update(View view) {
        MockApiService mockApiService = RetrofitConfig.getInstance().getMockApiService();
        MockApi mockApi = new MockApi();
        mockApi.setName("teste name");
        mockApi.setCreatedAt(new Date());
        mockApi.setAvatar("teste avatar");
        Call<MockApi> callMockApiUpdate = mockApiService.update(id, mockApi);
        callMockApiUpdate.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    lista.set(Integer.valueOf(id) - 1, mockApi);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyItemChanged(Integer.valueOf(id) - 1);
                } else {
                    Toast.makeText(RetrofitActivity.this, "ERRO UPDATE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "FAILURE UPDATE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void delete(View view) {
        MockApiService mockApiService = RetrofitConfig.getInstance().getMockApiService();
        Call<MockApi> callMockApiDelete = mockApiService.delete(id);
        callMockApiDelete.enqueue(new Callback<MockApi>() {
            @Override
            public void onResponse(Call<MockApi> call, Response<MockApi> response) {
                if (response.isSuccessful()) {
                    MockApi mockApi = response.body();
                    Log.i(TAG, mockApi.toString());

                    lista.remove(Integer.valueOf(id) -1);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyItemRemoved(Integer.valueOf(id) - 1);
                } else {
                    Toast.makeText(RetrofitActivity.this, "ERRO DELETE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "FAILURE DELETE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(MockApi mockApi) {
        Toast.makeText(this, mockApi.toString(), Toast.LENGTH_SHORT).show();
    }
}
