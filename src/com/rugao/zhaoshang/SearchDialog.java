package com.rugao.zhaoshang;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SearchDialog extends Dialog {
	private final String TAG = "SearchDialog";
	private Context context;
	private EditText searchValue;
	private OnSearchListener onSearchListener;

	public SearchDialog(Context context) {
		super(context);
		this.context = context;
	}

	public SearchDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
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

		this.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				Log.d(TAG, "onDismiss");
				hideInputMethod();
			}
		});

	}

	private void hideInputMethod() {
		InputMethodManager mInputMethodManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
	}

	public void setOnSearchListener(OnSearchListener onSearchListener) {
		this.onSearchListener = onSearchListener;
	}

	interface OnSearchListener {
		void onSearchConfirm(String searchValue);
	}
}
