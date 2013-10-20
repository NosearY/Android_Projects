package com.example.chinesechess;

public class ChessRule {
	boolean isRedGo = false;//�ǲ��Ǻ췽����
	public boolean canMove(int[][] qizi, int fromY, int fromX, int toY, int toX){
		int i = 0;
		int j = 0;
		int moveChessID;//��ʼλ����ʲô����
		int targetID;//Ŀ�ĵ���ʲô���ӻ�յ�
		if(toX<0){//����߳���ʱ
			return false;
		}
		if(toX>8){//���ұ߳���ʱ
			return false;
		}
		if(toY<0){//���ϱ߳���ʱ
			return false;
		}
		if(toY>9){//���±߳���ʱ
			return false;
		}
		if(fromX==toX && fromY==toY){//Ŀ�ĵ����������ͬ��
			return false;
		}
		moveChessID = qizi[fromY][fromX];//�õ���ʼ����
		targetID = qizi[toY][toX];//�ô��յ�����
		if(isSameSide(moveChessID,targetID)){//�����ͬһ��Ӫ��
			return false;
		}
		switch(moveChessID){
			case 1://��˧
				if(toY>2||toX<3||toX>5){//���˾Ź���
					return false;
				}
				if((Math.abs(fromY-toY)+Math.abs(toX-fromX))>1){//ֻ����һ��
					return false;
				}
				break;
			case 5://��ʿ
				if(toY>2||toX<3||toX>5){//���˾Ź���
					return false;
				}
				if(Math.abs(fromY-toY) != 1 || Math.abs(toX-fromX) != 1){//��б��
					return false;
				}
				break;
			case 6://����
				if(toY>4){//���ܹ���
					return false;
				}
				if(Math.abs(fromX-toX) != 2 || Math.abs(fromY-toY) != 2){//���ߡ����
					return false;
				}
				if(qizi[(fromY+toY)/2][(fromX+toX)/2] != 0){
					return false;//���۴�������
				}
				break;
			case 7://�ڱ�
				if(toY < fromY){//���ܻ�ͷ
					return false;
				}
				if(fromY<5 && fromY == toY){//����ǰֻ��ֱ��
					return false;
				}
				if(toY - fromY + Math.abs(toX-fromX) > 1){//ֻ����һ����������ֱ�� 
					return false;
				}
				break;
			case 8://�콫
				if(toY<7||toX>5||toX<3){//���˾Ź���
					return false;
				}
				if((Math.abs(fromY-toY)+Math.abs(toX-fromX))>1){//ֻ����һ��
					return false;
				}
				break;
			case 2://�ڳ�
			case 9://�쳵
				if(fromY != toY && fromX != toX){//ֻ����ֱ��
					return false;
				}
				if(fromY == toY){//�ߺ���
					if(fromX < toX){//������
						for(i = fromX + 1; i < toX; i++){//ѭ��
							if(qizi[fromY][i] != 0){
								return false;//����false
							}
						}
					}
					else{//������
						for(i = toX + 1; i < fromX; i++){//ѭ��
							if(qizi[fromY][i] != 0){
								return false;//����false
							}
						}
					}
				}
				else{//�ߵ�������
					if(fromY < toY){//������
						for(j = fromY + 1; j < toY; j++){
							if(qizi[j][fromX] != 0)
							return false;//����false							
						}
					}
					else{//������
						for(j= toY + 1; j < fromY; j++){
							if(qizi[j][fromX] != 0)
							return false;//����false							
						}
					}
				}
				break;
			case 10://���� 
			case 3://����
				if(!((Math.abs(toX-fromX)==1 && Math.abs(toY-fromY)==2)
						|| (Math.abs(toX-fromX)==2 && Math.abs(toY-fromY)==1))){
					return false;//���ߵĲ�������ʱ
				}
				if(toX-fromX==2){//������
					i=fromX+1;//�ƶ�
					j=fromY;
				}
				else if(fromX-toX==2){//������
					i=fromX-1;//�ƶ�
					j=fromY;
				}
				else if(toY-fromY==2){//������
					i=fromX;//�ƶ�
					j=fromY+1;
				}
				else if(fromY-toY==2){//������
					i=fromX;//�ƶ�
					j=fromY-1;
				}
				if(qizi[j][i] != 0)
					return false;//������
				break;
			case 11://��h
			case 4://����
				if(fromY!=toY && fromX!=toX){//����ֱ��
					return false;//����false
				}
				if(qizi[toY][toX] == 0){//������ʱ
					if(fromY == toY){//����
						if(fromX < toX){//������
							for(i = fromX + 1; i < toX; i++){
								if(qizi[fromY][i] != 0){
									return false;//����false
								}
							}
						}
						else{//������
							for(i = toX + 1; i < fromX; i++){
								if(qizi[fromY][i]!=0){
									return false;//����false
								}
							}
						}
					}
					else{//����
						if(fromY < toY){//������
							for(j = fromY + 1; j < toY; j++){
								if(qizi[j][fromX] != 0){
									return false;//����false
								}
							}
						}
						else{//������
							for(j = toY + 1; j < fromY; j++){
								if(qizi[j][fromX] != 0){
									return false;//����false
								}
							}
						}
					}
				}
				else{//����ʱ
					int count=0;
					if(fromY == toY){//�ߵ��Ǻ���
						if(fromX < toX){//������
							for(i=fromX+1;i<toX;i++){
								if(qizi[fromY][i]!=0){
									count++;
								}
							}
							if(count != 1){
								return false;//����false
							}
						}
						else{//������
							for(i=toX+1;i<fromX;i++){
								if(qizi[fromY][i] != 0){
									count++;
								}
							}
							if(count!=1){
								return false;//����false
							}
						}
					}
					else{//�ߵ�������
						if(fromY<toY){//������
							for(j=fromY+1;j<toY;j++){
								if(qizi[j][fromX]!=0){
									count++;//����false
								}
							}	
							if(count!=1){
								return false;//����false
							}
						}
						else{//������
							for(j=toY+1;j<fromY;j++){
								if(qizi[j][fromX] != 0){
									count++;//����false
								}
							}
							if(count!=1){
								return false;//����false
							}
						}
					}
				}
				break;
			case 12://����
				if(toY<7||toX>5||toX<3){//���˾Ź���
					return false;
				}
				if(Math.abs(fromY-toY) != 1 || Math.abs(toX-fromX) != 1){//��б��
					return false;
				}
				break;
			case 13://����
				if(toY<5){//���ܹ���
					return false;//����false
				}
				if(Math.abs(fromX-toX) != 2 || Math.abs(fromY-toY) != 2){//���ߡ����
					return false;//����false
				}
				if(qizi[(fromY+toY)/2][(fromX+toX)/2] != 0){
					return false;//���۴�������
				}
				break;
			case 14://����
				if(toY > fromY){//���ܻ�ͷ
					return false;
				}
				if(fromY > 4 && fromY == toY){
					return false;//������
				}
				if(fromY - toY + Math.abs(toX - fromX) > 1){//ֻ����һ����������ֱ�� 
					return false;//����false������
				}
				break;
			default:
				return false;
		}
		return true;
	}
	public boolean isSameSide(int moveChessID, int targetID){//�ж��������Ƿ�Ϊͬһ��Ӫ
		if(targetID == 0){// ��Ŀ���λ�յ�ʱ
			return false;
		}
		if(moveChessID>7&&targetID>7){//����Ϊ��ɫ����ʱ
			return true;
		}
		else if(moveChessID<=7&&targetID<=7){//��Ϊ��ɫ����ʱ
			return true;
		}
		else{//�������
			return false;
		}
	}
}
