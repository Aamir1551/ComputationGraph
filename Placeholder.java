package ComputationalGraph;

public class Placeholder extends Node{

	public Placeholder(String name, Matrix value) {
		super(name, value);
	}
	
	public Placeholder clone(String container, String name) {
		Placeholder c = new Placeholder(name, this.value); //placeholder does not take a clone of the "value" as the value will be changing
		nxtid -=1;
		c.nodeID = this.nodeID;
		c.container = container;
		return c;
	}
	
	@Override
	public void addToGraph(Graph graph) {
		graph.graphPlaceholders.add(this);
	}
	
}
