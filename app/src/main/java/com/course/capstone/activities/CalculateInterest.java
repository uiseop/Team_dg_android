package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.course.capstone.R;
import com.course.capstone.adapter.InterestAdapter;
import com.course.capstone.models.Interest;

import java.util.ArrayList;

public class CalculateInterest extends AppCompatActivity {

    private TextView TLI,RPM;
    private EditText money, rate, period;
    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_interest);

        TLI = findViewById(R.id.total_loan_interest);

        money = findViewById(R.id.loanmoney);
        rate = findViewById(R.id.loanrate);
        period = findViewById(R.id.loanperiod);


        btn1 = findViewById(R.id.btn1);//원금균등
        btn2 = findViewById(R.id.btn2);//원리금균등
        btn3 = findViewById(R.id.btn3);//만기일시

        ListView listView = (ListView)findViewById(R.id.interest_lv);

        ArrayList<Interest> interestarr = new ArrayList<Interest>();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loanmoney = Integer.parseInt(money.getText().toString());
                int loanrate = Integer.parseInt(rate.getText().toString());
                int loanperiod = Integer.parseInt(period.getText().toString());

                final InterestAdapter interestAdapter = new InterestAdapter();
                listView.setAdapter(interestAdapter);
                TLI.setText( Integer.toString(loanmoney/loanperiod + loanmoney*loanrate/100)+"원" );
                for(int i=1; i<=loanperiod; i++){
                    interestarr.add(new Interest(Integer.toString(i),Integer.toString(loanmoney)));
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loanmoney = Integer.parseInt(money.getText().toString());
                int loanrate = Integer.parseInt(rate.getText().toString());
                int loanperiod = Integer.parseInt(period.getText().toString());

                loanrate = loanrate/12*100;

                int e = (int) ((loanmoney*loanrate*Math.pow(1+loanrate,loanperiod))/(Math.pow(1+loanrate,loanperiod)-1));
                System.out.println(e);
                RPM.setText(Integer.toString(e)+"원");
            }
        });
    }

}