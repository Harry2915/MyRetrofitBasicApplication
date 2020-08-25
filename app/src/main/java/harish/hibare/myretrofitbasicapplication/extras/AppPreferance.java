package harish.hibare.myretrofitbasicapplication.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import harish.hibare.myretrofitbasicapplication.R;

public class AppPreferance {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public AppPreferance(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.s_pref_file), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //login status
    public void setLoginStatus(boolean status) {
        editor.putBoolean(String.valueOf(R.string.s_pref_login_status), status);
        editor.commit();
    }

    public boolean getLoginStatus() {
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pref_login_status), false);
    }

    //Name
    public void setDisplayName(String name) {
        editor.putString(String.valueOf(R.string.s_pref_login_name), name);
        editor.commit();
    }

    public String getDisplayName() {
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_name), "Name");
    }

    //Email
    public void setDisplayEmail(String email) {
        editor.putString(String.valueOf(R.string.s_pref_login_email), email);
        editor.commit();
    }

    public String getDisplayEmail() {
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_email), "Email");
    }

    //Date
    public void setDisplayDate(String date) {
        editor.putString(String.valueOf(R.string.s_pref_login_date), date);
        editor.commit();
    }

    public String getDisplayDate() {
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_date), "Date");
    }

    public void showToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
