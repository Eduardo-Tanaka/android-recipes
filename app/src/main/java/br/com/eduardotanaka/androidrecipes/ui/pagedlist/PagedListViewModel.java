package br.com.eduardotanaka.androidrecipes.ui.pagedlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import br.com.eduardotanaka.androidrecipes.data.datasource.PagedListDataSourceFactory;
import br.com.eduardotanaka.androidrecipes.data.model.MockApi;

public class PagedListViewModel extends ViewModel {

    private PagedListDataSourceFactory pagedListDataSourceFactory;
    private LiveData<PagedList<MockApi>> listLiveData;

    public PagedListViewModel() {
        pagedListDataSourceFactory = new PagedListDataSourceFactory();
        initializePaging();
    }

    private void initializePaging() {
        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(10).build();

        listLiveData = new LivePagedListBuilder<>(pagedListDataSourceFactory, pagedListConfig)
                .build();
    }

    public LiveData<PagedList<MockApi>> getListLiveData() {
        return listLiveData;
    }
}
