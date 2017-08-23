package tests;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
import main.Move;
import main.Position;
import pieces.*;
import tree.*;


public class Tests3 {
	@Test
	public void TestFen(){
		//TODO: has problem with h3h2
		Position position= Position.fenToMatrix("8/6p1/1p5p/2p5/4pP2/2bkP2p/3r4/5K2");
		System.out.println(position);
	}
	
	public void TestCastling() {
		
	}
}
