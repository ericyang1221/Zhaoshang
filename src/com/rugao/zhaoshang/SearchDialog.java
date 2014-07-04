package com.rugao.zhaoshang;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchDialog extends Dialog {

	private EditText searchValue;
	private OnSearchListener onSearchListener;

	public SearchDialog(Context context) {
		super(context);
	}

	public SearchDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.search_dialog);
		searchValue = (EditText) findViewById(R.id.search_value);
		findViewById(R.id.search_confirm).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (onSearchListener != null) {
							onSearchListener.onSearchConfirm(searchValue
									.getText().toString());
						}
						dismiss();
					}
				});
		findViewById(R.id.search_cancel).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
	}

	public void setOnSearchListener(OnSearchListener onSearchListener) {
		this.onSearchListener = onSearchListener;
	}

	interface OnSearchListener {
		void onSearchConfirm(String searchValue);
	}
}
