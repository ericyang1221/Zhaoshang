package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rugao.zhaoshang.asynctask.ProjectTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.ProjectBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;
import com.rugao.zhaoshang.views.XListView;
import com.rugao.zhaoshang.views.XListView.IXListViewListener;

public class ProjectFragment extends BaseFragment implements DataView,
		IXListViewListener {
	private final int PAGE_SIZE = 20;
	private int pageIndex = 0;
	private UserBean ub;
	private LayoutInflater mInflater;
	private ProjectListAdapter pa;
	private XListView lv;
	private TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mInflater = inflater;
		ub = getMyApplication().getUserBean();
		View projectLayout = inflater.inflate(R.layout.project_layout,
				container, false);
		lv = (XListView) projectLayout.findViewById(R.id.project_listview);
		tv = (TextView) projectLayout.findViewById(R.id.project_msg);
		pa = new ProjectListAdapter();
		lv.setDividerHeight(0);
		lv.setPullLoadEnable(true);
		lv.setAdapter(pa);
		lv.setXListViewListener(this);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				ProjectDetailFragment projectDetailFragment = new ProjectDetailFragment();
				projectDetailFragment.setProjectBean(((ViewHolder) v.getTag()).project);
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.replace(R.id.content, projectDetailFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});

		projectLayout.findViewById(R.id.tr).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						ProjectAddFragment projectAddFragment = new ProjectAddFragment();
						projectAddFragment.setProjectBean(new Project());
						FragmentTransaction transaction = getFragmentManager()
								.beginTransaction();
						transaction.replace(R.id.content, projectAddFragment);
						transaction.addToBackStack(null);
						transaction.commit();
					}
				});
		return projectLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		onRefresh();
	}

	class ProjectListAdapter extends BaseAdapter {
		List<Project> pList;

		public ProjectListAdapter() {
			this.pList = new ArrayList<Project>();
		}

		public void update(List<Project> pList) {
			this.pList = pList;
			this.notifyDataSetChanged();
		}

		public void add(List<Project> pList) {
			this.pList.addAll(pList);
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			if (pList != null)
				return pList.size();
			else
				return 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			Project p = pList.get(position);
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.project_listview_item,
						null);
				holder.title = (TextView) convertView
						.findViewById(R.id.project_listview_item_title);
				holder.respeople = (TextView) convertView
						.findViewById(R.id.project_listview_item_respeople);
				holder.projectLevel = (TextView) convertView
						.findViewById(R.id.project_listview_item_projectlevel);
				holder.releaseTime = (TextView) convertView
						.findViewById(R.id.project_listview_item_releasetime);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.title.setText(p.getProjectName());
			holder.respeople.setText(p.getResponsibleIdDisplay());
			holder.projectLevel.setText(p.getStageIdDisplay());
			holder.releaseTime.setText(p.getPlanningDate());
			holder.project = p;

			return convertView;
		}

	}

	class ViewHolder {
		public TextView title;
		public TextView respeople;
		public TextView projectLevel;
		public TextView releaseTime;
		public Project project;
	}

	@Override
	public void setData(DataBean db) {
		if (db != null) {
			ProjectBean pb = (ProjectBean) db;
			if (pb.getResult()) {
				List<Project> pList = pb.getProjects();
				if (pageIndex == 0) {
					pa.update(pList);
				} else {
					pa.add(pList);
				}
				if (pList.size() < PAGE_SIZE) {
					lv.setPullLoadEnable(false);
				} else {
					pageIndex++;
				}
				lv.setVisibility(View.VISIBLE);
				tv.setVisibility(View.GONE);
			} else {
				lv.setVisibility(View.GONE);
				tv.setVisibility(View.VISIBLE);
				this.getBaseActivity().showToast(pb.getResultMsg());
			}
		} else {
			getBaseActivity().showDataBeanNullToast();
		}
		onLoad();
	}

	@Override
	public void onRefresh() {
		pageIndex = 0;
		lv.setPullLoadEnable(true);
		new ProjectTask(this.getActivity()).execute(
				this,
				URLGenerater.makeUrl(Constants.API_GET_PROJECTS, new String[] {
						String.valueOf(pageIndex), String.valueOf(PAGE_SIZE),
						String.valueOf(ub.getUserId()), ub.getMemo() }));
	}

	@Override
	public void onLoadMore() {
		new ProjectTask(this.getActivity()).execute(
				this,
				URLGenerater.makeUrl(Constants.API_GET_PROJECTS, new String[] {
						String.valueOf(pageIndex), String.valueOf(PAGE_SIZE),
						String.valueOf(ub.getUserId()), ub.getMemo() }));
	}

	private void onLoad() {
		lv.stopRefresh();
		lv.stopLoadMore();
		lv.setRefreshTime(getActivity().getString(R.string.just_now));
	}
}
