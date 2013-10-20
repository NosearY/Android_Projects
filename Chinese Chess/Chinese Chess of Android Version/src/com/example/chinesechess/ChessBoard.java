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
	private TutorialThread thread;//ˢ֡���߳�
	private float size;
	MainActivity activity;
	Bitmap chessboard;//����
	Bitmap background;//����ͼƬ
	Bitmap sel;//ѡ��
	Paint paint;//����

	int startI, startJ;//��¼��ǰ���ӵĿ�ʼλ��
	int endI, endJ;//��¼��ǰ���ӵ�Ŀ��λ��
	Bitmap[] imageGroup = new Bitmap[5];//���ӵ�ͼƬ����
	Bitmap[] blackGroup = new Bitmap[7];//���ӵ�ͼƬ����
	Bitmap[] redGroup = new Bitmap[7];//���ӵ�ͼƬ����
	Bitmap[] buttonGroup = new Bitmap[3];//������ťͼƬ����
	Bitmap[] buttonGroup2 = new Bitmap[3];//���°�ť����
	Bitmap[] infoGroup = new Bitmap[2];//��ʾ��Ϣ
	int[][] chesspoint = new int[10][9];
	int boardWidth;
	int boardHeight;
	int width;
	int height;
	private int chessSize;
	private int topSize;
	boolean focus = false;//��ǰ�Ƿ���ѡ�е�����
	boolean isRedSide = true;
	boolean buttonEnable = true;
	public static boolean canPlayBack = false;
	boolean[] isPress={true, true, true};
	boolean isWin = false;
	int selectqizi = 0; //��Ȼѡ�е�����
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
		this.thread = new TutorialThread(getHolder(), this);//��ʼ��ˢ֡�߳�
		this.isRedSide = isRedSide;
		init();//��ʼ��������Դ
		chessrule = new ChessRule();

	}
	public void init(){//��ʼ������ 
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
		paint = new Paint();//��ʼ������
		
		chessboard = CreatMatrixBitmap(R.drawable.main, width, height);//����ͼƬ
		
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
		chessboard = CreatMatrixBitmap(R.drawable.main, width, height);//����ͼƬ
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
	public void onDraw(Canvas canvas){//�Լ�д�Ļ��Ʒ���
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(background, 0,0, null);//�屳��
		canvas.drawBitmap(imageGroup[0], 30, 0, null);//��������
		canvas.drawBitmap(chessboard, 0, topSize, null);//��������	
		if(2*chessSize%55==0){
			distance = 2*chessSize/55+chessSize;
		}else{
			distance = 2*chessSize/55+1+chessSize;
			if(chessSize>55){
				distance++;
			}
		}
		for(int i=0; i<chesspoint.length; i++){
			for(int j=0; j<chesspoint[i].length; j++){//��������
				if(chesspoint[i][j] != 0){				
					if(chesspoint[i][j] == 1){//Ϊ��˧ʱ
						canvas.drawBitmap(blackGroup[0], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 2){//Ϊ�ڳ�ʱ
						canvas.drawBitmap(blackGroup[1], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 3){//Ϊ����ʱ
						canvas.drawBitmap(blackGroup[2], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 4){//Ϊ����ʱ
						canvas.drawBitmap(blackGroup[3], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 5){//Ϊ��ʿʱ
						canvas.drawBitmap(blackGroup[4], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 6){//Ϊ����ʱ
						canvas.drawBitmap(blackGroup[5], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 7){//Ϊ�ڱ�ʱ
						canvas.drawBitmap(blackGroup[6], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 8){//Ϊ�콫ʱ
						canvas.drawBitmap(redGroup[0], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 9){//Ϊ�쳵ʱ
						canvas.drawBitmap(redGroup[1], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 10){//Ϊ����ʱ
						canvas.drawBitmap(redGroup[2], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 11){//Ϊ��hʱ
						canvas.drawBitmap(redGroup[3], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 12){//Ϊ����ʱ
						canvas.drawBitmap(redGroup[4], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 13){//Ϊ����ʱ
						canvas.drawBitmap(redGroup[5], (25+j*distance)*size, topSize+(25+i*distance)*size, paint);
					}
					else if(chesspoint[i][j] == 14){//Ϊ����ʱ
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
		if(event.getAction() == MotionEvent.ACTION_DOWN){//ֻȡ��갴�µ��¼�
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
							endJ = j;//����õ�
							boolean canMove = chessrule.canMove(chesspoint, startI, startJ, endI, endJ);
							if(canMove){
								eatenChess = chesspoint[i][j];
								chesspoint[endI][endJ] = chesspoint[startI][startJ];//�ƶ�����
								chesspoint[startI][startJ] = 0;//��ԭ�������
								if(eatenChess==1){
									Log.i("iswin","isRedSide: "+isRedSide);
									activity.myHandler.sendEmptyMessage(1);
								}
								buttonEnable = true;
								chess = new Chess(chesspoint[endI][endJ], eatenChess, startI, startJ, endI, endJ);
								startI = -1;
								startJ = -1;
								endI = -1;
								endJ = -1;//��ԭ�����
								focus = false;//��ǵ�ǰû��ѡ������
								isRedSide = false;
							}
						}else if(((chesspoint[i][j]>7&&chesspoint[i][j]<15)||chesspoint[i][j]==0)&&!isRedSide){
							if(chesspoint[i][j]!=0)
								selPosition = pos;
							Log.i("come on3","isRedSide: "+isRedSide);
							endI = i;
							endJ = j;//����õ�
							boolean canMove = chessrule.canMove(chesspoint, startI, startJ, endI, endJ);
							if(canMove){
								eatenChess = chesspoint[i][j];
								chesspoint[endI][endJ] = chesspoint[startI][startJ];//�ƶ�����
								chesspoint[startI][startJ] = 0;//��ԭ�������
								if(eatenChess==8){
									Log.i("iswin","isRedSide: "+isRedSide);
									activity.myHandler.sendEmptyMessage(2);
								}
								buttonEnable = true;
								chess = new Chess(chesspoint[endI][endJ], eatenChess, startI, startJ, endI, endJ);
								startI = -1;
								startJ = -1;
								endI = -1;
								endJ = -1;//��ԭ�����
								focus = false;//��ǵ�ǰû��ѡ������
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
	public int[] getPos(MotionEvent e){//�����껻��������ά��
		int[] back = new int[2];
		double mx = e.getX()/size;//�õ����λ�õ�x����
		double my = (e.getY()-topSize)/size;//�õ����λ�õ�y����
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
		return back;//���������鷵��
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
	        this.thread.start();//����ˢ֡�߳�
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
        boolean retry = true;
        thread.setFlag(false);//ֹͣˢ֡�߳�
     
        while (retry) {
            try {
                thread.join();
            
                retry = false;//����ѭ����־λΪfalse
            } 
            catch (InterruptedException e) {//���ϵ�ѭ����ֱ���ȴ����߳̽���
            }
        }
	}
	
	class TutorialThread extends Thread{//ˢ֡�߳�
		private int span = 300;//˯�ߵĺ����� 
		private SurfaceHolder surfaceHolder;//SurfaceHolder������
		private ChessBoard gameView;//gameView������
		private boolean flag = false;//ѭ����־λ
        public TutorialThread(SurfaceHolder surfaceHolder, ChessBoard gameView) {//������
            this.surfaceHolder = surfaceHolder;//�õ�SurfaceHolder����
            this.gameView = gameView;//�õ�GameView������
        }
        public void setFlag(boolean flag) {//����ѭ�����
        	this.flag = flag;
        }
		public void run() {//��д�ķ���
			Canvas c;//����
            while (this.flag) {//ѭ������
                c = null;
                try {
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {
                    	gameView.onDraw(c);//���û��Ʒ���
                    }
                } finally {//��finally��֤�������һ����ִ��
                    if (c != null) {
                    	//������Ļ��ʾ����
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);//˯��span����
                }catch(Exception e){//�����쳣��Ϣ
                	e.printStackTrace();//��ӡ�쳣��ջ��Ϣ
                }
            }
		}
	}
}
