package com.course.capstone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.course.capstone.MainActivity;
import com.course.capstone.R;

public class TestResultActivity extends AppCompatActivity {

    Button result;
    TextView a,b,c,d,e;
    TextView a1,b1,c1,d1,e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        result = findViewById(R.id.button_testresult);
        a = findViewById(R.id.textView_dkswjdgud);
        b = findViewById(R.id.textView_dkswjdcnrngud);
        c = findViewById(R.id.textView_dnlgjawndflqgud);
        d = findViewById(R.id.textView_wjrrmrxnwkgud);
        e = findViewById(R.id.textView_rhdrurxnwkgud);
        a1 = findViewById(R.id.textView20);
        b1 = findViewById(R.id.textView2040);
        c1 = findViewById(R.id.textView4060);
        d1 = findViewById(R.id.textView6080);
        e1 = findViewById(R.id.textView80);

        double score = getIntent().getDoubleExtra("score",0);
        System.out.println(score);

        if(score <= 20){
            a.setTextColor(Color.parseColor("#FF03DAC5"));
            a1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else if (score <= 40){
            b.setTextColor(Color.parseColor("#FF03DAC5"));
            b1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else if (score <= 60){
            c.setTextColor(Color.parseColor("#FF03DAC5"));
            c1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else if (score <= 80){
            d.setTextColor(Color.parseColor("#FF03DAC5"));
            d1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else {
            e.setTextColor(Color.parseColor("#FF03DAC5"));
            e1.setTextColor(Color.parseColor("#FF03DAC5"));
        }

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                if(score<=20){
                    new AlertDialog.Builder(context).setTitle("안정형")
                            .setMessage("예금이나 적금 수준의 수익률을 기대하며, 투자원금에 손실이 발생하는 것을 원하지 않는다. 원금손실의 우려가 없는 상품에 투자하는 것이 바람직하며 CMA와 MMF가 좋다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(context, MainActivity.class);
                                    startActivity(i);
                                }
                            }).show();
                }
                else if(score <=40){
                    new AlertDialog.Builder(context).setTitle("안정추구형")
                            .setMessage("투자원금의 손실위험은 최소화하고, 이자소득이나 배당소득 수준의 안정적인 투자를 목표로 한다. 다만 수익을 위해 단기적인 손실을 수용할 수 있으며, 예·적금보다 높은 수익을 위해 자산 중의 일부를 변동성 높은 상품에 투자할 의향이 있다. 채권형펀드가 적당하며, 그중에서도 장기회사채펀드 등이 좋다.\n" )
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(context, MainActivity.class);
                                    startActivity(i);
                                }
                            }).show();
                }
                else if(score <=60){
                    new AlertDialog.Builder(context).setTitle("위험중립형")
                            .setMessage("투자에는 그에 상응하는 투자위험이 있음을 충분히 인식하고 있으며, 예·적금보다 높은 수익을 기대할 수 있다면 일정수준의 손실위험을 감수할 수 있다. 적립식펀드나 주가연동상 품처럼 중위험 펀드로 분류되는 상품을 선택하는 것이 좋다.\n")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(context, MainActivity.class);
                                    startActivity(i);
                                }
                            }).show();
                }
                else if(score <=80){
                    new AlertDialog.Builder(context).setTitle("적극투자형")
                            .setMessage("투자원금의 보전보다는 위험을 감내하더라도 높은 수준의 투자수익을 추구한다. 투자자금의 상당 부분을 주식, 주식형펀드 또는 파생상품 등의 위험자산에 투자할 의향이 있다. 국내외 주식형펀드와 원금비보장형 주가연계증권(ELS) 등 고수익·고위험 상품에 투자할 수 있다.\n")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(context, MainActivity.class);
                                    startActivity(i);
                                }
                            }).show();
                }
                else{
                    new AlertDialog.Builder(context).setTitle("공격투자형")
                            .setMessage("시장평균수익률을 훨씬 넘어서는 높은 수준의 투자수익을 추구하며, 이를 위해 자산가치의 변동에 따른 손실위험을 적극 수용할 수 있다. 투자자금 대부분을 주식, 주식형펀드 또는 파생상품 등의 위험자산에 투자할 의향이 있다. 주식 비중이 70% 이상인 고위험 펀드가 적당하고, 자산의 10% 정도는 직접투자(주식)도 고려해볼 만하다.\n")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(context, MainActivity.class);
                                    startActivity(i);
                                }
                            }).show();
                }
            }
        });
    }
}