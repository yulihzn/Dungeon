package com.dungeon.activity;

import java.util.ArrayList;
import java.util.List;

import com.banditcat.dungeon.R;
import com.dungeon.adapter.GameGridViewAdapter;
import com.dungeon.logic.GameMap;
import com.dungeon.model.GameGridModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GameActivity extends Activity {
	private GridView gv_content;
	private GameGridViewAdapter adapter;
	private List<GameGridModel> datas = new ArrayList<GameGridModel>();
	private static final int STATUS_FAILED = 100; 
	private static final int STATUS_SUCCESS = 101; 
	private static final int STATUS_ING = 102; 
	private static final int COLUMN = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initView();
		loadData();
	}
	private void loadData() {
		datas.clear();
		datas = new GameMap().getList();
		adapter.setData(datas);
		adapter.notifyDataSetChanged();
	}
	private void initView() {
		adapter = new GameGridViewAdapter(this);
		adapter.setData(datas);
		gv_content = (GridView) findViewById(R.id.gridView_content);
		gv_content.setAdapter(adapter);
		gv_content.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if(isBlockCanClick(position)){
					datas.get(position).setActive(true);
					int status = checkStatus(position);
					switch (status) {
					case STATUS_ING:
						break;
					case STATUS_FAILED:
						showEndDialog(status);
						break;
					case STATUS_SUCCESS:
						showEndDialog(status);
						break;
					}
				}
				adapter.notifyDataSetChanged();
				
			}
		});
	}
	protected void showEndDialog(final int status) {
		String msg = "";
		String ok = "";
		String title = "";
		switch (status) {
		case STATUS_FAILED:
			ok = "召唤英灵";
			title = "你死了";
			msg = "您听到死神在耳边低语...";
			break;
		case STATUS_SUCCESS:
			ok = "加冕为王";
			title = "你击败了大魔王";
			msg = "这个地城属于你了，有太多想法在脑海里...";
			break;
		}
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setCancelable(false);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setPositiveButton(ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(status == STATUS_FAILED){
					loadData();
				}else if(status == STATUS_SUCCESS){
					loadData();
				}
				
			}
		});
		dialog.create().show();
		
	}
	private int checkStatus(int position) {
		int a = datas.size();
		int m = COLUMN;
		if(datas.get(position).getType() == 1){
			return STATUS_FAILED;
		}
		for (int i = 0; i < datas.size(); i++) {
			if(datas.get(i).isActive()){
				a = i;
				if(a-a%m<=0){
					return STATUS_SUCCESS;
				}
			}
		}
		return STATUS_ING;
		
	}
	private boolean isBlockCanClick(int position){
		//012,345,678
		int a = datas.size();
		int m = COLUMN;
		for (int i = 0; i < datas.size(); i++) {
			if(datas.get(i).isActive()){
				a = i;
				if(a-a%m>position&&position>=a-a%m-m){
					return true;
				}else{
					return false;
				}
			}
		}
		if(a-a%m>position&&position>=a-a%m-m){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
