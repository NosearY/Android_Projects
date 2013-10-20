package com.example.chinesechess;

public class ChessGroup{
	
	public ChessGroup(){

	}

	ChessGroup(int[] imagesID, String color, int[][] chessPoint){
		for(int i=0; i<imagesID.length; i++){
			if(color.equals("black")){
				if(imagesID[i] == R.drawable.heibing){ //five bing
					for(int j = 0; j< 5; j++){
						// their position are: (0,3),(2,3),(4,3),(6,3),(8,3)
						chessPoint[3][j*2] = 7;
					}
					
				}else if(imagesID[i] == R.drawable.heishuai){ //one jiang
					// its position is (0,4)
					chessPoint[0][4] = 1;
				}else if(imagesID[i] == R.drawable.heiju){ // two ju
					for(int j = 0; j< 2; j++){
						// their position are: (0,0) and (8,0)
						chessPoint[0][j*8] = 2;
					}
					
				}else if(imagesID[i] == R.drawable.heima){ // two ma
					for(int j = 0; j< 2; j++){
						// their position are: (1,0) and (7,0)
						chessPoint[0][6*j+1] = 3;
					}
				}else if(imagesID[i] == R.drawable.heipao){ // two pao
					for(int j = 0; j< 2; j++){
						// their position are: (1,2) and (7,2)
						chessPoint[2][6*j+1] = 4;
					}
				
				}else if(imagesID[i] == R.drawable.heishi){ // two shi
					for(int j = 0; j< 2; j++){
						// their position are: (3,0) and (5,0)
						chessPoint[0][j*2+3] = 5;
					}
				
				}else if(imagesID[i] == R.drawable.heixiang){ // two xiang
					for(int j = 0; j< 2; j++){
						// their position are: (2,0) and (6,0)
						chessPoint[0][j*4+2] = 6;
					}
					
				}else{
					System.out.println("error chess name here!");
				}
				
			}
			else if(color.equals("red")){
				if(imagesID[i] == R.drawable.hongzu){ //five bing
					for(int j = 0; j< 5; j++){
						// their position are: (0,6),(2,6),(4,6),(6,6),(8,6)
						chessPoint[6][j*2] = 14;
					}
					//index = index + 5;
				}else if(imagesID[i] == R.drawable.hongjiang){ //one jiang
					// its position is (4,9)
					chessPoint[9][4] = 8;
				}else if(imagesID[i] == R.drawable.hongju){ // two ju
					for(int j = 0; j< 2; j++){
						// their position are: (0,9) and (8,9)
						chessPoint[9][j*8] = 9;
					}
					//index = index + 2;
				}else if(imagesID[i] == R.drawable.hongma){ // two ma
					for(int j = 0; j< 2; j++){
						// their position are: (1,9) and (7,9)
						chessPoint[9][6*j+1] = 10;
					}
					//index = index + 2;
				}else if(imagesID[i] == R.drawable.hongpao){ // two pao
					for(int j = 0; j< 2; j++){
						// their position are: (1,7) and (7,7)
						chessPoint[7][6*j+1] = 11;
					}
					//index = index + 2;
				}else if(imagesID[i] == R.drawable.hongshi){ // two shi
					for(int j = 0; j< 2; j++){
						// their position are: (3,9) and (5,9)
						chessPoint[9][j*2+3] = 12;
					}
					//index = index + 2;
				}else if(imagesID[i] == R.drawable.hongxiang){ // two xiang
					for(int j = 0; j< 2; j++){
						// their position are: (2,9) and (6,9)
						chessPoint[9][j*4+2] = 13;
					}
					//index = index + 2;
				}else{
					System.out.println("error chess name here!");
				}
				
			}
		}
	}
}
