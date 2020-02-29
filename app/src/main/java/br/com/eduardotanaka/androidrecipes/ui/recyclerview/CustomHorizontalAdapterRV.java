package br.com.eduardotanaka.androidrecipes.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;

public class CustomHorizontalAdapterRV extends RecyclerView.Adapter {

    private List<MockApi> mockApiList;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }

        public void bindData(MockApi mockApi) {
            name.setText(mockApi.getName());
            Picasso.get().load(mockApi.getAvatar()).into(image);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_view_horizontal, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecyclerViewHolder) holder).bindData(mockApiList.get(position));
    }

    @Override
    public int getItemCount() {
        return mockApiList.size();
    }

    public void setData(List<MockApi> mockApiList) {
        this.mockApiList = mockApiList;
    }

}
