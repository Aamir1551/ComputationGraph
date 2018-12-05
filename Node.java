package ComputationalGraph;
import java.util.*;
public abstract class Node {

	Matrix value = null;
	protected ArrayList<Node> feeds = new ArrayList<Node>();
	protected ArrayList<Node> feeders = new ArrayList<Node>();
	protected ArrayList<Matrix> gradients = new ArrayList<Matrix>(); //list of all the gradients of this node for every iteration
	protected String name = "";
	public static int nxtid;
	protected int nodeID;
	public String container = null;
	
	public Node(String name, Matrix value) {
		this.name = name;
		this.value = value;
		nodeID =nxtid;
		nxtid +=1;
	}

	
	public void print() {
		value.print();
	}
	
	public abstract Node clone(String container, String name);
	
	public static Node searchNode(int id, Collection<Node> nodeList) {   //make searching more efficient using hashmap<id, node>
		for(Node i:nodeList) {
			if (i.nodeID == id) {
				return i;
			}
		}
		return null;
	}
	
	public static ArrayList<Integer> extractIds(Node ...vars) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(Node i: vars) {
			temp.add(i.nodeID);
		}
		return temp;
	}
	
	public static ArrayList<Node> convertToArrayList(Node ...vars) {
		ArrayList<Node> result = new ArrayList<Node>();
		for(Node i : vars) {
			result.add(i);
		}
		return result;
	}
	
	public abstract void addToGraph(Graph graph);
	
}
