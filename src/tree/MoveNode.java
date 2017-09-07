package tree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import main.*;

public class MoveNode extends DefaultMutableTreeNode { //80 bytes
	public Move thisMove; //38 bytes
	public Move bestOppMove; //38 bytes
	public int evaluation; //4 bytes
	
	public MoveNode(Move thisMove) {
		super();
		this.thisMove = thisMove;
	}
	
	public MoveNode(Move thisMove, Move bestMove, int evaluation) {
		super();
		this.thisMove = thisMove;
		this.bestOppMove = bestMove;
		this.evaluation = evaluation;
	}
	
	@Override
	public String toString() {
		if (this.thisMove==null) {
			return "(null)";
		}
		else {
			return this.thisMove.toString();
		}
	}
	
	public MoveNode getBestMoveNode(Move bestMove) {
		for (int i = 0; i < this.getChildCount(); i++) {
			if (((MoveNode) this.getChildAt(i)).thisMove.equals(bestMove)) {
				return (MoveNode) this.getChildAt(i);
			}
		}
		return null;
	}
}
