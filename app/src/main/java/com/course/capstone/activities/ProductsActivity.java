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

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView Productsrecyclerview;

    private RecyclerView.LayoutManager PManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }

        Productsrecyclerview=  findViewById(R.id.products_recyclerview);
        Productsrecyclerview.setHasFixedSize(true);
        PManager = new LinearLayoutManager(this);
        Productsrecyclerview.setLayoutManager(PManager);
        ArrayList<EducationCard> Product = new ArrayList<>();
        Product.add(new EducationCard(R.drawable.product_1,"실적배당형","MMF라고 하며 증권사에서 취급하는 CMA와 달리 은행에서 취급하고 운용 실적에 따라 수익금을 배분받게 됩니다.\n"
                +"시중 금리 변동에 큰 영향을 받지 않아 비교적 수익이 안정적이며 수수료 없이 언제든 환매가 가능해 단기로 자금을 운용하는 투자자에게 적합합니다. "));
        Product.add(new EducationCard(R.drawable.product_2,"재형저축","연봉 5천만원 이하 근로자와 종합소득 3천 5백만원 이하 개인사업자에게만 가입자격이 주어졌던 만기 10년의 적립식 저축상품입니다. 이자 및 배당소득에 대해 비과세 혜택이 있어 근로자들의 대표적인 절세 상품으로 인기를 끌었었다.\n" +
                "\n" +
                "재형저축은 1976년 도입된 뒤 1995년 폐지됐다. 또한 2012년 재도입이 결정됐고 2013년 은행권을 통해 부활됐으나 2015년 12월 31일 일몰되었다." +
                "\n"
        ));
        Product.add(new EducationCard(R.drawable.product_3,"회사채","주식회사가 투자자로부터 회사운영에 필요한 자금을 조달받기 위한 목적으로 발행하는 채권입니다.\n" +
                " 회사채 채권자는 주주들보다 배당 및 잔여재산 분배권에서 우선권을 가지고 있으나, 회사의 경영권은 없습니다. 주로 3~5년 만기로 발행됩니다."
        ));
        Product.add(new EducationCard(R.drawable.product_4,"정기예금","목돈을 한꺼번에 맡기고, 정해진 기간이 지난 다음에 약속한 이자와 함께 찾을 수 있는 저축입니다. \n" +
                "주로 3개월 단위로 가입할 수 있으며, 오래 맡길수록 더 높은 이자를 받을 수 있습니다."
        ));
        Product.add(new EducationCard(R.drawable.product_5,"장기주택\n"+"마련저축","장기주택마련저축은 집 없는 서민들의 주택자금 마련을 돕기 위해 탄생한 금융상품입니다.\n " +
                "그렇다고 장기주택마련저축으로 모은 돈을 반드시 집을 사는 데만 사용해야 하는 것은 아닙니다. 그 취지가 그렇다는 뜻입니다.\n" +
                "\n"
        ));
        Product.add(new EducationCard(R.drawable.product_6,"환매 \n"+"조건부채권","고금리 경쟁이 심화되면서 일반인에게는 다소 생소했던 RP형이 부쩍 관심의 대상이 되고 있다. \n" +
                "환매조건부채권 = RP  은행이나 증권사에서 일정기간이 지나면 되사는 조건으로 채권을 판 뒤  만기 때 고객에게 원금과 이자를 지급하는 방식의 상품이다.\n" +
                "3개월 가량 여유자금을 운용하기에 적합하다"
        ));
        Product.add(new EducationCard(R.drawable.product_7,"부동산\n"+"펀드","부동산 펀드란 펀드재산 50%를 초과하여 부동산 및 관련 자산에 투자하는 펀드를 말합니다. \n" +
                "종류는 공개적으로 모아 유가증권시장에 상장하는 공모펀드와, 투자자를 한정적으로 모아 설정하는 사모 펀드로 나누어지게 됩니다. \n" +
                "공모펀드가 사모펀드에 비해 초보자가 조금 더 접하기 쉽습니다."
        ));
        Product.add(new EducationCard(R.drawable.product_8,"콜론형특정\n금전신탁","기업이나 개인이 증권사 등에 돈을 맡기고 증권사는 투자자가 원하는 방식으로 운영해 수익을 내고 일정 수수료를 제외한 금액을 돌려주는 것을 의미한다. \n" +
                "또 MMT는 입출금이 자유롭고 시장실세금리 이상을 주기 때문에 마땅한 투자처가 없는 여유자금을 넣어두기에 적합한 투자상품이다. \n"+
                "다만 예금자 보호는 받지 못한다는 단점이 있다."
        ));
        Product.add(new EducationCard(R.drawable.product_9,"특수채","특수채는 특별법에 의해 설립된 기관이 발행하는 채권으로, 주로 공기업이 발행하는 채권을 통칭합니다.\n " +
                "대표적인 특수채로는 한국전력공사채권, 토지개발채권등이 있습니다."
        ));
        Product.add(new EducationCard(R.drawable.product_10,"신노후생활\n연금신탁","만 18세 이상이면 가입이 가능하고 만기가 40세 이상이거나 최저 5년 이상의 노후대비용 상품으로 판매되는 실적배당형 연금 상품이다. \n" +
                "원래 자영업자·월급쟁이들의 노후대비용 상품으로 개발한 실적배당형 연금상품이다. \n" +
                "하지만 돈을 1년 이상만 예치하면 중도해지에 따른 불이익이 전혀 없기 때문에 단기 투자상품으로 더 관심을 끌고 있다."
        ));
        Product.add(new EducationCard(R.drawable.product_11,"국채", "국채는 한 국가의 중앙정부가 자금을 조달하기 위해 발행하는 채권입니다.\n" +
                " 나라가 망하지 않는 한 돈을 떼는 위험이 없으므로 가장 안전한 대신 금리가 낮습니다.\n " +
                "국채는 정부가 빚을 낸 것 이므로 언젠가는 갚아야 합니다. \n" +
                "국채 발행량이 증가하면 미래에 세금부담이 그만큼 늘어나는 겁니다.\n"));
        Product.add(new EducationCard(R.drawable.product_12,"배당주\n펀드", "배당주펀드는 배당을 많이 주고, 또 꾸준히 주는 회사를 발굴해서 투자하는 펀드입니다.\n " +
                "장기적으로 안정적 수익을 바라는 사람들이 투자할 수 있는 펀드입니다. \n" +
                "은행 금리나 채권형 펀드의 수익은 너무 낮은 것 같고,\n " +
                "그렇다고 대박 추구형 펀드는 왠지 무서워서 가까이 하기 힘들다면\n " +
                "관심을 가져볼 만한 펀드입니다."));
        Product.add(new EducationCard(R.drawable.product_13,"주택청약\n"+"종합저축", "우리가 내 집을 마련하는 방법에는 여러가지가 있지만, 가장 손쉽게 접근할 수 있는 방법이 바로 ‘분양’ 인데요.\n " +
                "주택청약 제도는 이렇게 주택을 분양 받으려는 사람이 분양주택의 종류에 따라\n " +
                "일정한 입주자격을 갖추어 사겠다는 의사표시로 청약저축에 가입하는 것을 말해요.\n "));
        Product.add(new EducationCard(R.drawable.product_14,"양도성\n"+"예금증서", "CD란 certificate of deposit의 줄임말로 우리말로는 양도성 예금증서라고 합니다. \n" +
                "예금증서, 즉, 금융기관이 예금자에게 주는 보증증서,\n " +
                "이를 제 3자에게 양도가 가능한 예금상품을 말합니다. \n" +
                "무기명으로 발행이되며 만기일시까지 자유로이 거래가 가능한 상품이기 때문에 일반예금과는 다릅니다.\n"
        ));
        Product.add(new EducationCard(R.drawable.product_15,"생명보험", " 가입자가 세상을 떠났을 때 유가족에게 보험금을 지급하는 보험 상품입니다.\n" +
                " 보통 가족의 생계를 책임지는 가장이 많이 가입하는데,\n" +
                " 최근에는 가장이 아닌 가족구성원 중에서도 가입을 하고 있습니다."));


        Product.add(new EducationCard(R.drawable.product_16,"수시입출금\n"+"예금", "MMDA는 증권사의 단기금융상품인 MMF에 대응하기 위해 도임한 고금리 저축성 예금을 말하는데 통상 \n" +
                "‘시장 금리부 수시 입, 출금식 예금’이라고 한다. \n" +
                "일반 저축상품과 마찬가지로 각종 결제. 이체가 가능하다고 3개월마다 이자를 계산하여 지급한다.  "));
        Product.add(new EducationCard(R.drawable.product_17,"정기적금", "매달 일정한 돈을 정해진 기간 동안 저금한 뒤, 약속한 이자를 받는 저축입니다.\n" +
                " 정기적금은 주로 1년 이상으로 가입할 수 있고,\n " +
                "정기 예금과 마찬가지로 오래 맡길수록 더 높은 이자를 받을 수 있습니다. "));

        Product.add(new EducationCard(R.drawable.product_18,"연금보험", "내가 돈을 버는 시기에 조금씩 납입하여 모아둔 후 시간이 흐른 특정 시점이 되었을 때 \n" +
                "납입하고 적립하고 운용했던 돈을 나누어서 받는 상품이다.\n" +
                " 복리 부리를 하기 때문에 오래 유지할수록 더 많은 이익을 가져갈 수 있는 특징이 있다. "));

        Product.add(new EducationCard(R.drawable.product_19,"보통예금", "필요할 때 언제든지 돈을 맡기고 찾을 수 있는 저축입니다.\n" +
                " 은행에서는 손님이 언제 돈을 찾아갈지 모르기 때문에 이자를 많이 주지 않습니다. "));

        Product.add(new EducationCard(R.drawable.product_20,"기본예탁금", "선물거래는 그 위험이 주식보다 훨씬 크다고 볼 수 있다.\n" +
                " 따라서 선물거래 참여자를 제한(조절)할 목적으로 계좌에 일정금액 이상이 있어야만 선물거래를 할 수 있는데 이를 기본 예탁금이라고 한다.  "));
        Product.add(new EducationCard(R.drawable.product_21,"종금사형", "가장 오래된 CMA이며 종합금융회사에서 판매하는 상품으로 예금보험공사에서 원금보장을 받을 수 있는 상품입니다.\n" +
                " 우량채권 위주로 거래를 하기 때문에 손실의 위험이 자극히 낮은 것을 고려하여 \n" +
                "고금리의 상품을 선택할 것을 추천합니다."));

        Product.add(new EducationCard(R.drawable.product_22,"발행어음", "금융회사가 영업자금 조달을 위해 자체 신용으로 융통어음을 발행하여, \n" +
                "일반 투자자에게 매출하는 형식의 1년 미만 단기 금융상품을 말합니다.\n " +
                "발행어음은 증권사 신용으로만 발행하는 상품이고, \n" +
                "신용도가 높은 초대형 증권사만 발행이 가능하기 때문에 국내에서는 한국투자증권, KB증권, NH투자증권 단 3곳에서만 취급합니다."));

        Product.add(new EducationCard(R.drawable.product_23,"출자금", " 기업의 주인이 주주라면 협동조합의 주인은 출자금을 낸 조합원들입니다. \n" +
                "조합원들이 가입할 때 내는 출자금과 이후 매장이용과 주문공급 시마다 적립되는 출자금들이 모여서\n" +
                " 생협의 운영과 직거래 사업에 필요한 건물임대, 차량구매, 매장임대, 물류센터건립 등 각종 투자금이나 고정자산 구입을 위해 사용됩니다."));

        Product.add(new EducationCard(R.drawable.product_24,"저축성\n은행예금", "예금주가 일정기간 동안은 돈을 회수하지 않을 것을 약속하고\n" +
                " 일정 금액을 은행에 예치하여, 은행은 이에 대해 이자를 지급할 것을 약속하고\n" +
                " 증서 또는 통장을 발행, 교부하는 예금을 말한다. "));

        Product.add(new EducationCard(R.drawable.product_25,"단기채권", "단기채권은 상환기간이 1~2년 이하인 채권이다.\n\n" +
                " 다만, 중기채권, 장기채권이라도 상환기간이 1~2년 이내로 돌아오면 단기채권로 변한다. \n" +
                "금리가 계속 상승 될 경우 장기채권에서 금리 손해 볼 것이 뻔하기 때문에 금리상승기나 막바지에 투자하는 것이 옳지만 \n" +
                "개인별, 집단간의 이런저런 이유로 금리하락기에도 투자하는 경우가 있다. 대표적인 예로는 ETF 등이 있다."));
        Product.add(new EducationCard(R.drawable.product_26,"표지어음", "표지어음이란 말 그대로 몇 가지 어음을 근거로 대표적인 어음(표지)을 만든다는 뜻이다.\n" +
                " 기업들은 자금조달을 목적으로 기업어음(CP), 무역어음 등을 발행한다.\n" +
                " 금융기관들은 이들 어음을 근거로 별도의 자체 어음을 발행한다."));

        Product.add(new EducationCard(R.drawable.product_27,"지방채", "지방자치단체가 재정상의 필요에 의해 발행하는 채권을 말한다.\n" +
                " 발행 기관은 특별시, 광역시, 도 등 광역자치단체와 시, 군 등 기초자치단체이며,\n" +
                " 지방자치단체가 발행 주체이므로 국가가 발행하는 국채보다 규모가 작고, 신용도와 유동성도 낮은 편이다. \n" +
                "지하철공채, 상수도공채, 도로공채등이 있다."));

        Product.add(new EducationCard(R.drawable.product_28,"금융채", "금융채는 은행, 종합금융회사, 여신금융전문회사 등 금융기관의 자금조달을 위하여 자체 발행하는 채권입니다.\n" +
                " 시장금리 면에서 금융채는 은행이 채권을 발행 할 때 제공하는 금리를 토대로 산출 한 수치입니다. \n" +
                "채권 상품으로 일반 채권들과 함께 거래되기 때문에 \n" +
                "시장의 수요와 공급에 따라 가격변동이 상시 일어날 수 있습니다. "));

        Product.add(new EducationCard(R.drawable.product_29,"후순위\n"+"채권", "후순위란 순서가 가장 뒤라는 이야기입니다.\n" +
                " 즉, 기업이 부도가 날 경우 남들에게 진 빚을 모두 갚고 맨 나중에 갚는 채권입니다.\n" +
                " 따라서 투자자들의 입장에서는 그만큼 돈을 떼일 확률이 높다고 할 수 있습니다. \n" +
                "상대적으로 부도 위험이 적은 은행에서 주로 발행하며 이자를 많이 쳐줍니다."));

        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Product);

        Productsrecyclerview.setAdapter(cardviewAdapter);
    }
}