package br.com.eduardotanaka.androidrecipes.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.RecyclerViewActivity;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.RetrofitActivity;

public class StartFragment extends Fragment {

    private Button btnRetrofit;
    private Button btnRecyclerview;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
