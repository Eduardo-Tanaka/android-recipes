package br.com.eduardotanaka.androidrecipes.ui.recyclerview;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.data.model.MockApi;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomHorizontalAdapterRV customHorizontalAdapterRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        customHorizontalAdapterRV = new CustomHorizontalAdapterRV();
        customHorizontalAdapterRV.setData(getLista());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customHorizontalAdapterRV);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private List<MockApi> getLista() {
        Gson gson = new Gson();
        List<MockApi> lista = Arrays.asList(gson.fromJson(getJson(), MockApi[].class));
        return lista;
    }

    private String getJson() {
        return "[" +
                "  {" +
                "    \"id\": \"1\"," +
                "    \"createdAt\": \"2020-02-29T00:14:30.600Z\"," +
                "    \"name\": \"Jaquelin Goyette\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/sergeyalmone/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"2\"," +
                "    \"createdAt\": \"2020-02-28T23:05:02.275Z\"," +
                "    \"name\": \"Maggie Blick\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/joemdesign/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"3\"," +
                "    \"createdAt\": \"2020-02-29T18:56:17.357Z\"," +
                "    \"name\": \"Merle Conn\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/marlinjayakody/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"4\"," +
                "    \"createdAt\": \"2020-02-29T00:28:03.808Z\"," +
                "    \"name\": \"Haven Beahan\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/stevedesigner/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"5\"," +
                "    \"createdAt\": \"2020-02-29T02:20:29.990Z\"," +
                "    \"name\": \"Donny Veum\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/guischmitt/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"6\"," +
                "    \"createdAt\": \"2020-02-29T09:08:41.945Z\"," +
                "    \"name\": \"Vinnie Conn\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/flexrs/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"7\"," +
                "    \"createdAt\": \"2020-02-29T07:09:40.003Z\"," +
                "    \"name\": \"Jayne Rempel Sr.\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/id835559/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"8\"," +
                "    \"createdAt\": \"2020-02-29T10:47:57.615Z\"," +
                "    \"name\": \"Dr. Idell Will\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/joelhelin/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"9\"," +
                "    \"createdAt\": \"2020-02-29T01:59:39.240Z\"," +
                "    \"name\": \"Mrs. Erin Mraz\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/sebashton/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"10\"," +
                "    \"createdAt\": \"2020-02-29T06:16:54.345Z\"," +
                "    \"name\": \"Adrian Ferry DVM\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/ismail_biltagi/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"11\"," +
                "    \"createdAt\": \"2020-02-28T20:09:36.499Z\"," +
                "    \"name\": \"Sammy Von\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/bungiwan/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"12\"," +
                "    \"createdAt\": \"2020-02-29T09:37:37.057Z\"," +
                "    \"name\": \"Annabell Wintheiser II\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/laurengray/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"13\"," +
                "    \"createdAt\": \"2020-02-28T21:12:37.197Z\"," +
                "    \"name\": \"Pablo Maggio\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/wiljanslofstra/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"14\"," +
                "    \"createdAt\": \"2020-02-28T22:14:35.329Z\"," +
                "    \"name\": \"Elissa Flatley\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/colirpixoil/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"15\"," +
                "    \"createdAt\": \"2020-02-29T19:28:27.710Z\"," +
                "    \"name\": \"Mr. Mikel Rau\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/joshmedeski/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"16\"," +
                "    \"createdAt\": \"2020-02-29T01:48:01.745Z\"," +
                "    \"name\": \"Reuben Kovacek\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/agustincruiz/128.jpg\"" +
                "  }," +
                "  {" +
                "    \"id\": \"17\"," +
                "    \"createdAt\": \"2020-02-29T15:28:07.778Z\"," +
                "    \"name\": \"Zion Harvey\"," +
                "    \"avatar\": \"https://s3.amazonaws.com/uifaces/faces/twitter/_williamguerra/128.jpg\"" +
                "  }" +
                "]";
    }
}
