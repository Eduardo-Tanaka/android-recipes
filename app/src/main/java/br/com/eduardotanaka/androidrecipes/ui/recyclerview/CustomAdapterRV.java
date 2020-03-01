package br.com.eduardotanaka.androidrecipes.ui.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;

public class CustomAdapterRV extends RecyclerView.Adapter {

    private List<MockApi> mockApiList;
    private static ItemClickListener itemClickListener;
    private Context context;

    public CustomAdapterRV(ItemClickListener itemClickListener, Context context) {
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView id;
        private TextView name;
        private TextView createdAt;
        private LinearLayout linearLayout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.rvLayout);
            id = itemView.findViewById(R.id.rvId);
            name = itemView.findViewById(R.id.rvName);
            createdAt = itemView.findViewById(R.id.rvCreatedAt);
        }

        public void bindData(MockApi mockApi) {
            id.setText(mockApi.getId());
            name.setText(mockApi.getName());
            createdAt.setText(mockApi.getCreatedAt().toString());

            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, mockApiList.get(getAdapterPosition()));
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview, parent, false);
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

    public interface ItemClickListener {
        void onItemClick(View view, MockApi mockApi);
        void onDeleteSwipe(MockApi mockApi, int position);
    }

    public Context getContext() {
        return context;
    }

    public void deleteItem(int position) {
        itemClickListener.onDeleteSwipe(this.mockApiList.get(position), position);
    }
}
