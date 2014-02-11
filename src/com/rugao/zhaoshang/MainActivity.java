package com.rugao.zhaoshang;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener {

	private MessageFragment messageFragment;
	private ProjectFragment projectFragment;
	private ActivityFragment activityFragment;
	private ToolFragment toolFragment;

	private ImageView tab1;
	private ImageView tab2;
	private ImageView tab3;
	private ImageView tab4;

	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initViews();
		fragmentManager = this.getSupportFragmentManager();
		setTabSelection(0);
	}

	private void initViews() {
		tab1 = (ImageView) findViewById(R.id.tab1);
		tab2 = (ImageView) findViewById(R.id.tab2);
		tab3 = (ImageView) findViewById(R.id.tab3);
		tab4 = (ImageView) findViewById(R.id.tab4);
		tv1 = (TextView) findViewById(R.id.tab1_txt);
		tv2 = (TextView) findViewById(R.id.tab2_txt);
		tv3 = (TextView) findViewById(R.id.tab3_txt);
		tv4 = (TextView) findViewById(R.id.tab4_txt);
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
			tab1.setImageResource(R.drawable.tabbaritem_1_selected);
			tv1.setTextColor(Color.rgb(41, 158, 241));
			// if (projectFragment == null) {
			projectFragment = new ProjectFragment();
			// transaction.add(R.id.content, projectFragment);
			// } else {
			// transaction.show(projectFragment);
			// }
			transaction.replace(R.id.content, projectFragment);
			break;
		case 1:
			tab2.setImageResource(R.drawable.tabbaritem_2_selected);
			tv2.setTextColor(Color.rgb(41, 158, 241));
			// if (activityFragment == null) {
			activityFragment = new ActivityFragment();
			// transaction.add(R.id.content, activityFragment);
			// } else {
			// transaction.show(activityFragment);
			// }
			transaction.replace(R.id.content, activityFragment);
			break;
		case 2:
			tab3.setImageResource(R.drawable.tabbaritem_3_selected);
			tv3.setTextColor(Color.rgb(41, 158, 241));
			// if (messageFragment == null) {
			messageFragment = new MessageFragment();
			// transaction.add(R.id.content, messageFragment);
			// } else {
			// transaction.show(messageFragment);
			// }
			transaction.replace(R.id.content, messageFragment);
			break;
		case 3:
		default:
			tab4.setImageResource(R.drawable.tabbaritem_4_selected);
			tv4.setTextColor(Color.rgb(41, 158, 241));
			// if (toolFragment == null) {
			toolFragment = new ToolFragment();
			// transaction.add(R.id.content, toolFragment);
			// } else {
			// transaction.show(toolFragment);
			// }
			transaction.replace(R.id.content, toolFragment);
			break;
		}
		transaction.commit();
		clearStack();
	}

	private void clearSelection() {
		tab1.setImageResource(R.drawable.tabbaritem_1);
		tab2.setImageResource(R.drawable.tabbaritem_2);
		tab3.setImageResource(R.drawable.tabbaritem_3);
		tab4.setImageResource(R.drawable.tabbaritem_4);
		tv1.setTextColor(Color.WHITE);
		tv2.setTextColor(Color.WHITE);
		tv3.setTextColor(Color.WHITE);
		tv4.setTextColor(Color.WHITE);
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (activityFragment != null) {
			transaction.hide(activityFragment);
		}
		if (projectFragment != null) {
			transaction.hide(projectFragment);
		}
		if (toolFragment != null) {
			transaction.hide(toolFragment);
		}
	}

	private void clearStack() {
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
}
