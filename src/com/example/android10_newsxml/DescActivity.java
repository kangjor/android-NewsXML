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

		// 이전 액티비티에서 넘어온 기사를 받아주기 위해서 Intent 객체 선언
		Intent intent = getIntent();
		// 인텐트 안에 담겨진 값을 받아옴
		String desc = intent.getExtras().getString("desc");
		// 기사의 내용이 많으면 한 화면에서 기사를 다 못보기에 스크롤뷰 객체를 생성
		ScrollView sc = new ScrollView(this);
		// 화면에 텍스트를 보여주기 위한 객체 선언
		TextView tv = new TextView(this);
		// 텍스트 뷰에 글씨 크기를 설정
		// 클래스에서 글자크기는 float타입으로 정해준다.
		tv.setTextSize(22.0f);
		// 텍스트뷰에 텍스트값을 붙임
		tv.setText(desc);
		// 스크롤뷰에 텍스트를 붙여서 사용
		sc.addView(tv);
		// 텍스트가 부착된 스크롤뷰를 화면에 보여주시오
		setContentView(sc);
	}
}
