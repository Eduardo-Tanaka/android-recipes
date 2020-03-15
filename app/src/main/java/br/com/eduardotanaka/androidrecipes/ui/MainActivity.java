package br.com.eduardotanaka.androidrecipes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.util.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesUtil.getInstance(this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // listener para ao mudar de fragment realizar alguma mudança na activity
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            setTitle(destination.getLabel());
            switch (destination.getId()) {
                case R.id.login:
                    getSupportActionBar().hide();
                    break;
                default:
                    getSupportActionBar().show();
            }
        });
    }
}
