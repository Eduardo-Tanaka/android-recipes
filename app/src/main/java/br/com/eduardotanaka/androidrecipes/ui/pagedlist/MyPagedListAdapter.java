package br.com.eduardotanaka.androidrecipes.ui.pagedlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.data.model.MockApi;

public class MyPagedListAdapter extends PagedListAdapter<MockApi, MyPagedListAdapter.MyViewHolder> {

    public MyPagedListAdapter() {
        super(MockApi.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview, parent, false);
        return new MyPagedListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView createdAt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.rvId);
            name = itemView.findViewById(R.id.rvName);
            createdAt = itemView.findViewById(R.id.rvCreatedAt);
        }

        public void bindData(MockApi mockApi) {
            id.setText(mockApi.getId());
            name.setText(mockApi.getName());
            createdAt.setText(mockApi.getCreatedAt().toString());
        }
    }
}
