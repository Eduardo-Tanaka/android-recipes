package br.com.eduardotanaka.androidrecipes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.recyclerview.RecyclerViewActivity;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.RetrofitActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void retrofitClick(View view) {
        startActivity(new Intent(this, RetrofitActivity.class));
    }

    public void recyclewviewClick(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }
}
