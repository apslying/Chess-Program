package main;
import java.lang.Character;

public class Square {
	public String coordinates;
	public int file;
	public int rank;
	
	public int GetFile(){
		
		char file_char= coordinates.charAt(0);
		switch (file_char){
		case 'a':
			file=0;
			break;
		case 'b':
			file=1;
			break;
		case 'c':
			file=2;
			break;
		case 'd':
			file=3;
			break;
		case 'e':
			file=4;
			break;
		case 'f':
			file=5;
			break;
		case 'g':
			file=6;
			break;
		case 'h':
			file=7;
			break;
		}
		return file;
		
	}
	
	public int GetRank(){
		char rank_char= coordinates.charAt(1);
		int rank= Integer.parseInt(Character.toString(rank_char));
		rank=rank-1;
		return rank;
	}
	
}
