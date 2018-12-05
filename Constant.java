package ComputationalGraph;

public class Constant extends Node{
	
	public Constant(String name, Matrix value) {
		super(name, value.clone());
		
	}

	public Constant clone(String container, String name) {
		Constant c = new Constant(name, this.value.clone());
		nxtid -=1;
		c.nodeID = this.nodeID;
		c.container = container;
		return c;
	}

	@Override
	public void addToGraph(Graph graph) {
		graph.graphConstants.add(this);
	}
}
