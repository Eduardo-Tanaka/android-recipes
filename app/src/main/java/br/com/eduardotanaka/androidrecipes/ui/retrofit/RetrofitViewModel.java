package br.com.eduardotanaka.androidrecipes.ui.retrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.eduardotanaka.androidrecipes.repository.MockRepository;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.model.MockApi;

public class RetrofitViewModel extends ViewModel {

    private MutableLiveData<List<MockApi>> mutableLiveData;
    private MutableLiveData<MockApi> mockApiMutableLiveData;
    private MockRepository mockRepository;

    public void init(){
        mockRepository = MockRepository.getInstance();
    }

    public LiveData<List<MockApi>> getallMockApi() {
        return mutableLiveData;
    }

    public void getAll() {
        mutableLiveData = mockRepository.getAll();
    }

    public LiveData<MockApi> getByIdMockApi() {
        return mockApiMutableLiveData;
    }

    public void getById(String id) {
        mockApiMutableLiveData = mockRepository.getById(id);
    }

    public LiveData<MockApi> update() {
        return mockApiMutableLiveData = mockRepository.update();
    }

    public LiveData<MockApi> post() {
        return mockApiMutableLiveData = mockRepository.post();
    }

    public LiveData<MockApi> delete(String id) {
        return mockApiMutableLiveData = mockRepository.delete(id);
    }
}
