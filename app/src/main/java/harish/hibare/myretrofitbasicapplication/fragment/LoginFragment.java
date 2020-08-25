package harish.hibare.myretrofitbasicapplication.fragment;

        import android.app.Activity;
        import android.content.Context;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;

        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import harish.hibare.myretrofitbasicapplication.R;
        import harish.hibare.myretrofitbasicapplication.activities.MainActivity;
        import harish.hibare.myretrofitbasicapplication.model.User;
        import harish.hibare.myretrofitbasicapplication.services.MyInterface;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;


public class LoginFragment extends Fragment {
    MyInterface myInterface_login;
   Button loginBtn;
   EditText emailInput,passwordInput;
   TextView registerTV;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //Login
        emailInput=view.findViewById(R.id.emailInput);
        passwordInput=view.findViewById(R.id.passwordInput);
        loginBtn=view.findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();

            }
        });

        //Register

        registerTV=view.findViewById(R.id.registerTV);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Register", Toast.LENGTH_SHORT).show();
                myInterface_login.register();
            }
        });


        return view;
    }

    private void loginUser() {
        String email=emailInput.getText().toString().trim();
        String password=passwordInput.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            MainActivity.appPreferance.showToast("please enter your email");
        }
        else {
            Call<User> userCall = MainActivity.serviceApi.doLogin(email, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.body().getResponse().equals("data"))
                    {
                        MainActivity.appPreferance.setLoginStatus(true);
                        myInterface_login.login(response.body().getName(),response.body().getEmail(),response.body().getCreatedAt());
        //                System.out.println("Response\n"+response.body().getName());
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    }
                   else  if(response.body().getResponse().equals("Login Failed"))
                    {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                        emailInput.setText("");
                        passwordInput.setText("");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        myInterface_login= (MyInterface) activity;
    }
}