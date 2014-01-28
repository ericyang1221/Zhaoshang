package com.rugao.zhaoshang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.investor_detail_layout,
				container, false);
		name = (EditText) layout.findViewById(R.id.investor_name);
		address = (EditText) layout.findViewById(R.id.investor_address);
		status = (EditText) layout.findViewById(R.id.investor_status);
		mainProduct = (EditText) layout.findViewById(R.id.investor_mainproduct);
		productMemo = (EditText) layout.findViewById(R.id.investor_productmemo);
		layout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
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
		});

		layout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
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
