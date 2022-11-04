package pushpesh.popular.filmlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LoginViewModel extends AndroidViewModel{
    private Application application;
    private String strUsername;
    private String strPassword;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }
}


