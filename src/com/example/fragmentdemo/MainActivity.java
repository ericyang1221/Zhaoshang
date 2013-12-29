package com.example.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private MessageFragment messageFragment;
	private ContactsFragment contactsFragment;
	private NewsFragment newsFragment;
	private SettingFragment settingFragment;

	private ImageView tab1;
	private ImageView tab2;
	private ImageView tab3;
	private ImageView tab4;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 初始化布局元素
		initViews();
		fragmentManager = getFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		tab1 = (ImageView) findViewById(R.id.tab1);
		tab2 = (ImageView) findViewById(R.id.tab2);
		tab3 = (ImageView) findViewById(R.id.tab3);
		tab4 = (ImageView) findViewById(R.id.tab4);
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
		tab4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab1:
			setTabSelection(0);
			break;
		case R.id.tab2:
			setTabSelection(1);
			break;
		case R.id.tab3:
			setTabSelection(2);
			break;
		case R.id.tab4:
			setTabSelection(3);
			break;
		default:
			break;
		}
	}

	private void setTabSelection(int index) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			tab1.setImageResource(R.drawable.tabbaritem_1_selected);
			if (messageFragment == null) {
				// 如果MessageFragment为空，则创建一个并添加到界面上
				messageFragment = new MessageFragment();
				transaction.add(R.id.content, messageFragment);
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(messageFragment);
			}
			break;
		case 1:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			tab2.setImageResource(R.drawable.tabbaritem_2_selected);
			if (contactsFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				contactsFragment = new ContactsFragment();
				transaction.add(R.id.content, contactsFragment);
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(contactsFragment);
			}
			break;
		case 2:
			// 当点击了动态tab时，改变控件的图片和文字颜色
			tab3.setImageResource(R.drawable.tabbaritem_3_selected);
			if (newsFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				newsFragment = new NewsFragment();
				transaction.add(R.id.content, newsFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(newsFragment);
			}
			break;
		case 3:
		default:
			// 当点击了设置tab时，改变控件的图片和文字颜色
			tab4.setImageResource(R.drawable.tabbaritem_4_selected);
			if (settingFragment == null) {
				// 如果SettingFragment为空，则创建一个并添加到界面上
				settingFragment = new SettingFragment();
				transaction.add(R.id.content, settingFragment);
			} else {
				// 如果SettingFragment不为空，则直接将它显示出来
				transaction.show(settingFragment);
			}
			break;
		}
		transaction.commit();
	}

	private void clearSelection() {
		tab1.setImageResource(R.drawable.tabbaritem_1);
		tab2.setImageResource(R.drawable.tabbaritem_2);
		tab3.setImageResource(R.drawable.tabbaritem_3);
		tab4.setImageResource(R.drawable.tabbaritem_4);
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (contactsFragment != null) {
			transaction.hide(contactsFragment);
		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}
}
