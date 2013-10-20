package com.example.chinesechess;

public class Chess {
	public static final int UNIT = 57; // Chess board unit size
	private int chessID;//表明是什么棋子
	private int chessID2;
	private int fromX;//起始的坐标
	private int fromY;
	private int toX;//目的地的坐标
	private int toY;
	int score;//值,估值时会用到
	/*
	public Chess(int ChessID, int fromX,int fromY,int toX,int toY,int score){//构造器
		this.ChessID = ChessID;//棋子的类型
		this.fromX = fromX;//棋子的起始坐标
		this.fromY = fromY;
		this.toX = toX;//棋子的目标点x坐标
		this.toY = toY;//棋子的目标点y坐标
		this.score = score;
	}*/
	
	public Chess(int ChessID, int ChessID2, int fromX,int fromY,int toX,int toY){//构造器
		this.chessID = ChessID;//棋子的类型
		this.chessID2 = ChessID2;
		this.fromX = fromX;//棋子的起始坐标
		this.fromY = fromY;
		this.toX = toX;//棋子的目标点x坐标
		this.toY = toY;//棋子的目标点y坐标
	}
	
	public int getChessID(){
		return chessID;
	}
	
	public void setChessID(int chessID){
		this.chessID = chessID;
	}
	
	public int getChessID2(){
		return chessID2;
	}
	
	public void setChessID2(int chessID2){
		this.chessID2 = chessID2;
	}
	
	public int getFromX(){
		return this.fromX;
	}
	
	public void setfromX(int fromX){
		this.fromX = fromX;
	}
	
	public int getFromY(){
		return this.fromY;
	}
	
	public void setfromY(int fromY){
		this.fromY = fromY;
	}
	
	public int getToX(){
		return this.toX;
	}
	
	public void setToX(int toX){
		this.toX = toX;
	}
	
	public int getToY(){
		return this.toY;
	}
	
	public void setToY(int toY){
		this.toY = toY;
	}
}
