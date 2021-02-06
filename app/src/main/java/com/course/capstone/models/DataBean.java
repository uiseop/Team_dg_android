package com.course.capstone.models;

import com.course.capstone.R;

import java.util.ArrayList;
import java.util.List;

public class DataBean {
    public Integer imageRes;
    public String title;
    public int viewType;

    public DataBean(Integer imageRes, String title, int viewType){
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public String getTitle(){
        return title;
    }

    public static List<DataBean> getTestData(){
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.dongguk_logo_1,"http://www.dongguk.edu/mbs/kr/index.jsp",1));
        list.add(new DataBean(R.drawable.dongguk_logo_2,"http://www.naver.com",2));
        return list;
    }
}
