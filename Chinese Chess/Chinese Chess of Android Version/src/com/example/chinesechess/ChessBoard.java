package com.example.chinesechess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
@SuppressLint("WrongCall")
public class ChessBoard extends SurfaceView implements SurfaceHolder.Callback{
	private TutorialThread thread;//刷帧的线程
	private float size;
	MainActivity activity;
	Bitmap chessboard;//棋盘
	Bitmap background;//背景图片
	Bitmap sel;//选择
	Paint paint;//画笔

	int startI, startJ;//记录当前棋子的开始位置
	int endI, endJ;//记录当前棋子的目标位置
	Bitmap[] imageGroup = new Bitmap[5];//黑子的图片数组
	Bitmap[] blackGroup = new Bitmap[7];//黑子的图片数组
	Bitmap[] redGroup = new Bitmap[7];//红子的图片数组
	Bitmap[] buttonGroup = new Bitmap[3];//正常按钮图片数组
	Bitmap[] buttonGroup2 = new Bitmap[3];//按下按钮数组
	Bitmap[] infoGroup = new Bitmap[2];//提示信息
	int[][] chesspoint = new int[10][9];
	int boardWidth;
	int boardHeight;
	int width;
	int height;
	private int chessSize;
	private int topSize;
	boolean focus = false;//当前是否有选中的棋子
	boolean isRedSide = true;
	boolean buttonEnable = true;
	public static boolean canPlayBack = false;
	boolean[] isPress={true, true, true};
	boolean isWin = false;
	int selectqizi = 0; //当然选中的棋子
	int eatenChess = 0;
	int[] selPosition = {-1, -1};
	Chess chess;
	ChessRule chessrule;
	public ChessBoard(){
		super(null);
	}
	public ChessBoard(Context context,MainActivity activity,int screenWidth, int screenHeight, boolean isRedSide) {
		super(context);
		width = screenWidth;
		height = screenHeight;
    	Bitmap bitMap = null;
    	bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.heibing);
    	chessSize = bitMap.getWidth();
    	topSize = 100*height/854;
		this.activity = activity;
		getHolder().addCallback(this);
		this.thread = new TutorialThread(getHolder(), this);//初始化刷帧线程
		this.isRedSide = isRedSide;
		init();//初始化所需资源
		chessrule = new ChessRule();

	}
	public void init(){//初始化方法 
		for(int i=0; i<10; i++){
			for(int j=0; j<9; j++){
				chesspoint[i][j] = 0;
			}
		}
		int[] imageAdapter = {R.drawable.title, R.drawable.red_shuai, R.drawable.black_jiang};
		int[] redAdapter = {R.drawable.hongjiang, R.drawable.hongju, R.drawable.hongma, R.drawable.hongpao,
				R.drawable.hongshi, R.drawable.hongxiang, R.drawable.hongzu};
		int[] blackAdapter = {R.drawable.heishuai, R.drawable.heiju, R.drawable.heima, R.drawable.heipao,  
				R.drawable.heishi, R.drawable.heixiang, R.drawable.heibing,};
		int[] buttonAdapter = {R.drawable.new_game, R.drawable.play_back, R.drawable.draw};
		int[] buttonAdapter2 = {R.drawable.new_game2, R.drawable.play_back2, R.drawable.draw2};
		int[] infoAdapter = {R.drawable.redside, R.drawable.blackside};
		new ChessGroup(blackAdapter, "black", chesspoint);
		new ChessGroup(redAdapter, "red", chesspoint);
		paint = new Paint();//初始化画笔
		
		chessboard = CreatMatrixBitmap(R.drawable.main, width, height);//棋盘图片
		
		int k;
		for(k=0; k<imageAdapter.length; k++){
			imageGroup[k] = setChessSize(imageAdapter[k], width, height);
		}
		for(k=0; k<blackAdapter.length; k++){
			blackGroup[k] = setChessSize(blackAdapter[k], width, height);
		}
		for(k=0; k<redAdapter.length; k++){
			redGroup[k] = setChessSize(redAdapter[k], width, height);
		}
	
		sel = setChessSize(R.drawable.sel, width, height);
		for(k=0; k<buttonAdapter.length; k++){
			buttonGroup[k] = setChessSize(redAdapter[k], width, height);
		}
		for(k=0; k<buttonAdapter.length; k++){
			buttonGroup[k] = setChessSize(buttonAdapter[k], width, height);
		}
		for(k=0; k<buttonAdapter2.length; k++){
			buttonGroup2[k] = setChessSize(buttonAdapter2[k], width, height);
		}
		for(k=0; k<infoGroup.length; k++){
			infoGroup[k] = setChessSize(infoAdapter[k], width, height);
		}
		
		background = setBackground(R.drawable.background, width, height);
	}
	
	public void initChessBoardOnly(){
		chessboard = CreatMatrixBitmap(R.drawable.main, width, height);//棋盘图片
	}
    private Bitmap CreatMatrixBitmap(int resourcesID, float scr_width, float res_height) {
    	Bitmap bitMap = null;
    	bitMap = BitmapFactory.decodeResource(getResources(), resourcesID);
    	boardWidth = bitMap.getWidth();
    	boardHeight = bitMap.getHeight();
    	float scaleWidth = scr_width / (float) boardWidth;
    	float scaleHeight = res_height / (float) boardHeight;
    	Log.i("value here1", "scaleWidth:"+scaleWidth+",scaleHeight:"+scaleHeight+",src_width:"+scr_width+",src_height:"+res_height+",boardWidth:"+boardWidth+",boardHeight:"+boardHeight);
    	if(scaleWidth < scaleHeight){
        	Matrix matrix = new Matrix();
        	matrix.postScale(scaleWidth, scaleWidth);
        	size = scaleWidth;
        	Log.i("Width is large", ""+size);
        	bitMap = Bitmap.createBitmap(bitMap, 0, 0, boardWidth, boardHeight, matrix, true);
    	}else{
        	Matrix matrix = new Matrix();
        	matrix.postScale(scaleHeight, scaleHeight);
        	Log.i("Height is large", ""+size);
        	size = scaleHeight;
        	bitMap = Bitmap.createBitmap(bitMap, 0, 0, boardWidth, boardHeight, matrix, true);
    	}

    	return bitMap;
    }
    private Bitmap setChessSize(int resourcesID, float scr_width, float res_height) {
    	Bitmap bitMap = null;
    	bitMap = BitmapFactory.decodeResource(getResources(), resourcesID);
    	int bitWidth = bitMap.getWidth();
    	int bitHeight = bitMap.getHeight();
    	//chessSize = bitWidth;
    	Log.i("chess size", bitWidth+","+bitHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(size, size);
        bitMap = Bitmap.createBitmap(bitMap, 0, 0, bitWidth, bitHeight, matrix, true);

    	return bitMap;
     }
    private Bitmap setBackground(int resourcesID, float scr_width,
    	    float res_height) {
    	Bitmap bitMap = null;
    	bitMap = BitmapFactory.decodeResource(getResources(), resourcesID);
    	int bitWidth = bitMap.getWidth();
    	int bitHeight = bitMap.getHeight();
    	float scaleWidth = scr_width / (float) bitWidth;
    	float scaleHeight = res_height / (float) bitHeight;
    	Matrix matrix = new Matrix();
    	matrix.postScale(scaleWidth, scaleHeight);
    	bitMap = Bitmap.createBitmap(bitMap, 0, 0, bitWidth, bitHeight, matrix,
    		true);
    	return bitMap;
        }
    private int distance;
	public void onDraw(Canvas canvas){//自己写的绘制方法
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(background, 0,0, null);//清背景
		canvas.drawBitmap(imageGroup[0], 30, 0, null);//绘制棋盘
		canvas.drawBitmap(chessboard, 0, topSize, null);//绘制棋盘	
		if(2*chessSize%55==0){
			distance = 2*chessSize/55+chessSize;
		}else{
			distance = 2*chessSize/55+1+chessSize;
			if(chessSize>55){
				distance++;
			}
		}
		for(int i=0; i<chesspoint.length; i++){
			for(int j=0; j<chesspoint[i].length; j++){//绘制棋子
				if(chesspoint[i][j] != 0){				
					if(chesspoint[i][j] == 1){//为黑帅时
						canvas.drawBitmap(blackGroup[0], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 2){//为黑车时
						canvas.drawBitmap(blackGroup[1], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 3){//为黑马时
						canvas.drawBitmap(blackGroup[2], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 4){//为黑炮时
						canvas.drawBitmap(blackGroup[3], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 5){//为黑士时
						canvas.drawBitmap(blackGroup[4], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 6){//为黑象时
						canvas.drawBitmap(blackGroup[5], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 7){//为黑兵时
						canvas.drawBitmap(blackGroup[6], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 8){//为红将时
						canvas.drawBitmap(redGroup[0], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 9){//为红车时
						canvas.drawBitmap(redGroup[1], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 10){//为红马时
						canvas.drawBitmap(redGroup[2], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 11){//为红砲时
						canvas.drawBitmap(redGroup[3], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 12){//为红仕时
						canvas.drawBitmap(redGroup[4], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 13){//为红相时
						canvas.drawBitmap(redGroup[5], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 14){//为红卒时
						canvas.drawBitmap(redGroup[6], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
				}
			}
		}
		if(selPosition[0]!=-1 && selPosition[1]!=-1){
			canvas.drawBitmap(sel, (20+selPosition[1]*distance)*size, topSize+(20+selPosition[0]*distance)*size, paint);
		}
		
	
		if(isPress[0])
			canvas.drawBitmap(buttonGroup[0], 10, boardHeight*size+110*height/854, paint);
		else
			canvas.drawBitmap(buttonGroup2[0], 10, boardHeight*size+110*height/854, paint);
		if(isPress[1])
			canvas.drawBitmap(buttonGroup[1], 20+120*height/854*size, boardHeight*size+110*height/854, paint);
		else
			canvas.drawBitmap(buttonGroup2[1], 20+120*height/854*size, boardHeight*size+110*height/854, paint);
		if(isPress[2])
			canvas.drawBitmap(buttonGroup[2], 30+120*height/854*size*2, boardHeight*size+110*height/854, paint);
		else
			canvas.drawBitmap(buttonGroup2[2], 30+120*height/854*size*2, boardHeight*size+110*height/854, paint);
		
		if(isRedSide)
			canvas.drawBitmap(infoGroup[0], 10, boardHeight*size+115*height/854+buttonGroup[0].getHeight()*size, paint);
		else
			canvas.drawBitmap(infoGroup[1], 10, boardHeight*size+115*height/854+buttonGroup[0].getHeight()*size, paint);
		/*
		for(int k=0; k<3; k++){
			canvas.drawBitmap(buttonGroup[k], (k+1)*10+120*size*k, boardHeight*size+110, paint);
		}*/
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){//只取鼠标按下的事件
			if(event.getY()>=topSize&&event.getY()<boardHeight*size+topSize){
				selPosition[0] = -1;
				selPosition[1] = -1;
				int[] pos = getPos(event);
				Log.i("hi world",pos[0]+"  and "+pos[1]);
				int i = pos[0];
				int j = pos[1];
				if(i!=-1 && j!=-1){
					if(focus == false){
						Log.i("shelvin","chesspoint: "+chesspoint[i][j]+"  redside: "+isRedSide);
						if((chesspoint[i][j]>0&&chesspoint[i][j]<8&&!isRedSide)
								||(chesspoint[i][j]>7&&chesspoint[i][j]<15&&isRedSide)){
							Log.i("come on1","isRedSide: "+isRedSide);
							selPosition = pos;
							selectqizi = chesspoint[i][j];
							focus = true;
							startI = i;
							startJ = j;
						}
					}else{
						if(((chesspoint[i][j]>0&&chesspoint[i][j]<8)||chesspoint[i][j]==0)&&isRedSide){
							if(chesspoint[i][j]!=0)
								selPosition = pos;
							Log.i("come on2","isRedSide: "+isRedSide);
							endI = i;
							endJ = j;//保存该点
							boolean canMove = chessrule.canMove(chesspoint, startI, startJ, endI, endJ);
							if(canMove){
								eatenChess = chesspoint[i][j];
								chesspoint[endI][endJ] = chesspoint[startI][startJ];//移动棋子
								chesspoint[startI][startJ] = 0;//将原来处设空
								if(eatenChess==1){
									Log.i("iswin","isRedSide: "+isRedSide);
									activity.myHandler.sendEmptyMessage(1);
								}
								buttonEnable = true;
								chess = new Chess(chesspoint[endI][endJ], eatenChess, startI, startJ, endI, endJ);
								startI = -1;
								startJ = -1;
								endI = -1;
								endJ = -1;//还原保存点
								focus = false;//标记当前没有选中棋子
								isRedSide = false;
							}
						}else if(((chesspoint[i][j]>7&&chesspoint[i][j]<15)||chesspoint[i][j]==0)&&!isRedSide){
							if(chesspoint[i][j]!=0)
								selPosition = pos;
							Log.i("come on3","isRedSide: "+isRedSide);
							endI = i;
							endJ = j;//保存该点
							boolean canMove = chessrule.canMove(chesspoint, startI, startJ, endI, endJ);
							if(canMove){
								eatenChess = chesspoint[i][j];
								chesspoint[endI][endJ] = chesspoint[startI][startJ];//移动棋子
								chesspoint[startI][startJ] = 0;//将原来处设空
								if(eatenChess==8){
									Log.i("iswin","isRedSide: "+isRedSide);
									activity.myHandler.sendEmptyMessage(2);
								}
								buttonEnable = true;
								chess = new Chess(chesspoint[endI][endJ], eatenChess, startI, startJ, endI, endJ);
								startI = -1;
								startJ = -1;
								endI = -1;
								endJ = -1;//还原保存点
								focus = false;//标记当前没有选中棋子
								isRedSide = true;
							}
						}else if(chesspoint[i][j]>0&&chesspoint[i][j]<8&&!isRedSide){
							selPosition = pos;
							Log.i("come on4","isRedSide: "+isRedSide);
							selectqizi = chesspoint[i][j];
							focus = true;
							startI = i;
							startJ = j;
						}else if(chesspoint[i][j]>7&&chesspoint[i][j]<15&&isRedSide){
							selPosition = pos;
							Log.i("come on5","isRedSide: "+isRedSide);
							selectqizi = chesspoint[i][j];
							focus = true;
							startI = i;
							startJ = j;
						}
						
					}
				}
			}else{
				if(event.getX()>=10&&event.getX()<=buttonGroup[0].getWidth()*size+10&&event.getY()>=110*height/854+boardHeight*size&&event.getY()<=110*height/854+boardHeight*size+buttonGroup[0].getHeight()*size){
					Log.i("ohhhh my button","isRedSide: ");
					if(isPress[0])
						isPress[0] = false;
					else
						isPress[0] = true;
					activity.myHandler.sendEmptyMessage(3);
				}
				if(event.getX()>=20+buttonGroup[0].getWidth()*size&&event.getX()<=2*buttonGroup[0].getWidth()*size+20&&event.getY()>=110*height/854+boardHeight*size&&event.getY()<=110*height/854+boardHeight*size+buttonGroup[0].getHeight()*size){
					Log.i("ohhhh my button","isRedSide: ");
					if(isPress[1])
						isPress[1] = false;
					else
						isPress[1] = true;
					if(buttonEnable){
						//activity.myHandler.sendEmptyMessage(4);
						canPlayBack = true;
						if(canPlayBack){
							Log.i("why come in","seclectqizi: "+selectqizi);
							playBack();
						}
					}else{
						activity.myHandler.sendEmptyMessage(5);
					}
				
				}
				if(event.getX()>=30+2*buttonGroup[0].getWidth()*size&&event.getX()<=3*buttonGroup[0].getWidth()*size+30&&event.getY()>=110*height/854+boardHeight*size&&event.getY()<=110*height/854+boardHeight*size+buttonGroup[0].getHeight()*size){
					Log.i("ohhhh my button","isRedSide: ");
					if(isPress[2])
						isPress[2] = false;
					else
						isPress[2] = true;
				}
			}
		}
		return super.onTouchEvent(event);
	}
	
	public void playBack(){
		Log.i("what happen","isRedSide: "+canPlayBack);
		chesspoint[chess.getFromX()][chess.getFromY()] = chess.getChessID();
		chesspoint[chess.getToX()][chess.getToY()] = chess.getChessID2();
		if(isRedSide)
			isRedSide = false;
		else
			isRedSide = true;
		buttonEnable = false;
		canPlayBack = false;
	}
	public int[] getPos(MotionEvent e){//将坐标换算成数组的维数
		int[] back = new int[2];
		double mx = e.getX()/size;//得到点击位置的x坐标
		double my = (e.getY()-topSize)/size;//得到点击位置的y坐标
		int range = 15;
		int index_x = -1;
		int index_y = -1;
		int start_x = chessSize;
		int start_y = chessSize;
		if(2*chessSize%55==0){
			distance = 2*chessSize/55+chessSize;
		}else{
			distance = 2*chessSize/55+1+chessSize;
			if(chessSize>55){
				distance++;
			}
		}
		int reminder_x = (int) ((mx - start_x)%distance);
		int reminder_y = (int) ((my - start_y)%distance);
		//System.out.println("reminder x is :"+ reminder_x + "y is :"+ reminder_y);
		// cross the border case
		if(mx < (start_x-range) || mx > (start_x+distance*8+range) || my < (start_y-range) || my > (start_y+distance*9+range)){
			back[0] = -1;
			back[1] = -1;
			return back;
		}
		// only response in 10 X 10 local area
		if((reminder_x>range && reminder_x<(distance-range)) || (reminder_y>range && reminder_y<(distance-range))){
			// don't response
			;
		}else{
			if(reminder_x <= range){
				index_x = (int) ((mx - start_x)/distance);
			}else if(reminder_x >= (distance-range)){
				index_x = (int) ((mx - start_x +range)/distance);
			}
			if(reminder_y <=range){
				index_y = (int) ((my - start_y)/distance);
			}else if(reminder_y >=(distance-range)){
				index_y = (int) ((my - start_y +range)/distance);
			}
		}
		back[0] = index_y;
		back[1] = index_x;
		return back;//将坐标数组返回
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		   this.thread.setFlag(true);
	        this.thread.start();//启动刷帧线程
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
        boolean retry = true;
        thread.setFlag(false);//停止刷帧线程
     
        while (retry) {
            try {
                thread.join();
            
                retry = false;//设置循环标志位为false
            } 
            catch (InterruptedException e) {//不断地循环，直到等待的线程结束
            }
        }
	}
	
	class TutorialThread extends Thread{//刷帧线程
		private int span = 300;//睡眠的毫秒数 
		private SurfaceHolder surfaceHolder;//SurfaceHolder的引用
		private ChessBoard gameView;//gameView的引用
		private boolean flag = false;//循环标志位
        public TutorialThread(SurfaceHolder surfaceHolder, ChessBoard gameView) {//构造器
            this.surfaceHolder = surfaceHolder;//得到SurfaceHolder引用
            this.gameView = gameView;//得到GameView的引用
        }
        public void setFlag(boolean flag) {//设置循环标记
        	this.flag = flag;
        }
		public void run() {//重写的方法
			Canvas c;//画布
            while (this.flag) {//循环绘制
                c = null;
                try {
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {
                    	gameView.onDraw(c);//调用绘制方法
                    }
                } finally {//用finally保证下面代码一定被执行
                    if (c != null) {
                    	//更新屏幕显示内容
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);//睡眠span毫秒
                }catch(Exception e){//不会异常信息
                	e.printStackTrace();//打印异常堆栈信息
                }
            }
		}
	}
}
