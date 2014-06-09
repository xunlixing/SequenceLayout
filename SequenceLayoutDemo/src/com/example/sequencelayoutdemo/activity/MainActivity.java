package com.example.sequencelayoutdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.sequencelayoutdemo.R;
import com.oliver.keywordgroupview.view.SequenceLayout;

public class MainActivity extends Activity {
	private SequenceLayout mKeywordListView;
	private String[] as = { "幼儿园", "北京希望小", "天机", "监德问交", "在onCreat", "监听者",
			"德问，编程领域交问答。听者", "北京希望小", "天机", "监德问交", "在onCreat", "监听者",
			"德问，编程领域交问答。听者", "北京希望小", "天机", "监德问交", "在onCreat", "监听者",
			"德问，编程领域交问答。听者" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mKeywordListView = (SequenceLayout) findViewById(R.id.sequenceLayout);
		for (int i = 0; i < as.length; i++) {
			mKeywordListView.addView(createChildView(as[i]));
			;
		}

	}

	private View createChildView(final String text) {
		RelativeLayout chlid = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.button_view, null);
		Button button = (Button) chlid.findViewById(R.id.btn_show);
		button.setText(text);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ToastMsg.show(getApplicationContext(), text);

			}
		});
		return chlid;
	}
}
