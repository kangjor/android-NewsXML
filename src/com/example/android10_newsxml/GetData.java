package com.example.android10_newsxml;

import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;

// url Ŭ������ �̿��Ͽ� ������Ʈ�� ���� (news�� �������ִ� ����Ʈ)�ؼ� xml������ �о�帮�� �Ľ��ϴ� Ŭ����
public class GetData extends AsyncTask<Void, Void, Void> {
	
	// ������ Ÿ��Ʋ�� ������ �ִ� ��ü ��
	Vector<String> titlevec = new Vector<>();
	// ������ ��系���� ������ �ִ� ��ü ��
	Vector<String> descvec = new Vector<>();
	// ������Ʈ�� ������ �ּ�
	String uri = "http://rss.hankyung.com/new/news_economy.xml";
	// ������Ʈ�� ������ �����ִ� Ŭ����
	URL url;
	// XML������ ������ �ӽ÷� ������ ����
	String tagname = "", title="", desc="";
	// �������� ������ ��� �о��ȴ����� ���� ������ ����
	Boolean flag = null;
	
	// ��Ʈ��ũ(url)�� �����ؼ� xml������ �������� �޼ҵ� 
	@Override
	protected Void doInBackground(Void... params) {

		try {
			//xml������ �а� �ؼ����� �� �ִ� ��ü�� ����
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			//���ӽ����̽� ��뿩��
			factory.setNamespaceAware(true);
			//���� xml������ �о� �帮�鼭 �����͸� �������ִ� ��ü ����
			XmlPullParser xpp = factory.newPullParser();
			
			// ������Ʈ�� ����
			url = new URL(uri);
			// ����Ʈ �����Ŀ� xml ������ �о ������
			InputStream in = url.openStream();
			// ������Ʈ�κ��� �޾ƿ� xml������ �о�帮�鼭 �����͸� ������ �ִ� XmlPullParser��ü�� �Ѱ���
			xpp.setInput(in, "utf-8");
			
			// �̺�Ʈ ������ ����ϱ� ���ؼ� ���� ����
			int eventType = xpp.getEventType();
			// �ݺ����� ����Ͽ� ������ ������ �о� ���̸鼭 �����͸� �����Ͽ� ������ ���Ϳ� ����
			while(eventType != XmlPullParser.END_DOCUMENT ) {
				if(eventType == XmlPullParser.START_TAG) {
					// �±��� �̸��� �˾ƾ� �ؽ�Ʈ�� �����ϱ⿡ �±��̸��� �о ������ ����
					tagname = xpp.getName();
					
				} else if(eventType == XmlPullParser.TEXT) {
					// �±� �̸��� title�� ���ٸ� ������ title ����
					if(tagname.equals("title")) title += xpp.getText();
					// �±� �̸��� description�� ���ٸ� desc������ ����
					else if (tagname.equals("description")) desc += xpp.getText();
				} else if (eventType == XmlPullParser.END_TAG) {
					// end tag �̸��� ����
					tagname = xpp.getName();
					// end tag �̸��� item�̶�� ������ ���� title�� desc�� ���Ϳ� ����
					if(tagname.equals("item")) {
						titlevec.add(title);
						descvec.add(desc);
						// ���� �ʱ�ȭ
						title="";
						desc="";
					}
				}
				// ���� �̺�Ʈ�� �ѱ�
				eventType = xpp.next();
			}
			// ��� xml������ �о��ȴٸ�
			flag = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
