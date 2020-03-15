package br.com.eduardotanaka.androidrecipes.ui.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.BaseFragment;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.CustomAdapterRV;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.SwipeController;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.service.MockApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitFragment extends BaseFragment implements CustomAdapterRV.ItemClickListener {

    private final String TAG = RetrofitFragment.class.getName();
    private String id;
    private RecyclerView recyclerView;
    private CustomAdapterRV customAdapterRV;
    private List<MockApi> lista = new ArrayList<>();
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_retrofit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        customAdapterRV = new CustomAdapterRV(RetrofitFragment.this, getContext());
        customAdapterRV.setData(new ArrayList<>());
        recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(customAdapterRV);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeController(customAdapterRV));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Button getAll = view.findViewById(R.id.retrofit_get_all);
        getAll.setOnClickListener(v -> getAll(v));
        Button getById = view.findViewById(R.id.retrofit_get_by_id);
        getById.setOnClickListener(v -> getById(v));
        Button post = view.findViewById(R.id.retrofit_post);
        post.setOnClickListener(v -> post(v));
        Button update = view.findViewById(R.id.retrofit_update);
        update.setOnClickListener(v -> update(v));
        Button delete = view.findViewById(R.id.retrofit_delete);
        delete.setOnClickListener(v -> delete(v));
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
                    Toast.makeText(getContext(), "ERRO GET ALL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MockApi>> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE GET ALL", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "ERRO GET BY ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE GET BY ID", Toast.LENGTH_SHORT).show();
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
                    Log.i(TAG, mockApi.toString());

                    id = mockApi.getId();
                    lista.add(mockApi);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyItemInserted(lista.size());
                } else {
                    Toast.makeText(getContext(), "ERRO POST", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE POST", Toast.LENGTH_SHORT).show();
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

                    lista.set(lista.size() - 1, mockApi);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyItemChanged(lista.indexOf(mockApi));
                } else {
                    Toast.makeText(getContext(), "ERRO UPDATE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE UPDATE", Toast.LENGTH_SHORT).show();
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

                    lista.remove(position);
                    customAdapterRV.setData(lista);
                    customAdapterRV.notifyItemRemoved(position);

                    recyclerView.requestLayout();
                } else {
                    Toast.makeText(getContext(), "ERRO DELETE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MockApi> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE DELETE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, MockApi mockApi) {
        Toast.makeText(getContext(), mockApi.toString(), Toast.LENGTH_SHORT).show();
        //Bundle bundle = new Bundle();
        //bundle.putParcelable("TESTE", mockApi);
        //Navigation.findNavController(view).navigate(R.id.action_retrofitActivity_to_detailMockApiFragment, bundle);
        // Type Safe Args
        /* Projeto:
            dependencies {
                classpath "androidx.navigation:navigation-safe-args-gradle-plugin:x.x.x"
            }
            App:
            apply plugin: "androidx.navigation.safeargs
        */
        RetrofitFragmentDirections.ActionRetrofitActivityToDetailMockApiFragment action = RetrofitFragmentDirections.actionRetrofitActivityToDetailMockApiFragment(mockApi);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onDeleteSwipe(MockApi mockApi, int position) {
        id = mockApi.getId();
        this.position = position;
        delete(null);
    }
}
