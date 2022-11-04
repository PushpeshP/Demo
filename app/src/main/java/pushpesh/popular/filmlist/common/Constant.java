package pushpesh.popular.filmlist.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constant {
    //Base URL
    public static final String HOST = "https://api.themoviedb.org/";
    // API KEY
    public static final String API_KEY = "9f0d895bdcd292a71376a65b38b1bf3b";
    //Load Image with prefix URL
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/original/";

    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    //add next activity accordingly to pass login page with entry to ActivityMain.Java


    // Here is validating password
    public static boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8 && pass.length() <= 15) {
            return true;
        }
        return false;
    }
}
