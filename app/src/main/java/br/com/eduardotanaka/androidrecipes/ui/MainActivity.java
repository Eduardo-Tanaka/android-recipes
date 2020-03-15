package br.com.eduardotanaka.androidrecipes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.util.SharedPreferencesUtil;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesUtil.getInstance(this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // listener para ao mudar de fragment realizar alguma mudança na activity
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            setTitle(destination.getLabel());
            switch (destination.getId()) {
                case R.id.login:
                    getSupportActionBar().hide();
                    bottomNavigationView.setVisibility(View.GONE);
                    break;
                default:
                    getSupportActionBar().show();
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    setupWithNavController(bottomNavigationView, navController);
            }
        });
    }
}
