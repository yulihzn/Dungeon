package com.dungeon.adapter;

import java.util.List;

import com.banditcat.dungeon.R;
import com.dungeon.model.GameGridModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameGridViewAdapter extends BaseAdapter {
	private List<GameGridModel> datas;
	private Context context;
	private ViewHolder holder;
//	private onItemClickListener onItemClickListener;

	public void setData(List<GameGridModel> datas) {
		this.datas = datas;
	}

	public GameGridViewAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		if (datas != null) {
			return datas.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_gamegrid, null);
			convertView.setTag(holder);
			holder.tv_msg = (TextView) convertView.findViewById(R.id.textView_msg);
			holder.iv_content = (ImageView) convertView.findViewById(R.id.imageView_content);
			holder.fl_gamegrid = (FrameLayout) convertView.findViewById(R.id.layout_gamegrid);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_msg.setText("");
		holder.tv_msg.setBackgroundResource(android.R.color.darker_gray);
		if(datas.get(position).isActive()&&datas.get(position).getType()==0){
			holder.tv_msg.setVisibility(View.INVISIBLE);
			holder.iv_content.setVisibility(View.INVISIBLE);  
		}else if(datas.get(position).isActive()&&datas.get(position).getType()==1){
			holder.iv_content.setVisibility(View.VISIBLE); 
			holder.tv_msg.setVisibility(View.INVISIBLE);
			holder.iv_content.setImageResource(R.anim.ci);  
			AnimationDrawable animationDrawable = (AnimationDrawable) holder.iv_content.getDrawable();  
            animationDrawable.start();
		}else{
			holder.tv_msg.setVisibility(View.VISIBLE);
		}
		return convertView;
	}

	public static class ViewHolder {
		public TextView tv_msg;
		public FrameLayout fl_gamegrid;
		public ImageView iv_content;
	}

}
