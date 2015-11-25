package com.dungeon.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dungeon.model.GameGridModel;

public class GameMap {
	private Random random = new Random(System.currentTimeMillis());
	private List<GameGridModel> list = new ArrayList<GameGridModel>();
	private int column=3,row=3;
	private int[][]typearray;

	public GameMap() {
		typearray = getTypeArray();
		for (int i = 0; i < column*row; i++) {
		}
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				GameGridModel gameGridModel = new GameGridModel();
				gameGridModel.setType(typearray[i][j]);
				gameGridModel.setColumn(i);
				gameGridModel.setRow(j);
				if(gameGridModel.getType() == 1){
					gameGridModel.setMsg("陷阱");
				}
				list.add(gameGridModel);
			}
		}
	}
	
	public List<GameGridModel> getList() {
		return list;
	}

	/**
	 * 0:普通1:陷阱
	 */
	private int[][] getTypeArray(){
		int[][] array = new int[column][row];
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				array[i][j] = random.nextInt(2);
			}
		}
		return array;
	}

}
