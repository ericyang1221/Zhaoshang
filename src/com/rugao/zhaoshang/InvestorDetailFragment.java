package com.rugao.zhaoshang;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.rugao.zhaoshang.beans.Investor;
import com.rugao.zhaoshang.beans.Project;

public class InvestorDetailFragment extends BaseFragment {
	private Project p;
	private int index = -1;
	private Investor i;
	private EditText name;
	private EditText address;
	private EditText status;
	private EditText mainProduct;
	private EditText productMemo;
	private String nt = "";
	private String at = "";
	private String st = "";
	private String mpt = "";
	private String pmt = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.investor_detail_layout,
				container, false);
		name = (EditText) layout.findViewById(R.id.investor_name);
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				nt = name.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		address = (EditText) layout.findViewById(R.id.investor_address);
		address.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				at = address.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		status = (EditText) layout.findViewById(R.id.investor_status);
		status.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				st = status.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		mainProduct = (EditText) layout.findViewById(R.id.investor_mainproduct);
		mainProduct.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mpt = mainProduct.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		productMemo = (EditText) layout.findViewById(R.id.investor_productmemo);
		productMemo.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				pmt = productMemo.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		layout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StringBuffer sb = new StringBuffer();
				if(nt.length() < 1){
					sb.append("姓名 ");
				}
				if(at.length() < 1){
					sb.append("地址 ");
				}
				if(sb.length() > 0){
					sb.append("不能为空");
					Toast.makeText(getBaseActivity(),sb.toString(),Toast.LENGTH_LONG).show();
				}else{
					goBack();
					if (name.getText().length() > 0) {
						if (index < 0) {
							i = new Investor();
							p.getInvestors().add(i);
						}
						i.setInvestorName(name.getText().toString());
						i.setAddress(address.getText().toString());
						i.setStatus(status.getText().toString());
						i.setMainProduct(mainProduct.getText().toString());
						i.setProductMemo(productMemo.getText().toString());
					}
				}
			}
		});

		layout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goBack();
			}
		});
		return layout;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (index > -1) {
			i = p.getInvestors().get(index);
			name.setText(i.getInvestorName());
			address.setText(i.getAddress());
			status.setText(i.getStatus());
			mainProduct.setText(i.getMainProduct());
			productMemo.setText(i.getProductMemo());
		} else {
			name.setText("");
			address.setText("");
			status.setText("");
			mainProduct.setText("");
			productMemo.setText("");
		}
	}

	public void setProject(Project p) {
		this.p = p;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
