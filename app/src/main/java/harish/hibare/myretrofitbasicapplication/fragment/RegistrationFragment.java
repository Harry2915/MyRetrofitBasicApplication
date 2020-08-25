package harish.hibare.myretrofitbasicapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import harish.hibare.myretrofitbasicapplication.R;
import harish.hibare.myretrofitbasicapplication.activities.MainActivity;
import harish.hibare.myretrofitbasicapplication.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {

   EditText nameInput_reg,emailInput_reg,phoneInput_reg,passwordInput_reg;
   Button button_reg;

    public RegistrationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registration, container, false);
        nameInput_reg=view.findViewById(R.id.nameInput);
        emailInput_reg=view.findViewById(R.id.emailInput);
        phoneInput_reg=view.findViewById(R.id.phoneInput);
        passwordInput_reg=view.findViewById(R.id.passwordInput);
        button_reg=view.findViewById(R.id.regBtn);
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });
        return view;

    }
    private void registerUser()
    {
        String name=nameInput_reg.getText().toString().trim();
        String email=emailInput_reg.getText().toString().trim();
        String phone=phoneInput_reg.getText().toString().trim();
        String password=passwordInput_reg.getText().toString().trim();
        if(TextUtils.isEmpty(name))
        {
            MainActivity.appPreferance.showToast("please enter your name");
        }else
        if(TextUtils.isEmpty(password))
        {
            MainActivity.appPreferance.showToast("please enter your password");
        }
        else if(TextUtils.isEmpty(email))
        {
            MainActivity.appPreferance.showToast("please enter your email");
        }
        else  if(TextUtils.isEmpty(phone))
        {
            MainActivity.appPreferance.showToast("please enter your phone");
        }else
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            MainActivity.appPreferance.showToast("Your email is invalid");
        }else if(password.length()<6)
        {
            MainActivity.appPreferance.showToast("Password Must Contain 6 letter");
        }else
        {
            // data on server
            Call<User> userCall = MainActivity.serviceApi.doRegistration(name,email,password,phone);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    // Log.i("my response ",response.body().getResponse());
                    assert response.body() != null;
                    System.out.println("My Response" + response.body().getResponse());
                    if(response.body().getResponse().matches("inserted"))
                    {
                        showMsg("Successfully Registered", "Welcome"+name);
                    }
                    else  if(response.body().getResponse().matches("exists"))
                    {
                        showMsg("Already Registered", "Email"+email);
                    }


                }

                @Override
                public void onFailure(@NonNull Call<User> call,@NonNull Throwable t) {
                    System.out.println("My Response" + t);
                }
            });
        }
    }

    private void showMsg(String successfully_registered, String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("success");
        builder.setMessage(successfully_registered);
        builder.setCancelable(false);
        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearText();
                builder.setCancelable(true);
            }
        });
        builder.show();


    }

    private void clearText() {
        nameInput_reg.setText("");
        emailInput_reg.setText("");
        phoneInput_reg.setText("");
        passwordInput_reg.setText("");
    }
}

