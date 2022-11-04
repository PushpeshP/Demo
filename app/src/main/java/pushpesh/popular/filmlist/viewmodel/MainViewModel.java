package pushpesh.popular.filmlist.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import pushpesh.popular.filmlist.model.ResultResponse;
import pushpesh.popular.filmlist.retrofitclient.ApiClient;

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();
    private Application application;
    private MutableLiveData<Boolean> pDialog = new MutableLiveData<>();
    public MutableLiveData<ResultResponse> resultResponseMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }
    public MutableLiveData<Boolean> getpDialog() {
        if (pDialog == null) {
            pDialog = new MutableLiveData<>();
        }
        return pDialog;
    }
    public MutableLiveData<ResultResponse> getResultResponseMutableLiveData() {
        if (resultResponseMutableLiveData == null)
            resultResponseMutableLiveData = new MutableLiveData<>();
        return resultResponseMutableLiveData;
    }
    //-------------get Popular filmist-------------//
    public void getFilmList(String apiKey) {
        pDialog.setValue(true);
        disposable.add(ApiClient.initRetrofit().getFilmList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe((filmlist, throwable) -> {
                    pDialog.setValue(false);
                    if(filmlist!=null){
                        Log.d("filmlist", "msg " + (filmlist.getResults()));
                        resultResponseMutableLiveData.setValue(filmlist);
                    }else{
                        Toast.makeText(application.getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
