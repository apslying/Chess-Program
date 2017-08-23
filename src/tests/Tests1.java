package tests;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;
import main.Move;
import main.Position;
import pieces.*;
import tree.*;


//TODO make black test (all pieces)

public class Tests1 {
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
			whitePawns,
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			blackPawns,
			{blackRooks[0], blackKnights[0], blackBishops[0], blackKing, blackQueen, blackBishops[1], blackKnights[1], blackRooks[1]}};
		
		board = new Position(startingMatrix, false); 
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		Move h4= new Move(board.getPiece(1,0), 3, 0);
		//board.makeMove(e4);
		//board.makeMove(h4);
		//board.makeMove(Nf3);
		//System.out.println(board);
	}
	
/*	@After
	public void After() {
		board = new Position(startingMatrix, true);
		System.out.println(board.matrix[1][3]);
	}*/
	
	
	//Position Tests
	/*@Test
	public void TestBoard(){
		//System.out.println(whitePawns[0] + "k" +  blackPawns[0]);
		assertEquals(100, board.matrix[1][4].pointValue);
	}*/
	//start here! try to pretty print objects, (and matrices!) so that you can view the internals rather than see @fj94843
	
	@Test
	public void TestCountMaterial(){
		assertEquals(0, board.countMaterial());
	}
	
	@Ignore 
	public void TestMakeMove(){
		Piece[][] afterE4 = new Piece[][]{{whiteRooks[0], whiteKnights[0], whiteBishops[0], whiteKing, whiteQueen, whiteBishops[1], whiteKnights[1], whiteRooks[1]},
			{whitePawns[0], whitePawns[1], whitePawns[2], null, whitePawns[4], whitePawns[5], whitePawns[6], whitePawns[7]},
			null, null, null, null, null, null, null, null,
			{null,null,null,whitePawns[3],null,null,null,null},
			null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null,
			blackPawns,
			{blackRooks[0], blackKnights[0], blackBishops[0], blackKing, blackQueen, blackBishops[1], blackKnights[1], blackRooks[1]}};
		
		Move e4= new Move(board.getPiece(1,3), 3, 3);
		board.makeMove(e4);
		assertEquals(afterE4, board);
	}
	
	@Test
	public void TestListMoves(){
		ArrayList<Move> allMoves= new ArrayList<Move>();
		//Move e4= new Move(board.getPiece(1, 3), 3, 3);
		assertEquals(allMoves, board.listAllMoves());
	}
	
	
	//Pawn Tests
	@Test
	public void TestPossibleMovesPawn() {
		ArrayList<Move> pawnMoves= new ArrayList<Move>();
		Move d3= new Move(board.getPiece(1, 4), 2, 4);
		Move d4= new Move(board.getPiece(1, 4), 3, 4);
		pawnMoves.add(d3);
		pawnMoves.add(d4);

		assertEquals(pawnMoves, Pawn.possibleMoves(board, 1, 4));
		//only test hashcode of move at index 0 of linkedlist
	}
	
	@Test
	public void TestPossibleMovesPawnBlack() {
		ArrayList<Move> pawnMoves= new ArrayList<Move>();
		/*Move d6= new Move(board.getPiece(6, 4), 5, 4);
		Move d5= new Move(board.getPiece(6, 4), 4, 4);
		pawnMoves.add(d6);
		pawnMoves.add(d5);*/
		
		Move h6= new Move(board.getPiece(6, 0), 5, 0);
		Move h7= new Move(board.getPiece(6, 0), 4, 0);
		pawnMoves.add(h6);
		pawnMoves.add(h7);

		assertEquals(pawnMoves, Pawn.possibleMoves(board, 6, 0));
	}

	//Knight Tests
	@Test
	public void TestPossibleMovesKnight() {
		ArrayList<Move> knightMoves= new ArrayList<Move>();
		Move nh3= new Move(board.getPiece(0, 1), 2, 0);
		Move nf3= new Move(board.getPiece(0, 1), 2, 2);
		Move ne2= new Move(board.getPiece(0,1), 1, 3);
		knightMoves.add(ne2);
		knightMoves.add(nh3);
		knightMoves.add(nf3);
		
		
		assertEquals(knightMoves, Knight.possibleMoves(board, 0, 1));
		//only test hashcode of move at index 0 of linkedlist
	}
	
	//Bishop Tests
	@Test
	public void TestPossibleMovesBishop() {
		ArrayList<Move> bishopMoves= new ArrayList<Move>();
		Move be2= new Move(board.getPiece(0, 2), 1, 3);
		Move bd3= new Move(board.getPiece(0, 2), 2, 4);
		Move bc4= new Move(board.getPiece(0, 2), 3, 5);
		Move bb5= new Move(board.getPiece(0, 2), 4, 6);
		Move ba6= new Move(board.getPiece(0, 2), 5, 7);
		bishopMoves.add(be2);
		bishopMoves.add(bd3);
		bishopMoves.add(bc4);
		bishopMoves.add(bb5);
		bishopMoves.add(ba6);

		assertEquals(bishopMoves,Bishop.possibleMoves(board, 0, 2));
		//only test hashcode of move at index 0 of linkedlist
		assertEquals(bishopMoves.get(0).hashCode(), Bishop.possibleMoves(board, 0, 2).get(0).hashCode());
	}
	
	//Rook Tests
	@Test
	public void TestPossibleMovesRook() {
		ArrayList<Move> rookMoves= new ArrayList<Move>();
		Move rh2= new Move(board.getPiece(0, 0), 1, 0);
		Move rh3= new Move(board.getPiece(0, 0), 2, 0);
		rookMoves.add(rh2);
		rookMoves.add(rh3);
		

		assertEquals(rookMoves,Rook.possibleMoves(board, 0, 0));
		//only test hashcode of move at index 0 of linkedlist
	}
	
	//Queen Tests
	@Test
	public void TestPossibleMovesQueen() {
		ArrayList<Move> queenMoves= new ArrayList<Move>();
		Move qe2= new Move(board.getPiece(0, 4), 1, 3);
		Move qf3= new Move(board.getPiece(0, 4), 2, 2);
		Move qg4= new Move(board.getPiece(0, 4), 3, 1);
		Move qh5= new Move(board.getPiece(0, 4), 4, 0);
		queenMoves.add(qe2);
		queenMoves.add(qf3);
		queenMoves.add(qg4);
		queenMoves.add(qh5);


		assertEquals(queenMoves,Queen.possibleMoves(board, 0, 4));
		//only test hashcode of move at index 0 of linkedlist
		//assertEquals(queenMoves.getFirst().hashCode(), Queen.possibleMoves(afterE4, 0, 4).getFirst().hashCode());
	}
	
	//King Tests
	@Test
	public void TestPossibleMovesKing() {
		ArrayList<Move> kingMoves= new ArrayList<Move>();
		Move ke2= new Move(board.getPiece(0, 3), 1, 3);
		kingMoves.add(ke2);


		assertEquals(kingMoves, King.possibleMoves(board, 0, 3));
		//only test hashcode of move at index 0 of linkedlist
		//assertEquals(kingMoves.getFirst().hashCode(), King.possibleMoves(board, 0, 3).getFirst().hashCode());
	}
}
