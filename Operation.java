package ComputationalGraph;

import java.util.ArrayList;

public class Operation extends Node{
	public Operator op;
	
	
	public Operation(String name, Matrix value, Operator op) {
		super(name, value);
		this.op = op;
	}

	public void eval() {
		this.value = this.op.compute(feeders);
	}

	@Override
	public Operation clone(String container, String name) {
		Operation c = new Operation(name, null, this.op);
		nxtid -=1;
		c.nodeID = this.nodeID;
		c.container = container;
		return c;
	}
	
	protected void computeGrad() {
		ArrayList<Matrix> temp = this.op.d(this.gradients.get(this.gradients.size() -1), feeders);
		for(int i = 0;i<feeders.size();i++) {
			feeders.get(i).gradients.get(feeders.get(i).gradients.size() - 1).add(temp.get(i)); 
		}
	}
	
	@Override
	public void addToGraph(Graph graph) {
		graph.graphOperations.add(this);
	}

}
