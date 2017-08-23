package tree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import main.*;

public class MoveNode extends DefaultMutableTreeNode {
	public Move thisMove;
	public Move bestOppMove;
	public int evaluation;
	
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
