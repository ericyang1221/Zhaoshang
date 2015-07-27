package com.rugao.zhaoshang;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rugao.zhaoshang.beans.Contact;
import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.ValueBean;

import java.util.List;

public class ContactDetailFragment extends BaseFragment {
	private Project p;
	private int index = -1;
	private Contact c;
	private EditText name;
	private TextView role;
	private EditText mobile;
	private EditText phone;
	private EditText companyName;
	private ChooseFragment cf;
	private String roleTxt = "";
	private String nameText = "";
	private String mobileText = "";
	private String phoneText = "";
	private String companyNameText = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.contact_detail_layout,
				container, false);
		name = (EditText) layout.findViewById(R.id.contact_name);
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				nameText = name.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		role = (TextView) layout.findViewById(R.id.contact_role);
		role.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectTitle();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new ChooseFragment.OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						roleTxt = data.get(position).getValue();
					}
				});
				go(cf);
				save();
			}
		});
		mobile = (EditText) layout.findViewById(R.id.contact_telephone);
		mobile.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mobileText = mobile.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		phone = (EditText) layout.findViewById(R.id.contact_phone);
		phone.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				phoneText = phone.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		companyName = (EditText) layout.findViewById(R.id.contact_memo);
		companyName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				companyNameText = companyName.getText().toString();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		layout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StringBuffer sb = new StringBuffer();
				if(nameText.length() < 1){
					sb.append("姓名 ");
				}
				if(roleTxt.length() < 1){
					sb.append("职位 ");
				}
				if(mobileText.length() < 1){
					sb.append("手机 ");
				}
				if(sb.length() > 0){
					sb.append("为必填项目");
					Toast.makeText(getBaseActivity(),sb.toString(),Toast.LENGTH_LONG).show();
				}else{
					goBack();
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
			c = p.getContacts().get(index);
			name.setText(c.getName());
			role.setText(c.getRole());
			mobile.setText(c.getTelephone());
			phone.setText(c.getPhone());
			companyName.setText(c.getMemo());
		} else {
			name.setText(nameText);
			role.setText(roleTxt);
			mobile.setText(mobileText);
			phone.setText(phoneText);
			companyName.setText(companyNameText);
		}
	}

	public void setProject(Project p) {
		this.p = p;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private void save(){
		nameText = name.getText().toString();
		mobileText = mobile.getText().toString();
		phoneText = phone.getText().toString();
		companyNameText = companyName.getText().toString();
	}
}
