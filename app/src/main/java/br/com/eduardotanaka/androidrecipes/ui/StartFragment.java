package br.com.eduardotanaka.androidrecipes.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import br.com.eduardotanaka.androidrecipes.R;

public class StartFragment extends BaseFragment {

    private Button btnRetrofit;
    private Button btnRecyclerview;
    private Button btnPagedList;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btnRetrofit = view.findViewById(R.id.btnRetrofit);
        btnRetrofit.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_startFragment_to_retrofitActivity));

        btnRecyclerview = view.findViewById(R.id.btnRecyclerview);
        btnRecyclerview.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_startFragment_to_recyclerViewActivity));

        btnPagedList = view.findViewById(R.id.btnPagedList);
        btnPagedList.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_startFragment_to_pagedListFragment));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
