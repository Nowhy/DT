/**
 * Rule part is modified from Desktop version UI and the rest of are written 
 * by us own(Yu QU/Yuan FENG/Qiwei SUN) based on the requirement specification
 **/
package com.example.test;

import android.widget.ImageButton;

public class Rule {
ImageButton chessBoard[][];
ImageButton red[];
ImageButton black[];

//constructor
public Rule(ImageButton[][] chessBoard,ImageButton[] red, ImageButton[] black){
	this.chessBoard = chessBoard;
	this.red = red;
	this.black = black;
}

//decide which color wins the game - By YQ
public boolean isWin(ImageButton btn){
	if(btn.getId() == R.id.rshuai || btn.getId() == R.id.bshuai){
		return true;
	}
	return false;
}

//decide whether the piece is opponent or not - By YQ
public boolean isOpponent(ImageButton btn, int counter){
	//it is black
	boolean exist = false;
	for(int i = 0;i<black.length;i++){
		if(btn.equals(black[i])){
			exist = true;
		}
	}
	if(exist){
		if(Math.abs(counter)%8 == 0 || Math.abs(counter)%8 == 7 || Math.abs(counter)%8 == 6 || Math.abs(counter)%8 == 5){
			return false;//black
		}
		else{
			//red
			return true;
		}
	}
	else{//it is red
		if(Math.abs(counter)%8 == 1 || Math.abs(counter)%8 == 2 || Math.abs(counter)%8 == 3 || Math.abs(counter)%8 == 4){
			//it is red
			return false;
		}
		else{
			//black
			return true;
		}
	}
}

//Check with the given position, whether the piece exists or not - By YQ
public boolean isChessExist(int x, int y){
	boolean isExist = false;
	for(int i = 0;i<red.length;i++){
		if(chessBoard[x][y] == red[i]){
			isExist = true;
		}
	}
		for(int j = 0;j<black.length;j++){
			if(chessBoard[x][y] == black[j]){
				isExist = true;
			}
	}
	return isExist;	
} 

//check whether the move is a valid movement
//Modified from Desktop version UI - Han BAO
public boolean isValidMove(int startX, int startY, int endX, int endY){
	if(startX == endX && startY == endY){
		return false;
	}
    int minX = Math.min(startX, endX);
    int maxX = Math.max(startX, endX);
    int minY = Math.min(startY, endY);
    int maxY = Math.max(startY, endY);
	if(chessBoard[startX][startY].getId() == R.id.rju1 ||chessBoard[startX][startY].getId() == R.id.rju2
	||chessBoard[startX][startY].getId() == R.id.bju1 ||chessBoard[startX][startY].getId() == R.id.bju2){
		if (startX == endX) {
			int j = minY + 1;
	            if (j == maxY) {
	            	return true;
	            }
	            for (j = minY + 1; j <= maxY - 1; j++) {
	                if (isChessExist(startX,j)) {
	                	return false;
	                }
	            }
	            return true;
	    } 
		else if (startY == endY) {
	        int i = minX + 1;
	        if (i == maxX) {
	        	return true;
	        }
	        for (i = minX + 1; i <= maxX - 1; i++) {
	            if (isChessExist(i, startY)) {
	            	return false;
	            }
	        }
	        return true;
	    }
		return false;
	}
	else if(chessBoard[startX][startY].getId() == R.id.rma1 ||chessBoard[startX][startY].getId() == R.id.rma2
	||chessBoard[startX][startY].getId() == R.id.bma1 ||chessBoard[startX][startY].getId() == R.id.bma2){
		int xAxle = Math.abs(startX - endX);
	    int yAxle = Math.abs(startY - endY);
        if (xAxle == 2 && yAxle == 1) {
            if (endX > startX) {
                if (isChessExist(startX+1,startY)) {
                    return false;
                } else {
                    return true;
                }
            }
            if (endX < startX) {
                if (isChessExist(startX-1,startY)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        else if (xAxle == 1 && yAxle == 2) {
            if (endY > startY) {
                if (isChessExist(startX,startY+1)){
                    return false;
                } else {
                    return true;
                }
            }
            if (endY < startY) {
                if(isChessExist(startX,startY-1)){
                    return false;
                } 
                else{
                    return true;
                }
            }
        } 
        else {
           return false;
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.bxiang1 ||chessBoard[startX][startY].getId() == R.id.bxiang2){
	        int centerX = (startX + endX) / 2;
            int centerY = (startY + endY) / 2;
            int xAxle = Math.abs(startX - endX);
            int yAxle = Math.abs(startY - endY);
            if (xAxle == 2 && yAxle == 2 && endY <= 4) {
                if (isChessExist(centerX,centerY)) {
                    return false;
                } 
                else {
                    return true;
                }
            } 
            else {
                return false;
            }
	}
	else if(chessBoard[startX][startY].getId() == R.id.rxiang1 ||chessBoard[startX][startY].getId() == R.id.rxiang2 ){
		int centerX = (startX + endX) / 2;
        int centerY = (startY + endY) / 2;
        int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (xAxle == 2 && yAxle == 2 && endY >= 5) {
            if (isChessExist(centerX,centerY)) {
                return false;
            } 
            else {
                return true;
            }
        } 
        else {
            return false;
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.rpao1 ||chessBoard[startX][startY].getId() == R.id.rpao2 ||
	chessBoard[startX][startY].getId() == R.id.bpao1 ||chessBoard[startX][startY].getId() == R.id.bpao2){
        int number = 0;
        if (startX == endX) {
            int j = 0;
            for (j = minY + 1; j <= maxY - 1; j++) {
                if (isChessExist(startX,j)) {
                    number++;
                }
            }
            if (number > 1) {
                return false; 
            } 
            else if (number == 1) {
                if (isChessExist(endX,endY)) {
                    return true;
                }
            } 
            else if (number == 0 && !isChessExist(endX,endY)) {
                return true;
            } 
            else{
            	return false;
            }

        } 
        else if (startY == endY) {
            int i = 0;
            for (i = minX + 1; i <= maxX - 1; i++) {
                if (isChessExist(i,startY)) {
                    number++;
                }
            }
            if (number > 1) {
                return false; 
            } 
            else if (number == 1) {
                if (isChessExist(endX,endY)) {
                    return true;
                }

            } 
            else if (number == 0 &&  !isChessExist(endX, endY)) {
                return true;
            }
            else{
            	return false;
            }

        } 
        else {
            return false; 
        }  
    }
	
	else if(chessBoard[startX][startY].getId() == R.id.rbing1 ||chessBoard[startX][startY].getId() == R.id.rbing2 ||
	chessBoard[startX][startY].getId() == R.id.rbing3 ||chessBoard[startX][startY].getId() == R.id.rbing4 ||
	chessBoard[startX][startY].getId() == R.id.rbing5){
	    int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (endY >= 5) {
            if (startY - endY == 1 && xAxle == 0) {
                return true;
            }
            else {
                return false;
            }
        } 
        else if (endY <= 4) {
            if ((startY - endY == 1) && (xAxle == 0)) {
                return true;
            } 
            else if ((endY - startY == 0) && (xAxle == 1)) {
                return true;
            } 
            else {
                return false;
            }
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.bbing5 ||
	chessBoard[startX][startY].getId() == R.id.bbing3 ||chessBoard[startX][startY].getId() == R.id.bbing4 ||
	chessBoard[startX][startY].getId() == R.id.bbing1 ||chessBoard[startX][startY].getId() == R.id.bbing2){
		int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (endY <= 4) {
            if (endY - startY == 1 && xAxle == 0) {
                return true;  
            } 
            else {
                return false;
            }
        } 
        else if (endY >= 5) {
            if ((endY - startY == 1) && (xAxle == 0)) {
                return true;
            } 
            else if ((endY - startY == 0) && (xAxle == 1)) {
                return true;
            } 
            else {
                return false;
            }
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.bshi1 || chessBoard[startX][startY].getId() == R.id.bshi2){
        int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (endX <= 5 && endX >= 3 && endY <=2 && xAxle == 1 && yAxle == 1) {
            return true;
        } 
        else {
            return false;
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.rshi1 || chessBoard[startX][startY].getId() == R.id.rshi2){
		int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (endX <= 5 && endX >= 3 && endY >=7 && xAxle == 1 && yAxle == 1) {
            return true;
        } 
        else {
            return false;
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.rshuai ){
		int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (endX <= 5 && endX >= 3 && endY >=7 && (xAxle == 1 || yAxle == 1)) {
            return true;
        }
        else{
        	return false;
        }
	}
	else if(chessBoard[startX][startY].getId() == R.id.bshuai){
		int xAxle = Math.abs(startX - endX);
        int yAxle = Math.abs(startY - endY);
        if (endX <= 5 && endX >= 3 && endY <=2 && (xAxle == 1 || yAxle == 1)) {
            return true;
        }else{
        	return false;
        }
	
	}
	return false;
}

//check whether the first part is valid -By YQ
public boolean isFirstValid(int startX, int startY,int counter){
	if(!isChessExist(startX, startY)){
		return false;
	}
	else{
	if(isOpponent(chessBoard[startX][startY], counter)){
		return false;
	}
	return true;
	}
}

//check whether the second part is valid - By YQ
public boolean isSecondValid(int startX, int startY, int endX, int endY,int counter,ImageButton[] red, ImageButton[] black){
	if(!isChessExist(endX,endY)){
		if(isValidMove(startX, startY,endX, endY)){
			return true;
		}
		return false;
	}
	else{
		if(isOpponent(chessBoard[endX][endY],counter)){
			if(isValidMove(startX, startY, endX, endY)){
				return true;
			}
			return false;
		}
		return false;
		}
	}
}