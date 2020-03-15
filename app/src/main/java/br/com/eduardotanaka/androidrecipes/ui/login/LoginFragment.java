package br.com.eduardotanaka.androidrecipes.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.util.SharedPreferencesUtil;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnLogar = view.findViewById(R.id.btnLogar);
        btnLogar.setOnClickListener(v -> {
            SharedPreferencesUtil.getInstance().put(SharedPreferencesUtil.Key.IS_LOGGED, true);
            Navigation.findNavController(v).navigate(R.id.action_login_to_start_fragment);
        });

        Button btnCadastrar = view.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v ->
            Toast.makeText(getContext(), "IR PARA PAGINA DE CADATRO", Toast.LENGTH_SHORT).show()
        );
    }
}
