package br.com.eduardotanaka.androidrecipes.ui.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.BaseFragment;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;

public class DetailMockApiFragment extends BaseFragment {

    public DetailMockApiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        MockApi mockApi = DetailMockApiFragmentArgs.fromBundle(getArguments()).getTeste();
        TextView id = view.findViewById(R.id.rvId);
        id.setText(mockApi.getId());
        TextView name = view.findViewById(R.id.rvName);
        name.setText(mockApi.getName());
        TextView createdAt = view.findViewById(R.id.rvCreatedAt);
        createdAt.setText(mockApi.getCreatedAt().toString());

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Navigation.findNavController(view).popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
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
