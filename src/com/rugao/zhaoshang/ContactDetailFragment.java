package com.rugao.zhaoshang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rugao.zhaoshang.beans.Contact;
import com.rugao.zhaoshang.beans.Project;

public class ContactDetailFragment extends BaseFragment {
	private Project p;
	private int index = -1;
	private Contact c;
	private EditText name;
	private EditText role;
	private EditText mobile;
	private EditText phone;
	private EditText companyName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.contact_detail_layout,
				container, false);
		name = (EditText) layout.findViewById(R.id.contact_name);
		role = (EditText) layout.findViewById(R.id.contact_role);
		mobile = (EditText) layout.findViewById(R.id.contact_telephone);
		phone = (EditText) layout.findViewById(R.id.contact_phone);
		companyName = (EditText) layout.findViewById(R.id.contact_memo);
		layout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
				if (name.getText().length() > 0) {
					if (index < 0) {
						c = new Contact();
						p.getContacts().add(c);
					}
					c.setName(name.getText().toString());
					c.setRole(role.getText().toString());
					c.setTelephone(mobile.getText().toString());
					c.setPhone(phone.getText().toString());
					c.setMemo(companyName.getText().toString());
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
			c = p.getContacts().get(index);
			name.setText(c.getName());
			role.setText(c.getRole());
			mobile.setText(c.getTelephone());
			phone.setText(c.getPhone());
			companyName.setText(c.getMemo());
		} else {
			name.setText("");
			role.setText("");
			mobile.setText("");
			phone.setText("");
			companyName.setText("");
		}
	}

	public void setProject(Project p) {
		this.p = p;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
