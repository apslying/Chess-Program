package tree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import main.*;

public class MoveTree extends DefaultTreeModel{
	
	public Position position;

	public MoveTree(TreeNode root, Position position) {
		super(root);
		this.position=position;
	}

	/*public void AddRoot(ArrayList<Move> listOfMoves){//may not need this! Position position, 
		//tree where each node is a linked list
			MoveNode moveNode=new MoveNode(null,listOfMoves);
			this.setRoot(moveNode);	
	}*/

	
	public void createChildren(MoveNode moveNode){
		ArrayList<Move> listOfMoves= new ArrayList<Move>();
		TreeNode[] treeNodes= this.getPathToRoot(moveNode);
		MoveNode[] path= Arrays.copyOf(treeNodes, treeNodes.length, MoveNode[].class);
		
		
		
		for (int i = 1; i < path.length; i++) {
			position.makeMove(path[i].thisMove);
		}
		
	
		listOfMoves= position.listAllMoves();
		
		for (int i = 0; i < listOfMoves.size(); i++) {
			Move nextMove= (Move) listOfMoves.get(i);
			this.position.makeMove(nextMove);
			MoveNode newNode= new MoveNode(nextMove);
			this.position.undoMove(nextMove);
			this.insertNodeInto(newNode, moveNode, i);
		}
		for (int i = path.length-1; i >= 1; i--) {
			position.undoMove(path[i].thisMove);
		}
		
		
	}
	
	//refactor to use do while (but getFirstLeaf vs getNextLeaf)
	public void createChildlessNodes(Position position){
		//note: getPathToRoot includes root and givenNode
		MoveNode root= (MoveNode) this.getRoot();
		MoveNode leaf=(MoveNode) root.getFirstLeaf();
		//System.out.println("leaf: "+leaf);
		this.createChildren(leaf);
		
		//repeat
		while (leaf.getNextLeaf()!=null){
			leaf=(MoveNode) leaf.getNextLeaf();
			this.createChildren(leaf);
		}
	}
	
	public void printPath(MoveNode moveNode) {
		TreeNode[] treeNodes= this.getPathToRoot(moveNode);
		MoveNode[] path= Arrays.copyOf(treeNodes, treeNodes.length, MoveNode[].class);
		for (int i = 0; i < path.length; i++) {
			System.out.print(path[i] + ",");
		}
	}
	
	public void makeMoves(MoveNode moveNode) {
		TreeNode[] treeNodes= this.getPathToRoot(moveNode);
		MoveNode[] path= Arrays.copyOf(treeNodes, treeNodes.length, MoveNode[].class);
		for (int i = 1; i < path.length; i++) {
			position.makeMove(path[i].thisMove);
		}
	}
	
	//improve: find path 2x (once in makeMoves, once in undoMoves)
	public void undoMoves(MoveNode moveNode) {
		TreeNode[] treeNodes= this.getPathToRoot(moveNode);
		MoveNode[] path= Arrays.copyOf(treeNodes, treeNodes.length, MoveNode[].class);
		for (int i = path.length-1; i >= 1; i--) {
			//i ends at 1 to not include null root
			position.undoMove(path[i].thisMove);
		}
	}
	
	
	//improve undoing position so that it can undo one move, and not always start from the root
	public int evaluate(MoveNode moveNode){
		//System.out.println(moveNode);
		//System.out.println(position);

		if (moveNode.isLeaf()==true){
			this.makeMoves(moveNode);
			//System.out.println(this.position); //HERE TO PRINT BOARD
			//this.position.whiteToMove=!this.position.whiteToMove;
			moveNode.evaluation=this.position.countMaterial() + this.position.countMobility();
			this.undoMoves(moveNode);
		}
		
		//finally I am at the "bulk" of the program
		else{
			if(moveNode.thisMove!=null && moveNode.thisMove.piece.isWhite) {
				int min;
				MoveNode childNode=(MoveNode) moveNode.getFirstChild();
				moveNode.evaluation=evaluate(childNode);
				moveNode.bestOppMove=childNode.thisMove;
				//System.out.println(moveNode.evaluation);
				
				while (childNode.getNextSibling()!=null){
					childNode=(MoveNode) childNode.getNextSibling();
					min=evaluate(childNode);
					if(min<moveNode.evaluation) {
						moveNode.evaluation=min;
						moveNode.bestOppMove=childNode.thisMove;
					}
				}
			}
			else {
				int max;
				MoveNode childNode=(MoveNode) moveNode.getFirstChild();
				moveNode.evaluation=evaluate(childNode);
				moveNode.bestOppMove=childNode.thisMove;
				//System.out.println(moveNode.evaluation);
				
				while (childNode.getNextSibling()!=null){
					childNode=(MoveNode) childNode.getNextSibling();
					max=evaluate(childNode);
					if(max>moveNode.evaluation) {
						moveNode.evaluation=max;
						moveNode.bestOppMove=childNode.thisMove;
					}
				}
			}
			
		}
		return moveNode.evaluation;

	}
	
//	public MoveNode outputMove(MoveNode moveNode) {
//		//assumes moveNode has children
//		if (!moveNode.isLeaf()) {
//			this.evaluate(moveNode);
//			return moveNode;
//		}
//		return null;
//	}
	
	public void printNodeInfo(MoveNode moveNode) {
		System.out.println(moveNode.thisMove + " eval:" + moveNode.evaluation + " oppBest:" + moveNode.bestOppMove);
	}
	
	public void printChildrenInfo(MoveNode moveNode) {
		//TODO: catch NPE when no children 
		System.out.print("parent:");
		this.printNodeInfo(moveNode);
		System.out.println("children: "+ moveNode.getChildCount());
		for (int i = 0; i < moveNode.getChildCount(); i++) {
			MoveNode childNode= (MoveNode) moveNode.getChildAt(i);
			this.printNodeInfo(childNode);
		}
	}

}

