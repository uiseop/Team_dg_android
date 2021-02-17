package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.course.capstone.R;
import com.course.capstone.adapter.CardviewAdapter;
import com.course.capstone.adapter.MyAdapter;
import com.course.capstone.models.EducationCard;

import java.util.ArrayList;

public class TaxActivity extends AppCompatActivity {
    private RecyclerView taxrecyclerview;

    private RecyclerView.LayoutManager TManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }

        taxrecyclerview=  findViewById(R.id.info_recyclerview);
        taxrecyclerview.setHasFixedSize(true);
        TManager = new LinearLayoutManager(this);
        taxrecyclerview.setLayoutManager(TManager);
        ArrayList<EducationCard> Tax = new ArrayList<>();
        Tax.add(new EducationCard(R.drawable.tax_1,"비과세","국세청 등이 세금을 부과하는 과세권이  처음부터 없는 것으로, 세금 자체가 발생하지 않으므로 신고의 의무도 없다.\n" +"Q.감면vs비과세\n" +"감면: 감면요건을 충족한 뒤 신고하고 감면 신청을 해야하는 등 신고의무와 납세협력의무가 있다.\n" +"비과세: 당초 세금 자체가 발생하지 않으므로 세금을 신고,신청 납부할 필요가 없다."));
        Tax.add(new EducationCard(R.drawable.tax_2,"비과세소득","비과세 소득은 세법에서 특별히 과세하지 않는 소득으로 규정한 소득이다.근로자 본인의 학자금, 실업급여, 출산&보육수당, 숙직료, 자가 운전 보조금등이 있다."));
        Tax.add(new EducationCard(R.drawable.tax_3,"세후","세금을 공제 받은 후 실수령 금액을 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_4,"세전","세금을 공제 받기 전의 금액을 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_5,"과세소득","소득세의 부과 대상이 되는 소득, 전체 소득 중에서 과세대상이 되지 않는 비과세 소득 및 면세 소득이외의 모든 소득을 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_6,"갑근세","갑종근로소득으로 분류된 소득에 대하여 부과되는 조세입니다. "));
        Tax.add(new EducationCard(R.drawable.tax_7,"지방세","지방자치단체가 당해 지방자치단체의 재정수요에 충당하기 위하여 주민에게 부과,징수하는 조세를 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_8,"종합부동산세","지난 2005년도부터 부동산 투기 수요를 억제하여 부동산 가격을 안정시키기 위한 목적으로 시행되었다.\n" +
                "현재 국내에 소재한 재산세 과세대상인 주택 및 토지를 유형별로 구분하여 인별로 합산해 그 공시가격 합계액이 각 유형별로 공제금액을 초과하는 경우 그 초과분에 대하여 과세하는 세금이다.\n"));
        Tax.add(new EducationCard(R.drawable.tax_9,"양도소득세","토지,건물 따위를 유상으로 양도하여 얻은 이익에 대하여 차익만큼 과세를 부과하는 세금을 말한다.\n" +
                "예를들어 어떠한 토지를 15년을 보유하여 5억원의 양도차익이 생겼다면 5억원에 대한 양도소득세를 납부해야한다.\n"));
        Tax.add(new EducationCard(R.drawable.tax_10,"보통징수","지방세의 징수 방법중 하나로 세무공무원이 납세고지서를 납세자에게 발급하여 지방세를 징수하는 것을 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_11,"과세주체","세금을 매기는 주체로 국가나 지방 자치 단체를 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_12,"결정세액","산출세액에서 세액고제액과 감면세액을 공제한 금액을 말한다."));
        Tax.add(new EducationCard(R.drawable.tax_13,"인지세","재산상의 권리를 표시하는 증서를 대상으로 작성자에게 부과하는 세금.\n" +
                "예를들어 부동산을 취득할 때 자신의 이름으로 집주인을 등록하기 위해서 등기소에서 소유권이전 등기를 하면 OOO도 같이 부과된다.\n" +
                "\n"));
        Tax.add(new EducationCard(R.drawable.tax_14,"소득공제","과세의 대상이 되는 소득중에서 일정금액을 공제하여 주는 것으로 납세자의 세금 부담을 얼어주고 최저생계비를 보장하는 데 목적이 있다."));
        Tax.add(new EducationCard(R.drawable.tax_15,"과세표준","세금부과 기준이 되는 소득 구간으로 종합소득에서 소득공제 금액을 뺀 것이다.\n" +
                "예를들어 월급이 100만원일때 필수적으로 사용해야하는 금액이 40만원이라면, 필수적으로 필요한 40만원을 소득이 아닌 것으로 계산해주는 소득공제를 통해 60만원으로 과세표준을 구할 수 있다.\n" +
                "\n"));
        Tax.add(new EducationCard(R.drawable.tax_16,"부가가치세(VAT)","생산 및 유통과정의 각 단계에서 창출되는 부가 가치에 대해 부과되는 조세이다. 소비 거래를 하면 나타나는 세금 재화와 용역을 최종적으로 제공받는 사람들이 내는 세금이다.\n" +
                "우리나라는 현재 재화 및 용역의 최종 가격에 10%의 부가가치세가 포함된다.\n"));
        Tax.add(new EducationCard(R.drawable.tax_17,"지방소득세","국세의 부가세 일부를 지방세로 돌려 활용하는 것으로 지자체의 재정자립도를 높이려는 목적으로 도입이 시도 되고 있는 세금이다.\n"+
                "개인의 경우 소득세 과세표준의 0.6%~4.0%, 법인은 법인세 과세표준의 1.0%~2.5%가 세금으로 매겨진다."));
        Tax.add(new EducationCard(R.drawable.tax_18,"원천징수","소득 또는 수입금액을 지급하는 자(원천징수의무자)가 그 금액을 지급할 때, 상대방(원천납세의무자)이 내야할 세금을 국가를 대신하여 징수 하고 납부하는 조세 징수 방법 중의 하나이다."));
        Tax.add(new EducationCard(R.drawable.tax_19,"주민세","지방세의 일종으로 자신이 거주하고 있는 소재지에서 거주자에게 부과하는 세금입니다."));

        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Tax);

        taxrecyclerview.setAdapter(cardviewAdapter);
    }
}