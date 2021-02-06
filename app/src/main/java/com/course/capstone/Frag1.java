package com.course.capstone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.course.capstone.activities.TestActivity;
import com.course.capstone.adapter.CurrentAdapter;
import com.course.capstone.adapter.ImageAdapter;
import com.course.capstone.models.ChartDTO;
import com.course.capstone.models.DataBean;
import com.youth.banner.Banner;
import com.youth.banner.util.BannerUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Frag1 extends Fragment {
    TextView news_title;
    String nums;
    Bitmap bitmap;
    ImageView imageView,test;
    URL iurl;
    String nurl;

    //환율데이터 레이아웃
    private RecyclerView recyclerView;
    private ArrayList<ChartDTO> list = new ArrayList();
    //뉴스데이터 받아오는곳
    private RecyclerView recyclerView2;
   // private ArrayList<Search> slist = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag1,container,false);

        //환율데이타 받아오는곳
        recyclerView = v.findViewById(R.id.recyclerview);
        CurrentAdapter myAdapter = new CurrentAdapter(list);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        //뉴스데이타 받아오는곳
        final Bundle bundle = new Bundle();
        imageView = v.findViewById(R.id.imageView);
        news_title = v.findViewById(R.id.news);
        //검색어 데이터 받아오는곳
//        recyclerView2 = v.findViewById(R.id.searchRV);
//        SearchAdapter sAdapter = new SearchAdapter(slist);
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
//        recyclerView2.setLayoutManager(layoutManager2);
//        recyclerView2.setAdapter(sAdapter);
        test = v.findViewById(R.id.imageView4);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent (getActivity(), TestActivity.class));
            }
        });

        Thread mThread = new Thread(){
            ArrayList<String> listTitle = new ArrayList<>();
            ArrayList<String> listCurrent = new ArrayList<>();
            ArrayList<String> listP = new ArrayList<>();
            ArrayList<String> listR = new ArrayList<>();

            @Override
            public void run() {
                Document doc = null;
                Document doc2 = null;
                Document doc3 = null;
                Bitmap bmp = null;
                try {
                    doc = Jsoup.connect("https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=101").get();
                    doc2 = Jsoup.connect("https://m.stock.naver.com/marketindex/index.nhn").get();

//환율 데이터 먼저 받아오자
                    Elements mElementDataSize = doc2.select("ul[class=international_lst]").select("li"); //필요한 녀석만 꼬집어서 지정
                    int mElementSize = 4; //목록이 몇개인지 알아낸다. 그만큼 루프를 돌려야 하나깐.

                    for(Element elem : mElementDataSize){ //이렇게 요긴한 기능이
                        //영화목록 <li> 에서 다시 원하는 데이터를 추출해 낸다.
                        listTitle.add(elem.select("a div[class=item_wrp] strong").text());
                        listCurrent.add(elem.select("a div[class=price_wrp] span[class=stock_price]").text());
                        String my_gapp = elem.select("a div[class=price_wrp] div[class=gap_wrp] span[class=gap_price]").text();
                        if(my_gapp.substring(0,2).equals("상승")){
                            my_gapp = "+" + my_gapp.substring(2,my_gapp.length());
                        }
                        else{
                            my_gapp = "-" + my_gapp.substring(2,my_gapp.length());
                        }
                        listP.add(my_gapp);
                        listR.add(elem.select("a div[class=price_wrp] div[class=gap_wrp] span[class=gap_rate]").text());

                        //Log.d("test", "test" + mTitle);
                        //ArrayList에 계속 추가한다.
                    }
                    for(int i=0; i<4; i++){
                        ChartDTO data = new ChartDTO();
                        data.setTitle(listTitle.get(i));
                        data.setCurrent(listCurrent.get(i));
                        data.setGapPrice(listP.get(i));
                        data.setGapRate(listR.get(i));

                        myAdapter.addItem(data);
                    }
                    myAdapter.notifyDataSetChanged();

//여기까지가 환율데이터
                    Element contents = doc.select("div.cluster_text a").first();
                    nurl = contents.attr("href");
                    iurl = new URL(doc.select("div.cluster_thumb_inner a img").attr("src"));
                    HttpURLConnection conn = (HttpURLConnection)iurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                    nums = contents.text();
//                    for(int i = 1; i < 7; i++){
//                        contents = doc.select("#drwtNo"+i);                 //복권 번호 6개 가져오기
//                        nums += " "+contents.text();
//                    }
//                    nums += doc.select("#bnusNo").text();                   //보너스 번호 contents 변수를 사용하지 않고 가져오는 방법

                    bundle.putString("numbers", nums);                               //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                    Message msg = myHandler.obtainMessage();
                    msg.setData(bundle);
                    myHandler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();

        try {
            mThread.join();
            imageView.setImageBitmap(bitmap);

        }catch (InterruptedException e){
            e.printStackTrace();
        }



        //환율 리사이클러뷰 적용용


       //여기까지 -----------

        //배너 시작부분 ------
        Banner banner;
        banner = v.findViewById(R.id.banner);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(nurl));
                startActivity(intent);
            }
        });

        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());

        banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)
//                .setIndicator(new RectangleIndicator())
                .setIndicatorSelectedColor((int) BannerUtils.dp2px(12))
                .setIndicatorSpace((int) BannerUtils.dp2px(4))
                .setIndicatorRadius(0)
                .setOnBannerListener((data, position) -> {
//                    Snackbar.make(banner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
                    String url = ((DataBean)data).title;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                });
        return v;
    }

    Handler myHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            news_title.setText(bundle.getString("numbers"));
        }
    };

}
