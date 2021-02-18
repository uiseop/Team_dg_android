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

public class SenseActivity extends AppCompatActivity {
    private RecyclerView Senserecyclerview;

    private RecyclerView.LayoutManager S1Manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense);



        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }

        Senserecyclerview=  findViewById(R.id.sense_recyclerview);
        Senserecyclerview.setHasFixedSize(true);
        S1Manager = new LinearLayoutManager(this);
        Senserecyclerview.setLayoutManager(S1Manager);
        ArrayList<EducationCard> Sense1 = new ArrayList<>();
        Sense1.add(new EducationCard(R.drawable.sense_1,"기저효과","기저효과란 시점에 따라 주어진 상황을 달리 해석하는 현상이다\n\n"+
                "따라서, 수치 그 자체를 그대로 믿지 않고 수치 뒤에 가려진 사실을 파악하는 능력이 필요하다.\n\n"+
                "예를 들어, 2020년에 100원이 물건이 2021년에 150원이 되고 2022년에 200원이 되면 인상금액은 같지만, 21년에는 인상률이 50%이고, 22년에는 인상률이 33.3%인 것이다.\n" ));
        Sense1.add(new EducationCard(R.drawable.sense_2,"최저임금제","2021년 최저시급은 8720원이며 월급은 1,822,400원 이다.\n"+
                " 일의 종류에 상관없이 최저임금보다 적은 금액을 지급할 시 고용주는 3년이하의 징역 또는 2000만원 이하의 벌금이 부과됩니다." ));
        Sense1.add(new EducationCard(R.drawable.sense_3,"원금리스크","말 그대로 투자원금을 잃을 수도 있는 것입니다. 상대방이 파산하게 되면 매입통화를 받지 못하는 리스크입니다. 은행 예.적금 같은 경우는 원금 리스크가 거의 없고 투자는 원금 리스크가 있다고 할 수 있지요" ));
        Sense1.add(new EducationCard(R.drawable.sense_4,"크라우드\n"+"펀딩","온라인 플랫폼을 통해 불특정 다수에게 자금을 모으는 방식\n"+
               " 대표적인 플랫폼으로 ‘와디즈’라는 곳이 있다"
        )); Sense1.add(new EducationCard(R.drawable.sense_5,"골드칼라","두뇌와 정보로 새로운 가치를 창조하여 정보화시대를 이끌어가는 능력 위주의 전문직 종사자, 대표적으로 컴퓨터 프로그래머가 있습니다, 세계적으로 유명한 골드칼라 인물로 빌 게이츠가 있습니다"
        ));
        Sense1.add(new EducationCard(R.drawable.sense_6,"화이트스완","과거 경험에도 불구하고, 적절한 대책을 게을리하여 막지 못하는 위기상황\n" +
                "비슷한 말로 우리나라 속담에는 ‘소 잃고 외양간 고친다’ 라는 말이 있습니다.\n" +
                "‘인간의 욕심은 끝이 없고 같은 실수를 반복한다’고 하지만, 같은 실수를 반복해서는 안됩니다.\n"));
        Sense1.add(new EducationCard(R.drawable.sense_7,"워킹\n"+"홀리데이", "외국의 청소년 및 청년들에게 특별비자를 발급하여 여행 중인 방문국에서 취업 자격을 주는 제도\n" +
                "실제 체류기간은 1년으로 직접 돈을 벌면서 나라의 문화를 체험하는 좋은 기회가 될 수 있지만, 외국에서의 생활비를 충당하기 위해 생각보다 장시간 아르바이트를 해야 하며, 회화 능력이 부족하다면, 일자리를 구하지 못하는 경우도 있습니다"));
        Sense1.add(new EducationCard(R.drawable.sense_8,"리베이트", "판매자가 지불액의 일부를 구입자에게 환불하는 행위\n"
               +" 거래 장려의 목적을 지니고 있지만, 부정한 리베이트는 곧 뇌물이며 불법입니다."
        ));
        Sense1.add(new EducationCard(R.drawable.sense_9,"스타트업","혁신적 기술과 아이디어를 보유한 설립한지 오래되지 않은 창업기업\n" +
                "우버, 배달의 민족, 쏘카, 직방 이들은 모두 스타트업으로 성공한 대표적 사례들입니다. 여러분도 독창적인 아이디어로 스타트업에 도전해보세요\n" ));
        Sense1.add(new EducationCard(R.drawable.sense_10,"실업급여","고용보험 가입 근로자가 실직하여 재취업 활동을 하는 기간에 지급하는 급여, 퇴직전 3개월의 평균급여액이 기준이 됩니다.\n" +
                "재취업 활동이란 구직활동을 하거나 직업훈련을 수강하는 등의 행위를 말합니다.\n" +
                "자발적으로 퇴사한 경우는 실업급여를 받을 수 없습니다.\n"));
        Sense1.add(new EducationCard(R.drawable.sense_11,"상여금","급여 외에 지급되는 금품, 성과급이라고도 하며 보너스라고도 한다. 기대이상의 성과를 올린 경우 지급된다.\n" +"자사주로 지급하는 경우도 있다"
        ));
        Sense1.add(new EducationCard(R.drawable.sense_12,"애그\n"+"플레이션","농업의 agriculture와 인플레이션의 inflation의 합성어\n" +
                "농산물가격의 급격한 상승이 일반 물가의 상승을 일으키는 현상으로 기후이상, 농업인 감소, 곡물소비 증가등이 그 원인이 됩니다\n"));
        Sense1.add(new EducationCard(R.drawable.sense_13,"레드오션","이미 잘 알려져 있어서 경쟁이 매우 치열한 특정 산업내의 기존 시장\n" +
                "음식점, 생산업은 대표적인 레드오션의 예시입니다.\n"  ));
        Sense1.add(new EducationCard(R.drawable.sense_13,"블루오션","치열한 출혈경쟁을 벌이는 기존 시장과는 달리, 경쟁이 없거나 미약하여 가치 혁신을 통해 부가가치 창출과 비용 절감을 동시에 추구할 수 있는 새로운 시장"
        ));
        Sense1.add(new EducationCard(R.drawable.sense_15,"블루칼라","작업현장에서 일하는 노동자를 일컫는 말. 주로 청색 작업복을 입는 데서 유래된 말\n" +
                "주로 제조업 건설업 종사자가 이에 해당합니다.\n"));
        Sense1.add(new EducationCard(R.drawable.sense_16,"화이트칼라","샐러리맨이나 사무직 노동자를 일컫는 말. \n" +
                "주로 와이셔츠를 입는데서 유래된 말이다\n" ));




        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Sense1);

        Senserecyclerview.setAdapter(cardviewAdapter);
    }

}