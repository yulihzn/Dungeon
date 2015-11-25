package com.dungeon.activity;

import java.util.ArrayList;
import java.util.List;

import com.banditcat.dungeon.R;
import com.dungeon.adapter.GameGridViewAdapter;
import com.dungeon.logic.GameMap;
import com.dungeon.model.GameGridModel;

import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initView();
		loadData();
	}
	private void loadData() {
		datas = new GameMap().getList();
		adapter.setData(datas);
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
					checkStatus(position);
				}
				adapter.notifyDataSetChanged();
				
			}
		});
	}
	private void checkStatus(int position) {
		// TODO Auto-generated method stub
		
	}
	//判断该行是否active
	private boolean isRowActive(int position){
		for (int i = 0; i < datas.size(); i++) {
			if(datas.get(i).getRow() == position && datas.get(i).isActive()){
				return true;
			}
		}
		return false;
	}
	private boolean isBlockCanClick(int position){
		//012,345,678
		for (int i = 0; i < datas.size(); i++) {
			if(position == i){
				if(datas.get(i).isActive()){
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
