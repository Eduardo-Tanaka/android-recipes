package br.com.eduardotanaka.androidrecipes.data.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import br.com.eduardotanaka.androidrecipes.data.model.MockApi;

public class PagedListDataSourceFactory extends DataSource.Factory<Integer, MockApi> {

    private MutableLiveData<PagedListDataSource> liveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, MockApi> create() {
        PagedListDataSource dataSourceClass = new PagedListDataSource();
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
