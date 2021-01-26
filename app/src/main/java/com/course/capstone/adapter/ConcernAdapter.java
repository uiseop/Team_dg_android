package com.course.capstone.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.course.capstone.R;
import com.course.capstone.models.Concern;
import com.course.capstone.models.ConcernInterface;
import com.course.capstone.models.DataManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConcernAdapter extends BaseAdapter {
    private ArrayList<Concern> listViewItemList = new ArrayList<>() ;

    // 생성자
    public ConcernAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_concern, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textTextView = convertView.findViewById(R.id.tv_concern);
        CheckBox checkBox = convertView.findViewById(R.id.check_concern);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Concern listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textTextView.setText(listViewItem.getName());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ConcernInterface concernInterface = retrofit.create(ConcernInterface.class);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked() == false){
                }
                else if (checkBox.isChecked() == true){
                    DataManager dataManager = DataManager.getInstance();
                    String parentid = dataManager.getUser().getId();
                    String concernname = listViewItem.getName();
                    Concern concern = new Concern(concernname,parentid);

                    System.out.println(parentid);
                    Call<Concern> call = concernInterface.addInterest(concern);
                    call.enqueue(new Callback<Concern>() {
                        @Override
                        public void onResponse(Call<Concern> call, Response<Concern> response) {
                            System.out.println("onresponse");
                        }

                        @Override
                        public void onFailure(Call<Concern> call, Throwable t) {
                            System.out.println("onfailure");
                        }
                    });
                }
            }
        });

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem( String text,String parentid) {
        Concern item = new Concern(text,parentid);

        item.setName(text);

        listViewItemList.add(item);
    }
}
