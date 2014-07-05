package com.rugao.zhaoshang;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewNoticeDialog extends Dialog {
	private TextView contentTv;
	private String content;

	public NewNoticeDialog(Context context) {
		super(context);
	}

	public NewNoticeDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.newnotice_dialog);
		contentTv = (TextView) findViewById(R.id.new_notice_content);
		if (content != null && content.length() > 0) {
			contentTv.setText(content);
		}
		findViewById(R.id.new_notice_close).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
	}

	public void setContent(String content) {
		this.content = content;
	}
}
