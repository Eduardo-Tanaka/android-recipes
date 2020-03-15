package br.com.eduardotanaka.androidrecipes.ui.retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.BaseFragment;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.CustomAdapterRV;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.SwipeController;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;

public class RetrofitFragment extends BaseFragment implements CustomAdapterRV.ItemClickListener {

    private final String TAG = RetrofitFragment.class.getName();
    private String id;
    private RecyclerView recyclerView;
    private CustomAdapterRV customAdapterRV;
    private List<MockApi> lista = new ArrayList<>();
    private int position;
    private RetrofitViewModel retrofitViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_retrofit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);
        retrofitViewModel.init();

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
        getAll.setOnClickListener(v -> getAll());
        Button getById = view.findViewById(R.id.retrofit_get_by_id);
        getById.setOnClickListener(v -> getById());
        Button post = view.findViewById(R.id.retrofit_post);
        post.setOnClickListener(v -> post());
        Button update = view.findViewById(R.id.retrofit_update);
        update.setOnClickListener(v -> update());
        Button delete = view.findViewById(R.id.retrofit_delete);
        delete.setOnClickListener(v -> delete());
    }

    public void getAll() {
        retrofitViewModel.getAll();
        retrofitViewModel.getallMockApi().observe(getViewLifecycleOwner(), mockApis -> {
            if (mockApis != null) {
                lista.addAll(mockApis);
                customAdapterRV.setData(mockApis);
                customAdapterRV.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "ERRO GET ALL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getById() {
        retrofitViewModel.getById("1");
        retrofitViewModel.getByIdMockApi().observe(getViewLifecycleOwner(), mockApi -> {
            if (mockApi != null) {
                lista = new ArrayList<>();
                lista.add(mockApi);
                customAdapterRV.setData(lista);
                customAdapterRV.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "ERRO GET BY ID", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void post() {
        retrofitViewModel.post().observe(getViewLifecycleOwner(), mockApi -> {
            if (mockApi != null) {
                id = mockApi.getId();
                lista.add(mockApi);
                customAdapterRV.setData(lista);
                customAdapterRV.notifyItemInserted(lista.size());
            } else {
                Toast.makeText(getContext(), "ERRO POST", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void update() {
        retrofitViewModel.update().observe(getViewLifecycleOwner(), mockApi -> {
            if (mockApi != null) {
                lista.set(lista.size() - 1, mockApi);
                customAdapterRV.setData(lista);
                customAdapterRV.notifyItemChanged(lista.indexOf(mockApi));
            } else {
                Toast.makeText(getContext(), "ERRO UPDATE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void delete() {
        retrofitViewModel.delete(id).observe(getViewLifecycleOwner(), mockApi -> {
            if (mockApi != null) {
                lista.remove(position);
                customAdapterRV.setData(lista);
                customAdapterRV.notifyItemRemoved(position);

                recyclerView.requestLayout();
            } else {
                Toast.makeText(getContext(), "ERRO DELETE", Toast.LENGTH_SHORT).show();
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
        delete();
    }
}
