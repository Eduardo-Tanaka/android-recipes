package br.com.eduardotanaka.androidrecipes.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.util.SharedPreferencesUtil;

/* BaseFragment para colocar a lógica dos menus e verificar se o usuário está logado */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isLogged = SharedPreferencesUtil.getInstance().getBoolean(SharedPreferencesUtil.Key.IS_LOGGED);
        if (!isLogged) {
            logout();
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_logout:
                SharedPreferencesUtil.getInstance().put(SharedPreferencesUtil.Key.IS_LOGGED, false);
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        NavDirections navDirections = StartFragmentDirections.actionGlobalLogin();
        NavHostFragment.findNavController(this).navigate(navDirections);
    }
}
