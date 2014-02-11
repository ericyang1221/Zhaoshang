package com.rugao.zhaoshang;

import android.widget.Toast;

import com.rugao.zhaoshang.asynctask.CreateProjectTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;

public class ProjectAddFragment extends ProjectDetailFragment {
	@Override
	protected void titleRightButtonAction() {
		project.setProjectName(pn.getText().toString());
		project.setCase(pc.getText().toString());
		try {
			project.setScale(Double.valueOf(pst.getText().toString()));
		} catch (Exception e) {
			project.setScale(null);
		}
		project.setLandRequire(pl.getText().toString());
		project.setBuildTime(pbt.getText().toString());
		try {
			project.setTotalAmount(Long.valueOf(pta.getText().toString()));
		} catch (Exception e) {
			project.setTotalAmount(null);
		}
		try {
			project.setTotalTax(Double.valueOf(ptt.getText().toString()));
		} catch (Exception e) {
			project.setTotalTax(null);
		}
		project.setProjectPlan(ppp.getText().toString());
		project.setCityLeader(pcl.getText().toString());
		project.setTownLeader(ptl.getText().toString());
		project.setReferrer(pr.getText().toString());

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
		getActivity().getSupportFragmentManager().popBackStack();
		System.out.println(db.getResultMsg());
	}
}
