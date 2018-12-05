package ComputationalGraph;

public class Variable extends Node{
	
	public Variable(String name, Matrix value) {
		super(name, value.clone());
	}
	
	public Variable clone(String container, String name) {
		Variable c = new Variable(name, this.value.clone());
		nxtid -=1;
		c.nodeID = this.nodeID;
		c.container = container;
		return c;
	}
	
	@Override
	public void addToGraph(Graph graph) {
		graph.graphVariables.add(this);
	}
	
}
