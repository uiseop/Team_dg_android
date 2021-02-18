package com.course.capstone.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.capstone.R;
import com.course.capstone.models.Bank;
import com.course.capstone.models.BankInterface;
import com.course.capstone.models.DataManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankAdapter extends BaseAdapter {
    private ArrayList<Bank> listViewItemList = new ArrayList<Bank>() ;

    // ListViewAdapter의 생성자
    public BankAdapter() {

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
            convertView = inflater.inflate(R.layout.item_bank, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView textTextView = convertView.findViewById(R.id.textView1);
        EditText editText = convertView.findViewById(R.id.account);
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox1);
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Bank listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        textTextView.setText(listViewItem.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BankInterface bankInterface = retrofit.create(BankInterface.class);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkBox.isChecked()) {
                } else if (checkBox.isChecked()) {
                    DataManager dataManager = DataManager.getInstance();
                    String parentid = dataManager.getUser().getId();
                    int account = Integer.parseInt(editText.getText().toString());
                    String bankname = listViewItem.getText();
                    Bank bank = new Bank(bankname, account, parentid);
                    Call<Bank> call = bankInterface.addBank(bank);
                    call.enqueue(new Callback<Bank>() {
                        @Override
                        public void onResponse(Call<Bank> call, Response<Bank> response) {
                            System.out.println("onresponse");
                        }

                        @Override
                        public void onFailure(Call<Bank> call, Throwable t) {
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
    public void addItem(Drawable icon, String text, int acc,String parentid) {
        Bank item = new Bank(text,acc,parentid);

        item.setIcon(icon);
        item.setText(text);

        listViewItemList.add(item);
    }
}
