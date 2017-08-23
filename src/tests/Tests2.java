package tests;
import static org.junit.Assert.*;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import org.junit.*;

import main.Move;
import main.Position;
import pieces.*;
import tree.*;

public class Tests2 {
	static Position board;
	static Piece[][] startingMatrix;
	
	static Pawn[] whitePawns= new Pawn[8];
	static Knight[] whiteKnights= new Knight[2];
	static Bishop[] whiteBishops= new Bishop[2];
	static Rook[] whiteRooks= new Rook[2];
	static Pawn[] blackPawns= new Pawn[8];
	static Knight[] blackKnights= new Knight[2];
	static Bishop[] blackBishops= new Bishop[2];
	static Rook[] blackRooks= new Rook[2];
	static Queen whiteQueen= new Queen('Q', 0, 4, true);
	static Queen blackQueen= new Queen('Q', 7, 4, false);
	static King whiteKing= new King('K', 0, 3, true);
	static King blackKing= new King('K', 7, 3, false);
	
	@BeforeClass
	public static void initialize(){
		for (int file = 0; file < 8; file++) {
			Pawn newPawn= new Pawn('P', 1, file, true);
			whitePawns[file]=newPawn;
		}
		for (int file = 0; file < 8; file++) {
			Pawn newPawn= new Pawn('P', 6, file, false);
			blackPawns[file]=newPawn;
		}
		
		whiteKnights[0]= new Knight('N', 0, 1, true);
		whiteKnights[1]= new Knight('N', 0, 6, true);
		blackKnights[0]= new Knight('N', 7, 1, false);
		blackKnights[1]= new Knight('N', 7, 6, false);
		
		whiteBishops[0]= new Bishop('B', 0, 2, true);
		whiteBishops[1]= new Bishop('B', 0, 5, true);
		blackBishops[0]= new Bishop('B', 7, 2, false);
		blackBishops[1]= new Bishop('B', 7, 5, false);
		
		whiteRooks[0]= new Rook('R', 0, 0, true);
		whiteRooks[1]= new Rook('R', 0, 7, true);
		blackRooks[0]= new Rook('R', 7, 0, false);
		blackRooks[1]= new Rook('R', 7, 7, false);
		//end of creating pieces, now create board
		
		startingMatrix= new Piece[][]{
			{whiteRooks[0], whiteKnights[0], whiteBishops[0], whiteKing, whiteQueen, whiteBishops[1], whiteKnights[1], whiteRooks[1]},
			{whitePawns[0], whitePawns[1], whitePawns[2], whitePawns[3], whitePawns[4], whitePawns[5], whitePawns[6], whitePawns[7]},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{blackPawns[0], blackPawns[1], blackPawns[2], blackPawns[3], blackPawns[4], blackPawns[5], blackPawns[6], blackPawns[7]},
			{blackRooks[0], blackKnights[0], blackBishops[0], blackKing, blackQueen, blackBishops[1], blackKnights[1], blackRooks[1]}};
					
		board = new Position(startingMatrix, true); 
		//Move e4= new Move(board.getPiece(1,3), 3, 3);
		//Move h4= new Move(board.getPiece(1,0), 3, 0);
		//board.makeMove(e4);
		//board.makeMove(h4);
		//board.makeMove(Nf3);
	}
	
	
	@Ignore
	public void TestWhiteToMove(){
		ArrayList<Move> allMoves= new ArrayList<Move>();
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		Move e5= new Move(board.getPiece(6, 3), 4, 3);
		board.makeMove(e4);
		board.makeMove(e5);
		System.out.println(board);
		assertEquals(allMoves, board.listAllMoves());
		
	}
	
	@Ignore
	public void TestBishopCapture(){
		ArrayList<Move> allMoves= new ArrayList<Move>();
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		Move a6= new Move(board.getPiece(6, 7), 5, 7);
		Move ba6= new Move(board.getPiece(0,2), 5, 7);
		board.makeMove(e4);
		board.makeMove(a6);
		board.makeMove(ba6);
		System.out.println(board);
		assertEquals(allMoves, board.listAllMoves());
		
	}
	
	@Ignore
	public void TestFourMoveMate(){
		ArrayList<Move> allMoves= new ArrayList<Move>();
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		board.makeMove(e4);
		Move e5= new Move(board.getPiece(6,3), 4, 3);
		board.makeMove(e5);
		Move bc4= new Move(board.getPiece(0,2), 3, 5);
		board.makeMove(bc4);
		Move nf6= new Move(board.getPiece(7,1), 5, 2);
		board.makeMove(nf6);
		Move qh5= new Move(board.getPiece(0,4), 4, 0);
		board.makeMove(qh5);
		Move ne4= new Move(board.getPiece(5,2), 3, 3);
		board.makeMove(ne4);
		Move qf7= new Move(board.getPiece(4,0), 6, 2);
		board.makeMove(qf7);
		Move kf7= new Move(board.getPiece(7,3), 6, 2);
		board.makeMove(kf7);
		Move bf7= new Move(board.getPiece(3,5), 6, 2);
		board.makeMove(bf7);
		
		System.out.println(board);
		assertEquals(allMoves, board.listAllMoves());
	}
	
	@Ignore
	public void TestPawnCapture(){
		ArrayList<Move> allMoves= new ArrayList<Move>();
		Move e4= new Move(board.getPiece(1,3), 3,3);
		board.makeMove(e4);
		Move d5= new Move(board.getPiece(6, 4), 4,4);
		board.makeMove(d5);
		//Move ba6= new Move(board.getPiece(0,2), 5, 7);
		//board.makeMove(ba6);
		Move exd5= new Move(board.getPiece(3,3), 4,4);
		board.makeMove(exd5);

		System.out.println(board);
		assertEquals(allMoves, board.listAllMoves());
	}
	
	@Ignore
	public void TestUndoMove() {
		ArrayList<Move> allMoves= new ArrayList<Move>();
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		board.makeMove(e4);
		Move e5= new Move(board.getPiece(6,3), 4, 3);
		board.makeMove(e5);
		Move bc4= new Move(board.getPiece(0,2), 3, 5);
		board.makeMove(bc4);
		Move nf6= new Move(board.getPiece(7,1), 5, 2);
		board.makeMove(nf6);
		Move qh5= new Move(board.getPiece(0,4), 4, 0);
		board.makeMove(qh5);
		Move ne4= new Move(board.getPiece(5,2), 3, 3);
		board.makeMove(ne4);
		Move qf7= new Move(board.getPiece(4,0), 6, 2);
		board.makeMove(qf7);
		Move kf7= new Move(board.getPiece(7,3), 6, 2);
		board.makeMove(kf7);
		Move bf7= new Move(board.getPiece(3,5), 6, 2);
		board.makeMove(bf7);
		
		board.undoMove(bf7);
		board.undoMove(kf7);
		board.undoMove(qf7);
		board.undoMove(ne4);
		board.undoMove(qh5);
		board.undoMove(nf6);
		board.undoMove(bc4);
		board.undoMove(e5);
		board.undoMove(e4);
		
		
		System.out.println(board);
	}
	
	@Ignore
	public void TestCountMobility(){
		/*Move e4= new Move(board.getPiece(1,3), 3,3);
		board.makeMove(e4);*/
		Move Nh3= new Move(board.getPiece(0,1), 2,0);
		board.makeMove(Nh3);
		Move e5= new Move(board.getPiece(6,3), 4, 3);
		board.makeMove(e5);
		System.out.println(board);
		assertEquals(22, board.countMobility());
	}
	
	@Ignore
	public void TestMoveTree2(){
		MoveNode moveNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(moveNode, board);
		System.out.println(moveNode);
	}
	
	@Ignore
	public void TestMoveTree(){
		MoveNode moveNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(moveNode, board);
		moveTree.createChildren(moveNode);
		moveTree.createChildlessNodes(board);
		
		MoveNode root= (MoveNode) moveTree.getRoot();
		System.out.println("root");
		System.out.println(root.thisMove);
		//System.out.println(root.listOfMoves);
		
		for (int i = 0; i < root.getChildCount(); i++) {
			MoveNode whiteFirstMoves= (MoveNode) moveTree.getChild(root, i);
			System.out.println("white------------------------------------------------------------------------------------------------------");
			System.out.println(whiteFirstMoves.thisMove);
			//System.out.println(whiteFirstMoves.listOfMoves);
			for (int j = 0; j < whiteFirstMoves.getChildCount(); j++) {
				MoveNode blackFirstMoves= (MoveNode) moveTree.getChild(whiteFirstMoves, j);
				System.out.println("black");
				System.out.println(blackFirstMoves.thisMove);
				//System.out.println(blackFirstMoves.listOfMoves);
			}
		}
	}
	
	
	
	@Ignore
	public void TestEval1Level(){
		MoveNode moveNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(moveNode, board);
		moveTree.createChildren(moveNode);
		moveTree.createChildlessNodes(board);
		MoveNode root= (MoveNode) moveTree.getRoot();
		
		for (int i = 0; i < root.getChildCount(); i++) {
			MoveNode whiteFirstMove= (MoveNode) moveTree.getChild(root, i);
			moveTree.evaluate(whiteFirstMove);
			System.out.println("white---- " + whiteFirstMove.thisMove + " " + whiteFirstMove.evaluation+ " best: " + whiteFirstMove.bestOppMove);

			for (int j = 0; j < whiteFirstMove.getChildCount(); j++) {
				MoveNode blackFirstMove= (MoveNode) moveTree.getChild(whiteFirstMove, j);
				System.out.println(blackFirstMove.thisMove + " " + blackFirstMove.evaluation);	
			}
		}
	}
	
	
	@Ignore
	public void TestEval2Level(){
		MoveNode moveNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(moveNode, board);
		moveTree.createChildren(moveNode);
		moveTree.createChildlessNodes(board);
		moveTree.createChildlessNodes(board);
		moveTree.createChildlessNodes(board);

		MoveNode root= (MoveNode) moveTree.getRoot();
		
		//System.out.println("root's children " + root.getChildCount());
		
		for (int i = 0; i < root.getChildCount(); i++) {
			MoveNode whiteFirstMove= (MoveNode) moveTree.getChild(root, i);
			System.out.println("white------------------ " + whiteFirstMove.thisMove);
			for (int j = 0; j < whiteFirstMove.getChildCount(); j++) {
				MoveNode blackFirstMove= (MoveNode) moveTree.getChild(whiteFirstMove, j);
				moveTree.evaluate(blackFirstMove);
				System.out.println(blackFirstMove.thisMove + " " + blackFirstMove.evaluation + " best: " + blackFirstMove.bestOppMove);

			}
		}
	}
	
	@Ignore
	public void Test4MoveMate(){
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		board.makeMove(e4);
		Move nf6= new Move(board.getPiece(7,1), 5, 2);
		board.makeMove(nf6);
		Move bc4= new Move(board.getPiece(0,2), 3, 5);
		board.makeMove(bc4);
		Move ng8= new Move(board.getPiece(5,2), 7, 1);
		board.makeMove(ng8);
		Move qh5= new Move(board.getPiece(0,4), 4, 0);
		board.makeMove(qh5);
		Move g5= new Move(board.getPiece(6,1), 4, 1);
		board.makeMove(g5);
		board= Position.fenToMatrix("4rr2/p5kn/1p6/2p1Rp2/q7/7R/1B3PPP/6K1");
		System.out.println(board);
		
		MoveNode rootNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode); //level 1
		moveTree.createChildlessNodes(board); //level 2
		moveTree.createChildlessNodes(board); //level 3
		//moveTree.createChildlessNodes(board); //level 4
		//moveTree.createChildlessNodes(board); //level 5

		moveTree.evaluate(rootNode);
		System.out.println(rootNode.bestOppMove);
		//MoveNode level1BestNode= (MoveNode) rootNode.getBestMoveNode(rootNode.bestOppMove);
		//MoveNode level2BestNode= (MoveNode) level1BestNode.getBestMoveNode(level1BestNode.bestOppMove);
		//MoveNode level3BestNode= (MoveNode) level2BestNode.getBestMoveNode(level2BestNode.bestOppMove);
		//MoveNode level4BestNode= (MoveNode) level2BestNode.getBestMoveNode(level3BestNode.bestOppMove);
		
		//moveTree.printChildrenInfo(rootNode);
		//moveTree.printChildrenInfo(level1BestNode);
		//moveTree.printChildrenInfo(level2BestNode);
		//moveTree.printChildrenInfo(level3BestNode);
		//moveTree.printChildrenInfo(level4BestNode);
	}
	
	@Ignore
	public void Test2Levels(){
		MoveNode rootNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode);
		moveTree.evaluate(rootNode);

		moveTree.printNodeInfo(rootNode);		
		moveTree.printChildrenInfo(rootNode);
	}
	
	@Ignore
	public void Test3Levels(){
		MoveNode rootNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode);
		moveTree.createChildlessNodes(board);
		moveTree.createChildlessNodes(board);
		moveTree.evaluate(rootNode);
		MoveNode level1BestNode= (MoveNode) rootNode.getBestMoveNode(rootNode.bestOppMove);

		
		moveTree.printChildrenInfo(rootNode);
		moveTree.printChildrenInfo(level1BestNode);
	}
	
	@Ignore
	public void Test5Levels(){
		MoveNode rootNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode);
		moveTree.createChildlessNodes(board);
		moveTree.createChildlessNodes(board);
		moveTree.createChildlessNodes(board);
		moveTree.evaluate(rootNode);
		MoveNode level1BestNode= (MoveNode) rootNode.getBestMoveNode(rootNode.bestOppMove);
		MoveNode level2BestNode= (MoveNode) level1BestNode.getBestMoveNode(level1BestNode.bestOppMove);
		MoveNode level3BestNode= (MoveNode) level2BestNode.getBestMoveNode(level2BestNode.bestOppMove);

		
		moveTree.printChildrenInfo(rootNode);
		moveTree.printChildrenInfo(level1BestNode);
		moveTree.printChildrenInfo(level2BestNode);
		moveTree.printChildrenInfo(level3BestNode);

	}
	
	@Ignore
	public void TestBestMove(){ //works
		MoveNode rootNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode);
		moveTree.evaluate(rootNode);
		moveTree.printNodeInfo(rootNode);
		MoveNode bestMoveNode= (MoveNode) rootNode.getBestMoveNode(rootNode.bestOppMove);

		moveTree.printNodeInfo(bestMoveNode);
	}
	
	@Ignore
	public void TestHomePawnCapture() {
		Move nf3= new Move(board.getPiece(0,1), 2,2);
		board.makeMove(nf3);
		Move nf6= new Move(board.getPiece(7,1), 5,2);
		board.makeMove(nf6);
		Move ne5= new Move(board.getPiece(2,2), 4,3);
		board.makeMove(ne5);
		Move ne4= new Move(board.getPiece(5,2), 3,3);
		board.makeMove(ne4);
		Move ng6= new Move(board.getPiece(4,3), 5,1);
		board.makeMove(ng6);
			
		MoveNode rootNode= new MoveNode(null);
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode);
		moveTree.evaluate(rootNode);
		moveTree.printChildrenInfo(rootNode);
		
	}
	
	@Test
	public void TestCastling() {
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		board.makeMove(e4);
		Move nf6= new Move(board.getPiece(7,1), 5, 2);
		board.makeMove(nf6);
		Move bc4= new Move(board.getPiece(0,2), 3, 5);
		board.makeMove(bc4);
		Move ng8= new Move(board.getPiece(5,2), 7, 1);
		board.makeMove(ng8);
		Move nf3= new Move(board.getPiece(0,1), 2,2);
		board.makeMove(nf3);
		board.makeMove(nf6);
//		Move castle= new Move(board.getPiece(0,3), 0,1);
//		board.makeMove(castle);
		System.out.println(King.possibleMoves(board, 0, 3));
		System.out.println(board);
	}
	
}

