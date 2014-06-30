package com.example.android10_newsxml;

import java.util.Vector;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsActivity extends ListActivity {
	
	// url을 이용하여 데이터를 가져오는 클래스 선언
	GetData getdata = new GetData();
	// 타이틀 벡터값을 GetData로부터 가져와서 저장할 객체 선언
	Vector<String> titlevec;
	// 기사의 내용을 GetData로부터 가져와서 저장할 객체 선언
	Vector<String> descvec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// GetData 클래스를 통하여 데이터를 읽어드리게 한후에 값을 가져옴
		getdata.execute(null, null, null); // 이 메소드를 호출하면 doinBackground가 호출됨
		// 데이터를 받아옴
		while(true) {
			try{
				Thread.sleep(1000);
				// 네트워크를 통하여 데이터를 모두 불러왔다면
				if(getdata.flag == true) {
					titlevec = getdata.titlevec;
					descvec = getdata.descvec;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 리스트뷰에 붙일 어뎁터 클래스 선언
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlevec);
		// 리스트뷰 객체 선언
		ListView lv = getListView();
		// 리스트뷰에 아답터 붙임
		setListAdapter(adapter);
		// 화면에 리스트뷰 보여줌
		setContentView(lv);
		// 리스트뷰에 이벤트 부착
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				String descmsg = descvec.get(arg2);
				// 화면전환하는 객체 선언
				// 인자값 ("현재액티비티(화면)", "앞으로 보여질 액티비티를 선언")
				Intent intent = new Intent().setClass(NewsActivity.this, DescActivity.class);
				// 화면을 전환할때 기사의 내용을 떠 넘겨줌
				intent.putExtra("desc", descmsg);
				// 화면 전환 메소드
				startActivity(intent);
			}
			
		});
		
	}

}