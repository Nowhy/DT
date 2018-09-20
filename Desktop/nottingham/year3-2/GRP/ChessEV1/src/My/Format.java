package My;

import org.acerge.engine.*;

public class Format{
	
	public String getMessage(String input){
		return input.split(" ")[1];
	}
	
	public int getLocByCoor(char coor){
		switch(coor){
			case '1':
			case 'A':
				return 1;
			case '2':
			case 'B':
				return 2;
			case '3':
			case 'C':
				return 3;
			case '4':
			case 'D':
				return 4;
			case '5':
			case 'E':
				return 5;
			case '6':
			case 'F':
				return 6;
			case '7':
			case 'G':
				return 7;
			case '8':
			case 'H':
				return 8;
			case '9':
			case 'I':
				return 9;
			default:
				return 0;
		}
	}
	
	public int getIndexByCoor(ActiveBoard activeBoard, int x, int y){
		return activeBoard.getSquares(getPositionByCoor(activeBoard, x, y));
	}
	
	public int getPositionByCoor(ActiveBoard activeBoard, int x, int y){
		return x * 10 + y - 10;
	}
	
	public String getNameByIndex(int num){
		switch(num){
			case 16:
				return "K";
			case 17:
			case 18:
				return "A";
			case 19:
			case 20:
				return "B";
			case 21:
			case 22:
				return "N";
			case 23:
			case 24:
				return "R";
			case 25:
			case 26:
				return "C";
			case 27:
			case 28:
			case 29:
			case 30:
			case 31:
				return "P";
			case 32:
				return "k";
			case 33:
			case 34:
				return "a";
			case 35:
			case 36:
				return "b";
			case 37:
			case 38:
				return "n";
			case 39:
			case 40:
				return "r";
			case 41:
			case 42:
				return "c";
			case 43:
			case 44:
			case 45:
			case 46:
			case 47:
				return "p";
			default:
				return "0";
		}
	}
	
	public int getTypeByName(String name){
		switch (name){
			case "k":
			case "K":
				return 0;
			case "a":
			case "A":
				return 1;
			case "b":
			case "B":
				return 2;
			case "n":
			case "N":
				return 3;
			case "r":
			case "R":
				return 4;
			case "c":
			case "C":
				return 5;
			default:
				return 6;
		}
	}
	
	public int getTypeByCoor(ActiveBoard activeBoard, int x, int y){
		return getTypeByName(getNameByIndex(getIndexByCoor(activeBoard, x, y)));
	}
	
	public String getCoorByLoction(int num){
		String result = "";
		switch(num/10){
		case 0:
			result = result + "A";
			break;
		case 1:
			result = result + "B";
			break;
		case 2:
			result = result + "C";
			break;
		case 3:
			result = result + "D";
			break;
		case 4:
			result = result + "E";
			break;
		case 5:
			result = result + "F";
			break;
		case 6:
			result = result + "G";
			break;
		case 7:
			result = result + "H";
			break;
		case 8:
			result = result + "I";
			break;
			default:
				return "Invalid Position";
		}
		result = result + (num%10);
		return result;
	}
	
	
}