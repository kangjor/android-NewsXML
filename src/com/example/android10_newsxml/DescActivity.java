package com.example.android10_newsxml;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class DescActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// ���� ��Ƽ��Ƽ���� �Ѿ�� ��縦 �޾��ֱ� ���ؼ� Intent ��ü ����
		Intent intent = getIntent();
		// ����Ʈ �ȿ� ����� ���� �޾ƿ�
		String desc = intent.getExtras().getString("desc");
		// ����� ������ ������ �� ȭ�鿡�� ��縦 �� �����⿡ ��ũ�Ѻ� ��ü�� ����
		ScrollView sc = new ScrollView(this);
		// ȭ�鿡 �ؽ�Ʈ�� �����ֱ� ���� ��ü ����
		TextView tv = new TextView(this);
		// �ؽ�Ʈ �信 �۾� ũ�⸦ ����
		// Ŭ�������� ����ũ��� floatŸ������ �����ش�.
		tv.setTextSize(22.0f);
		// �ؽ�Ʈ�信 �ؽ�Ʈ���� ����
		tv.setText(desc);
		// ��ũ�Ѻ信 �ؽ�Ʈ�� �ٿ��� ���
		sc.addView(tv);
		// �ؽ�Ʈ�� ������ ��ũ�Ѻ並 ȭ�鿡 �����ֽÿ�
		setContentView(sc);
	}
}
