package com.example.chinesechess;

public class Chess {
	public static final int UNIT = 57; // Chess board unit size
	private int chessID;//������ʲô����
	private int chessID2;
	private int fromX;//��ʼ������
	private int fromY;
	private int toX;//Ŀ�ĵص�����
	private int toY;
	int score;//ֵ,��ֵʱ���õ�
	/*
	public Chess(int ChessID, int fromX,int fromY,int toX,int toY,int score){//������
		this.ChessID = ChessID;//���ӵ�����
		this.fromX = fromX;//���ӵ���ʼ����
		this.fromY = fromY;
		this.toX = toX;//���ӵ�Ŀ���x����
		this.toY = toY;//���ӵ�Ŀ���y����
		this.score = score;
	}*/
	
	public Chess(int ChessID, int ChessID2, int fromX,int fromY,int toX,int toY){//������
		this.chessID = ChessID;//���ӵ�����
		this.chessID2 = ChessID2;
		this.fromX = fromX;//���ӵ���ʼ����
		this.fromY = fromY;
		this.toX = toX;//���ӵ�Ŀ���x����
		this.toY = toY;//���ӵ�Ŀ���y����
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
