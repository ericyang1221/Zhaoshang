package com.rugao.zhaoshang;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewNoticeDialog extends Dialog {

	private TextView content;

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
		content = (TextView) findViewById(R.id.new_notice_content);
		findViewById(R.id.new_notice_close).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
	}

	public void setContent(String contentStr) {
		if (content != null) {
			content.setText(contentStr);
		}
	}
}
