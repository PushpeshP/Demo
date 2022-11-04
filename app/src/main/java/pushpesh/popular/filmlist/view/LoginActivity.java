package pushpesh.popular.filmlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import pushpesh.popular.filmlist.R;
import pushpesh.popular.filmlist.common.Constant;
import pushpesh.popular.filmlist.databinding.ActivityLoginBinding;
import pushpesh.popular.filmlist.viewmodel.LoginViewModel;
import pushpesh.popular.filmlist.viewmodel.MainViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        initParams();
    }

    private void initParams() {
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.btnLogin:
                loginViewModel.setStrUsername(binding.txtEmailAddress.getText().toString().trim());
                loginViewModel.setStrPassword(binding.txtPassword.getText().toString().trim());
                loginValidator();
                break;
        }
    }

    public void loginValidator() {
        if (!Constant.isValidEmail(loginViewModel.getStrUsername())) {
            binding.txtEmailAddress.setError("Please enter valid Email");
        }else if (!Constant.isValidPassword(loginViewModel.getStrPassword())) {
            binding.txtPassword.setError("Please enter Password in between 8 to 15 character");
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}