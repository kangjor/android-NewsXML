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
	
	// url�� �̿��Ͽ� �����͸� �������� Ŭ���� ����
	GetData getdata = new GetData();
	// Ÿ��Ʋ ���Ͱ��� GetData�κ��� �����ͼ� ������ ��ü ����
	Vector<String> titlevec;
	// ����� ������ GetData�κ��� �����ͼ� ������ ��ü ����
	Vector<String> descvec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// GetData Ŭ������ ���Ͽ� �����͸� �о�帮�� ���Ŀ� ���� ������
		getdata.execute(null, null, null); // �� �޼ҵ带 ȣ���ϸ� doinBackground�� ȣ���
		// �����͸� �޾ƿ�
		while(true) {
			try{
				Thread.sleep(1000);
				// ��Ʈ��ũ�� ���Ͽ� �����͸� ��� �ҷ��Դٸ�
				if(getdata.flag == true) {
					titlevec = getdata.titlevec;
					descvec = getdata.descvec;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ����Ʈ�信 ���� ��� Ŭ���� ����
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titlevec);
		// ����Ʈ�� ��ü ����
		ListView lv = getListView();
		// ����Ʈ�信 �ƴ��� ����
		setListAdapter(adapter);
		// ȭ�鿡 ����Ʈ�� ������
		setContentView(lv);
		// ����Ʈ�信 �̺�Ʈ ����
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				String descmsg = descvec.get(arg2);
				// ȭ����ȯ�ϴ� ��ü ����
				// ���ڰ� ("�����Ƽ��Ƽ(ȭ��)", "������ ������ ��Ƽ��Ƽ�� ����")
				Intent intent = new Intent().setClass(NewsActivity.this, DescActivity.class);
				// ȭ���� ��ȯ�Ҷ� ����� ������ �� �Ѱ���
				intent.putExtra("desc", descmsg);
				// ȭ�� ��ȯ �޼ҵ�
				startActivity(intent);
			}
			
		});
		
	}

}