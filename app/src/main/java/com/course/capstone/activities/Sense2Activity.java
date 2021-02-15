package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.course.capstone.R;
import com.course.capstone.adapter.CardviewAdapter;
import com.course.capstone.models.EducationCard;

import java.util.ArrayList;

public class Sense2Activity extends AppCompatActivity {
    private RecyclerView Sense2recyclerview;

    private RecyclerView.LayoutManager SManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense2);



        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }

        Sense2recyclerview=  findViewById(R.id.sense2_recyclerview);
        Sense2recyclerview.setHasFixedSize(true);
        SManager = new LinearLayoutManager(this);
        Sense2recyclerview.setLayoutManager(SManager);
        ArrayList<EducationCard> Sense2 = new ArrayList<>();
        Sense2.add(new EducationCard(R.drawable.sense2_1,"임금피크제","일정연령을 기준으로 임금을 삭감하는 대신 근로자의 고용을 보장하는 제도\n"+
                "근로자입장에서는 임금이 다소 삭감되더라도 지속적인 경제활동으로 안정된 생활을 계속할 수 있다는 점과\n"+"사용자입장에서는 인건비의 부담을 완화하며 장기 근속자의 풍부한 경험과 노하우를 활용할 수 있다는 장점이 있다"
        ));
        Sense2.add(new EducationCard(R.drawable.sense2_2,"금리","금리란 이자율과 같은 말로 원금에 지급되는 기간당 이자를 말한다.\n"+
                "금리가 상승하면 사람들은 소비보다는 저축을 추구하게 되는 경향이 있다.\n"+"그 결과로 경제활동이 둔화되며 물가가 자연스레 하락한다.\n" +"반대로 금리인하는 투자나 소비를 촉진시켜 경기를 활성화시킬 수도 있다."

        ));
        Sense2.add(new EducationCard(R.drawable.sense2_3,"코스피","일국내에 상장된 종목들의 주식 가격을 종합적으로 표시한 수치\n"+
                "1980년 1월 4일의 시가총액을 100을 기준으로 한다.\n"+".. 즉, 2021.02.10 코스피지수는 3100.58인데 기준시점에 비해 시가총액이 31배 상승했다고 할 수 있다. 국내 코스피 시가총액1위는 삼성전자로 그 액수가 500조에 달한다"));
        Sense2.add(new EducationCard(R.drawable.sense2_4,"국내 총생산","GDP(gross domestic product), 한 국가 안에서 생산한 상품의 가치를 모두 합한 금액\n"+
                " 자국민이 외국에서 벌어들인 금액은 GDP에 속하지 않고, 외국인이 국내에서 벌어들인 소득은 GDP에 속한다. 우리나라의 GDP는 세계에서 12위에 달한다"
        )); Sense2.add(new EducationCard(R.drawable.sense2_5,"블록체인","가상 화폐로 거래할 때 해킹을 막아주는 암호화 기술\n"+
                "P2P네트워크를 통해 이중 지불을 막는데 이 기술이 활용되고, 블록체인이 쓰인 가장 유명한 사례는 ‘비트코인’이다."
        )); Sense2.add(new EducationCard(R.drawable.sense2_6,"청년내일\n"+"채움공제","만 15~34세의 중소.중견기업에 정규직으로 취업한 청년들의 장기근속을 위한 정부사업.\n"+
                "2년간 본인은 300만원(매월 125000원) 기업은 300만원 정부는 600만원을지원하여 2년간 근속시 1200만원의 목돈을 마련해주는 제도이다. 2021년부터 2년형/3년형이 2년형으로 통합하였다." ));
        Sense2.add(new EducationCard(R.drawable.sense2_7,"개인\n" +
                "종합관리계좌","ISA,한 계좌에서 다양한 금융상품들을 이용할 수 있는 통합계좌, 세금을 크게 아낄 수 있다는 장점을 가진다. \n"+
                "의무 가입기간이 3년이상으로 연간 납입한도는 2000만원이다. 일임형과 신탁형이 있는데 일임형은 전문가에게 권한을 일임하여 전문가가 투자하는 식입니다"
        ));
        Sense2.add(new EducationCard(R.drawable.sense2_8,"리만브라더스\n " +
                "사태","2008년 미국의 가장 유명한 투자은행 중 하나인 리먼 브라더스가 파산하게 된 사태\n"+
                "부동산 가격이 가파르게 상승하던 시기 은행이 신용도가 낮은 사람한테도 집을 담보로 대출을 무차별적으로 해주다 높은 이자를 감당하지 못한 대출자들이 파산하게 되며 리먼 브라더스가 파산하게 되어 세계적으로 금융위기를 초래한 사태" ));
        Sense2.add(new EducationCard(R.drawable.sense2_9,"브릭스","2000년대 전후로 빠른 경제성장을 거듭하고 있는 브라질, 러시아, 인도, 중국, 남아프리카공화국 5국을 일컫는 경제용어. 2000년대 전후로 빠른 경제성장을 거듭하고 있는 브라질, 러시아, 인도, 중국, 남아프리카공화국 5국을 일컫는 경제용어.\n"+
                "기존 4개국에서 2010년 남아공이 가입하면서 5개국이 되었다. 기존 4개국은 거대한 영토와 인구, 풍부한 지하자원을 통해 성장요인을 갖추고 있으며 기존 4개국의 인구는 전 세계40%가 넘는다" ));
        Sense2.add(new EducationCard(R.drawable.sense2_10,"골디락스가격","고가,중간가,저가의 상품을 함께 진열함으로써 소비자가 중간가 상품을 선택하도록 유도하는 것\n"+
                "물건을 사기 전 무엇을 사야 하는지, 예산을 미리 정하고 가는 것이 중요합니다."));
        Sense2.add(new EducationCard(R.drawable.sense2_11,"디플레이션","경제 전반적으로 상품과 서비스의 가격이 지속적으로 하락하는 현상\n"+
                "디플레이션 하에서는 현금 보유량을 늘리는 등 안전한 투자를 하는 것이 유리합니다."  ));
        Sense2.add(new EducationCard(R.drawable.sense2_12,"소비자 \n" +
                "물가지수","CPI(Consumer Price Index), 소비자가 구입하는 상품과 서비스의 가격변동을 측정하기 위한 지표. 이 값이 10%상승하면 기존소득으로 똑같은  물건을 구매할 수 있는 수량이 10%감소하는 것이다."));
        Sense2.add(new EducationCard(R.drawable.sense2_13,"기회비용","어떤 선택으로 인해 포기된 기회들 가운데 가장 큰 가치를 갖는 기회 자체 또는 그러한 기회가 갖는 가치.\n"+
                ". 예를 들어 연봉이5천인 A회사와 연봉이 6천인 B회사 중에 B회사를 택한다면 기회비용은 5천만원이 되는 것이다"  ));
        Sense2.add(new EducationCard(R.drawable.sense2_14,"인수합병","한 기업이 다른기업의 주식이나 자산을 취특하며 경영권을 획득해 하나의 기업으로 합쳐지는 것\n"+
                "과거 현대차가 기아차를 인수함으로써 급성장하게 되어 현재는 세계 시장에서 인정받는 회사가 되었다."
        ));
        Sense2.add(new EducationCard(R.drawable.sense2_15,"인플레이션","통화량의 증가로 화폐가치가 하락하고, 모든 상품의 물가가 전반적으로 꾸준히 오르는 경제 현상\n"+
                "경제의 악영향을 주지만, 화폐가치가 하락하기 때문에 빚을 갚을 사람에게는 유리해지는 측면도 있습니다. 현재 베네수엘라는 초인플레이션으로 인해 물가상승률이 130만%에 이르며 국가부도위기상태에 놓여있습니다."));

        Sense2.add(new EducationCard(R.drawable.sense2_16,"생활물가지수","체감물가를 파악하기 위해 일상생활에서 구입 빈도가 높고 지출비중이 높아 가격변동을 민감하게 느끼는 생활필수품을 대상으로 작성한 소비자물가지수의 보조지표" ));
        Sense2.add(new EducationCard(R.drawable.sense2_17,"낙수효과","고소득층의 소득 증대가 소비 및 투자 확대로 이어져 저소득층의 소득도 증가하게 되는 효과.우리나라의 산업구조가 대표적인 이 효과를 보여준다\n"+
                "반대되는 말로는 분수효과가 있습니다"  ));




        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Sense2);

        Sense2recyclerview.setAdapter(cardviewAdapter);
    }

}