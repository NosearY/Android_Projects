package com.example.chinesechess;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	ChessBoard gameView = null;
	static int count = 0;
	Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 1){
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle("The red side win the game!");
				// Setting Dialog Message
				alertDialog.setMessage("Do you want to play again?");
				alertDialog.setNegativeButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								initGameView();//初始化欢迎界面
								Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
							}
				});
				alertDialog.setPositiveButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// Write your code here to execute after dialog
								finish();
								Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
							}
						});


				// Showing Alert Message
				alertDialog.show();
			}
			if(msg.what == 2){
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle("The black side win the game!");

				// Setting Dialog Message
				alertDialog.setMessage("Do you want to play again?");
				alertDialog.setNegativeButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								initGameView();//初始化欢迎界面
								Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
							}
				});
				alertDialog.setPositiveButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// Write your code here to execute after dialog
								finish();
								Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
							}
						});


				// Showing Alert Message
				alertDialog.show();
			}
			if(msg.what == 3){
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle("New Game Request!");

				// Setting Dialog Message
				alertDialog.setMessage("Are you sure to a new game?");
				alertDialog.setNegativeButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								initGameView();//初始化欢迎界面
								Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
							}
				});
				alertDialog.setPositiveButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// Write your code here to execute after dialog
								Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
							}
						});


				// Showing Alert Message
				alertDialog.show();
			}
			if(msg.what == 4){
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle("Play Back Request!");

				// Setting Dialog Message
				alertDialog.setMessage("Are you sure to play back?");
				alertDialog.setNegativeButton("YES",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								Log.i("I click here1!!","Shelvin.....................");
								ChessBoard.canPlayBack = true;
								Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
							}
				});
				alertDialog.setPositiveButton("NO",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// Write your code here to execute after dialog
								Log.i("He click here1!!","Louise.....................");
								ChessBoard.canPlayBack = false;
								Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
							}
						});


				// Showing Alert Message
				alertDialog.show();
			}
			
			if(msg.what == 5){
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle("Warning!");

				// Setting Dialog Message
				alertDialog.setMessage("You can not play back currently!");
				alertDialog.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
							}
				});
				// Showing Alert Message
				alertDialog.show();
			}
		}


	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.setContentView(R.layout.activity_main);
		//全屏
	
       // initChessBoardOnly();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        this.initGameView();//初始化欢迎界面
    }

    
    public void initGameView(){//初始化游戏界面
    	Display display = getWindowManager().getDefaultDisplay();
    	count += 1;
    	if(count%2==1)
    		this.setContentView(new ChessBoard(this,this, display.getWidth(), display.getHeight(), true)); //切换到游戏界面
    	else
    		this.setContentView(new ChessBoard(this,this, display.getWidth(), display.getHeight(), false)); //切换到游戏界面
    }
    

}
