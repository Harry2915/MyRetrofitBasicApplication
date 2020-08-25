package harish.hibare.myretrofitbasicapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import harish.hibare.myretrofitbasicapplication.R;
import harish.hibare.myretrofitbasicapplication.activities.MainActivity;
import harish.hibare.myretrofitbasicapplication.services.MyInterface;


public class ProfileFragment extends Fragment {
    TextView name, email;
    Button logoutButton;
    MyInterface myInterface_profile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);

        email = view.findViewById(R.id.email);
        String Name="Hi,"+MainActivity.appPreferance.getDisplayName();
        name.setText(Name);
        email.setText("\nRegistered on: \n" + MainActivity.appPreferance.getDisplayEmail() +"\n\ncreated at\n"+MainActivity.appPreferance.getDisplayDate());
        logoutButton = view.findViewById(R.id.logoutBtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myInterface_profile.logout();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        myInterface_profile= (MyInterface) activity;
    }
}