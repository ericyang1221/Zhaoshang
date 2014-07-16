package com.rugao.zhaoshang;

import android.widget.Toast;

import com.rugao.zhaoshang.asynctask.CreateProjectTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;

public class ProjectAddFragment extends ProjectDetailFragment {
	@Override
	protected void titleRightButtonAction() {
		UserBean ub = getMyApplication().getUserBean();
		String url = Constants.DOMAIN + Constants.PROJECT_CREATE;
		CreateProjectTask cpt = new CreateProjectTask(getActivity());
		cpt.execute(
				ProjectAddFragment.this,
				url,
				project.getPostParams(String.valueOf(ub.getUserId()),
						ub.getMemo(), true));
	}
	
	@Override
	public void setData(DataBean db) {
		if (db.getResult()) {
			Toast.makeText(getActivity(), R.string.create_success,
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(), R.string.create_fail,
					Toast.LENGTH_SHORT).show();
		}
		goBack();
		
		MainActivity activity = (MainActivity)getActivity();
		if(activity!=null){
			activity.onResume();
		}
	}
}
