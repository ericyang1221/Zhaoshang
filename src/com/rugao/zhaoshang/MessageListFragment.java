package com.rugao.zhaoshang;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rugao.zhaoshang.asynctask.MessageListTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.MessagePiece;
import com.rugao.zhaoshang.beans.MessagePieceBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;

public class MessageListFragment extends BaseFragment implements DataView {
	private LayoutInflater mInflater;
	private ListView lv;
	private MessageListListAdapter mla;
	private int activityId;
	private EditText msgInput;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.mInflater = inflater;
		View messageLayout = inflater.inflate(R.layout.message_list_layout,
				container, false);
		lv = (ListView) messageLayout.findViewById(R.id.message_list_listview);
		msgInput = (EditText) messageLayout.findViewById(R.id.msg_input);
		mla = new MessageListListAdapter();
		lv.setDividerHeight(0);
		lv.setAdapter(mla);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
			}
		});
		messageLayout.findViewById(R.id.msg_send).setOnClickListener(
				new OnClickListener() {
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(View v) {
						final String msg = msgInput.getText().toString();
						if (msg != null && msg.length() > 0) {
							getBaseActivity().showDialog(
									BaseActivity.LOADING_DIALOG);
							new Thread(new Runnable() {
								@Override
								public void run() {
									final UserBean ub = getMyApplication()
											.getUserBean();
									String url = URLGenerater.makeUrl(
											Constants.MESSAGE_REPLY,
											new String[] {
													String.valueOf(ub
															.getUserId()),
													ub.getMemo(),
													String.valueOf(activityId),
													msg.trim() });
									try {
										JSONObject jo = getMyApplication()
												.getHttpRequestHelper()
												.sendRequestAndReturnJson(url);
										DataBean db = new DataBean(jo);
										if (db.getResult()) {
											JSONObject rd = db.getResultData();
											if (rd != null && rd.has("success")) {
												if (rd.getInt("success") == 1) {
													msgSendSuccess(ub, msg);
												} else {
													msgSendFailed();
												}
											}
										} else {
											msgSendFailed();
										}
									} catch (JSONException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}
									getBaseActivity().dismissDialogIfExist(
											BaseActivity.LOADING_DIALOG);
								}
							}).start();
						} else {
							getBaseActivity().showToast(R.string.pls_input_msg);
						}
					}
				});
		messageLayout.findViewById(R.id.tl).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						goBack();
					}
				});
		return messageLayout;
	}

	private void msgSendFailed() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getBaseActivity().showToast(R.string.msg_send_failed);
				msgInput.setText("");
			}
		});
	}

	private void msgSendSuccess(final UserBean ub, final String msg) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mla.addMessage(new MessagePiece(ub.getFullName(), msg));
				msgInput.setText("");
				goBottom();
				markReviewed();
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		MyApplication myApp = getMyApplication();
		UserBean ub = myApp.getUserBean();
		new MessageListTask(this.getActivity()).execute(
				this,
				URLGenerater.makeUrl(
						Constants.MESSAGE_GETLIST,
						new String[] { String.valueOf(ub.getUserId()),
								ub.getMemo(), String.valueOf(activityId) }));
	}

	class MessageListListAdapter extends BaseAdapter {
		private List<MessagePiece> mList;

		public void update(List<MessagePiece> mList) {
			this.mList = mList;
			this.notifyDataSetChanged();
		}

		public void addMessage(MessagePiece mp) {
			mList.add(mp);
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mList == null ? 0 : mList.size();
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
			MessagePiece m = mList.get(position);
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.message_list_msg_item,
						null);
				holder.title = (TextView) convertView.findViewById(R.id.m_up);
				holder.subTitle = (TextView) convertView
						.findViewById(R.id.m_down);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.title.setText(m.getPosterIdDisplay() + " : ");
			holder.subTitle.setText(m.getContent());
			return convertView;
		}

	}

	class ViewHolder {
		public TextView title;
		public TextView subTitle;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	@Override
	public void setData(DataBean db) {
		if (db != null && db.getObj() != null) {
			MessagePieceBean mpb = (MessagePieceBean) db.getObj();
			if (mpb.getResult()) {
				mla.update(mpb.getMessages());
				goBottom();
				markReviewed();
			} else {
				this.getBaseActivity().showToast(mpb.getResultMsg());
			}
		} else {
			getBaseActivity().showDataBeanNullToast();
		}
	}

	private void goBottom() {
		lv.post(new Runnable() {
			@Override
			public void run() {
				lv.setSelection(mla.getCount() - 1);
			}
		});
	}

	private void markReviewed() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				UserBean ub = getMyApplication().getUserBean();
				String url = URLGenerater.makeUrl(
						Constants.MESSAGE_REVIEW,
						new String[] { String.valueOf(ub.getUserId()),
								ub.getMemo(), String.valueOf(activityId) });
				try {
					getMyApplication().getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
