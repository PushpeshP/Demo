package pushpesh.popular.filmlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

import pushpesh.popular.filmlist.R;
import pushpesh.popular.filmlist.adapter.PopularFilmAdapter;
import pushpesh.popular.filmlist.common.Constant;
import pushpesh.popular.filmlist.databinding.ActivityMainBinding;
import pushpesh.popular.filmlist.model.Result;
import pushpesh.popular.filmlist.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ProgressDialog progress;
    private PopularFilmAdapter popularFilmAdapter;
    private List<Result> resultList;
    private List<Result> getResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setMainViewModel(viewModel);
        initParams();
    }
    private void initParams() {
        progress = new ProgressDialog(this);
        pDialogInitialization();
        pDialogEnableDisable();
        resultList = new ArrayList<>();
        getResultList = new ArrayList<>();
        //------------To load data on Recyclerview----------//
        viewModel.getFilmList(Constant.API_KEY);
        viewModel.getResultResponseMutableLiveData().observe(this, response -> {
            resultList = (ArrayList<Result>) response.getResults();
            getResultList = new ArrayList<>();
            getResultList.addAll(resultList);
            popularFilmAdapter = new PopularFilmAdapter(this,getResultList);
            binding.rvMovies.setLayoutManager(new GridLayoutManager(this,2));
            binding.rvMovies.setAdapter(popularFilmAdapter);
            int resId = R.anim.anim_rv;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
            binding.rvMovies.setLayoutAnimation(animation);
        });
    }
    private void pDialogInitialization() {
        progress = new ProgressDialog(this);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage(getString(R.string.please_wait));
    }
    private void pDialogEnableDisable() {
        viewModel.getpDialog().observe(this, aBoolean -> {
            if (aBoolean)
                progress.show();
            else progress.dismiss();
        });
    }
}