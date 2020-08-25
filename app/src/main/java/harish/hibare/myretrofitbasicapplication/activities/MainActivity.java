package harish.hibare.myretrofitbasicapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import harish.hibare.myretrofitbasicapplication.R;
import harish.hibare.myretrofitbasicapplication.constants.Constant;
import harish.hibare.myretrofitbasicapplication.extras.AppPreferance;
import harish.hibare.myretrofitbasicapplication.fragment.LoginFragment;
import harish.hibare.myretrofitbasicapplication.fragment.ProfileFragment;
import harish.hibare.myretrofitbasicapplication.fragment.RegistrationFragment;
import harish.hibare.myretrofitbasicapplication.services.MyInterface;
import harish.hibare.myretrofitbasicapplication.services.RetrofitClient;
import harish.hibare.myretrofitbasicapplication.services.ServiceApi;

public class MainActivity extends AppCompatActivity implements MyInterface {
    FrameLayout container_fragment;
   public static AppPreferance appPreferance;
   public static ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container_fragment=findViewById(R.id.fragment_container);
        appPreferance=new AppPreferance(this);

        serviceApi= RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        if(container_fragment!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            if(appPreferance.getLoginStatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new ProfileFragment()).commit();
            }
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
            }
        }

    }

    @Override
    public void register() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void login(String name, String email, String created_at) {
        appPreferance.setDisplayName(name);
        appPreferance.setDisplayEmail(email);
        appPreferance.setDisplayDate(created_at);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();

    }

    @Override
    public void logout() {
        appPreferance.setLoginStatus(false);
        appPreferance.setDisplayName("Name");
        appPreferance.setDisplayEmail("Email");
        appPreferance.setDisplayDate("Date");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();

    }
}