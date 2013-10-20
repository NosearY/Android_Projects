package com.example.chinesechess;

public class ChessRule {
	boolean isRedGo = false;//是不是红方走棋
	public boolean canMove(int[][] qizi, int fromY, int fromX, int toY, int toX){
		int i = 0;
		int j = 0;
		int moveChessID;//起始位置是什么棋子
		int targetID;//目的地是什么棋子或空地
		if(toX<0){//当左边出界时
			return false;
		}
		if(toX>8){//当右边出界时
			return false;
		}
		if(toY<0){//当上边出界时
			return false;
		}
		if(toY>9){//当下边出界时
			return false;
		}
		if(fromX==toX && fromY==toY){//目的地与出发点相同，
			return false;
		}
		moveChessID = qizi[fromY][fromX];//得到起始棋子
		targetID = qizi[toY][toX];//得带终点棋子
		if(isSameSide(moveChessID,targetID)){//如果是同一阵营的
			return false;
		}
		switch(moveChessID){
			case 1://黑帅
				if(toY>2||toX<3||toX>5){//出了九宫格
					return false;
				}
				if((Math.abs(fromY-toY)+Math.abs(toX-fromX))>1){//只能走一步
					return false;
				}
				break;
			case 5://黑士
				if(toY>2||toX<3||toX>5){//出了九宫格
					return false;
				}
				if(Math.abs(fromY-toY) != 1 || Math.abs(toX-fromX) != 1){//走斜线
					return false;
				}
				break;
			case 6://黑象
				if(toY>4){//不能过河
					return false;
				}
				if(Math.abs(fromX-toX) != 2 || Math.abs(fromY-toY) != 2){//相走“田”字
					return false;
				}
				if(qizi[(fromY+toY)/2][(fromX+toX)/2] != 0){
					return false;//相眼处有棋子
				}
				break;
			case 7://黑兵
				if(toY < fromY){//不能回头
					return false;
				}
				if(fromY<5 && fromY == toY){//过河前只能直走
					return false;
				}
				if(toY - fromY + Math.abs(toX-fromX) > 1){//只能走一步，并且是直线 
					return false;
				}
				break;
			case 8://红将
				if(toY<7||toX>5||toX<3){//出了九宫格
					return false;
				}
				if((Math.abs(fromY-toY)+Math.abs(toX-fromX))>1){//只能走一步
					return false;
				}
				break;
			case 2://黑车
			case 9://红车
				if(fromY != toY && fromX != toX){//只能走直线
					return false;
				}
				if(fromY == toY){//走横线
					if(fromX < toX){//向右走
						for(i = fromX + 1; i < toX; i++){//循环
							if(qizi[fromY][i] != 0){
								return false;//返回false
							}
						}
					}
					else{//向左走
						for(i = toX + 1; i < fromX; i++){//循环
							if(qizi[fromY][i] != 0){
								return false;//返回false
							}
						}
					}
				}
				else{//走的是竖线
					if(fromY < toY){//向右走
						for(j = fromY + 1; j < toY; j++){
							if(qizi[j][fromX] != 0)
							return false;//返回false							
						}
					}
					else{//想左走
						for(j= toY + 1; j < fromY; j++){
							if(qizi[j][fromX] != 0)
							return false;//返回false							
						}
					}
				}
				break;
			case 10://红马 
			case 3://黑马
				if(!((Math.abs(toX-fromX)==1 && Math.abs(toY-fromY)==2)
						|| (Math.abs(toX-fromX)==2 && Math.abs(toY-fromY)==1))){
					return false;//马走的不是日字时
				}
				if(toX-fromX==2){//向右走
					i=fromX+1;//移动
					j=fromY;
				}
				else if(fromX-toX==2){//向左走
					i=fromX-1;//移动
					j=fromY;
				}
				else if(toY-fromY==2){//向下走
					i=fromX;//移动
					j=fromY+1;
				}
				else if(fromY-toY==2){//向上走
					i=fromX;//移动
					j=fromY-1;
				}
				if(qizi[j][i] != 0)
					return false;//绊马腿
				break;
			case 11://红砲
			case 4://黑炮
				if(fromY!=toY && fromX!=toX){//炮走直线
					return false;//返回false
				}
				if(qizi[toY][toX] == 0){//不吃子时
					if(fromY == toY){//横线
						if(fromX < toX){//想右走
							for(i = fromX + 1; i < toX; i++){
								if(qizi[fromY][i] != 0){
									return false;//返回false
								}
							}
						}
						else{//向走走
							for(i = toX + 1; i < fromX; i++){
								if(qizi[fromY][i]!=0){
									return false;//返回false
								}
							}
						}
					}
					else{//竖线
						if(fromY < toY){//向下走
							for(j = fromY + 1; j < toY; j++){
								if(qizi[j][fromX] != 0){
									return false;//返回false
								}
							}
						}
						else{//向上走
							for(j = toY + 1; j < fromY; j++){
								if(qizi[j][fromX] != 0){
									return false;//返回false
								}
							}
						}
					}
				}
				else{//吃子时
					int count=0;
					if(fromY == toY){//走的是横线
						if(fromX < toX){//向右走
							for(i=fromX+1;i<toX;i++){
								if(qizi[fromY][i]!=0){
									count++;
								}
							}
							if(count != 1){
								return false;//返回false
							}
						}
						else{//向左走
							for(i=toX+1;i<fromX;i++){
								if(qizi[fromY][i] != 0){
									count++;
								}
							}
							if(count!=1){
								return false;//返回false
							}
						}
					}
					else{//走的是竖线
						if(fromY<toY){//向下走
							for(j=fromY+1;j<toY;j++){
								if(qizi[j][fromX]!=0){
									count++;//返回false
								}
							}	
							if(count!=1){
								return false;//返回false
							}
						}
						else{//向上走
							for(j=toY+1;j<fromY;j++){
								if(qizi[j][fromX] != 0){
									count++;//返回false
								}
							}
							if(count!=1){
								return false;//返回false
							}
						}
					}
				}
				break;
			case 12://红仕
				if(toY<7||toX>5||toX<3){//出了九宫格
					return false;
				}
				if(Math.abs(fromY-toY) != 1 || Math.abs(toX-fromX) != 1){//走斜线
					return false;
				}
				break;
			case 13://红相
				if(toY<5){//不能过河
					return false;//返回false
				}
				if(Math.abs(fromX-toX) != 2 || Math.abs(fromY-toY) != 2){//相走“田”字
					return false;//返回false
				}
				if(qizi[(fromY+toY)/2][(fromX+toX)/2] != 0){
					return false;//相眼处有棋子
				}
				break;
			case 14://红卒
				if(toY > fromY){//不能回头
					return false;
				}
				if(fromY > 4 && fromY == toY){
					return false;//不让走
				}
				if(fromY - toY + Math.abs(toX - fromX) > 1){//只能走一步，并且是直线 
					return false;//返回false不让走
				}
				break;
			default:
				return false;
		}
		return true;
	}
	public boolean isSameSide(int moveChessID, int targetID){//判断两个子是否为同一阵营
		if(targetID == 0){// 当目标地位空地时
			return false;
		}
		if(moveChessID>7&&targetID>7){//当都为红色棋子时
			return true;
		}
		else if(moveChessID<=7&&targetID<=7){//都为黑色棋子时
			return true;
		}
		else{//其他情况
			return false;
		}
	}
}
