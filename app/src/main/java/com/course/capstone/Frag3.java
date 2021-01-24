package com.course.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.course.capstone.activities.education.EducationBank;
import com.course.capstone.activities.education.EducationFraud;
import com.course.capstone.activities.education.EducationInsurance;
import com.course.capstone.activities.education.EducationStock;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;

public class Frag3 extends Fragment implements View.OnClickListener{

    @Nullable
    Button btn1, btn2, btn3, btn4;
    DataManager dataManager = DataManager.getInstance();
    User user = dataManager.getUser();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = (ViewGroup) inflater.inflate(R.layout.activity_eduction_main, container, false);
        btn1 = view.findViewById(R.id.button_bank);
        btn2 = view.findViewById(R.id.button_fraud);
        btn3 = view.findViewById(R.id.button_insurance);
        btn4 = view.findViewById(R.id.button_stock);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
//
//        Log.d("생년월일",user.getBirthdate());
//        System.out.println(Integer.parseInt(user.getBirthdate()) + 1);
        int age;
        if (Integer.parseInt(user.getBirthdate()) < 19610101) { age = 60; }
        else if(Integer.parseInt(user.getBirthdate()) <19810101 ) { age = 4050; }
        else if(Integer.parseInt(user.getBirthdate()) < 19910101 ) { age = 30; }
        else if(Integer.parseInt(user.getBirthdate()) < 20010101 ) { age = 20; }
        else { age = 10; }

        System.out.println(age);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_bank:
                getActivity().startActivity(new Intent (getActivity(), EducationBank.class));
                break;
            case R.id.button_fraud:
                getActivity().startActivity(new Intent (getActivity(), EducationFraud.class));
                break;
            case R.id.button_insurance:
                getActivity().startActivity(new Intent (getActivity(), EducationInsurance.class));
                break;
            case R.id.button_stock:
                getActivity().startActivity(new Intent (getActivity(), EducationStock.class));
                break;
        }
    }
}
