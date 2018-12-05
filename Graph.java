package ComputationalGraph;
import java.util.*;

public class Graph {
	
	ArrayList<Node> graphNodes = new ArrayList<Node>();
	LinkedHashSet<Variable> graphVariables = new LinkedHashSet<Variable>();
	LinkedHashSet<Constant> graphConstants = new LinkedHashSet<Constant>();
	LinkedHashSet<Operation> graphOperations = new LinkedHashSet<Operation>();
	LinkedHashSet<Placeholder> graphPlaceholders = new LinkedHashSet<Placeholder>();
	
	public Graph(String container, Node rootNode) {
		this.graphNodes = cloneNodes(container, generateGraph(new ArrayList<Node>(Arrays.asList(rootNode))));
	}
	
	private ArrayList<Node> generateGraph(ArrayList<Node> vars) {
		LinkedHashSet<Node> graphNodes = new LinkedHashSet<Node>();
		for(Node i: vars) {
				graphNodes.addAll(generateGraph(i.feeders));
				graphNodes.add(i);
				
		}
		return new ArrayList<Node>(graphNodes);
	}
	
	private ArrayList<Node> cloneNodes(String container, ArrayList<Node> nodeList) {
		ArrayList<Node> clonedNodes = new ArrayList<Node>();
		
		for(Node i : nodeList) {
			Node temp = i.clone(container, i.name);
			clonedNodes.add(temp);
			temp.addToGraph(this);
		}
		
		for(Node i : nodeList) { 
			for(Node j : i.feeders) {
				Node.searchNode(i.nodeID, clonedNodes).feeders.add(Node.searchNode(Node.extractIds(j).get(0), clonedNodes));
			}
		}
		
		for(Node i : nodeList) {
			for(Node j : i.feeds) {
				Node.searchNode(i.nodeID, clonedNodes).feeds.add(Node.searchNode(Node.extractIds(j).get(0), clonedNodes));
			}
			
		}
		
		return clonedNodes;
	}

	
}
