package ComputationalGraph;
import java.util.*;

public class Session {
	
	public Graph sessGraph;
	public final String name;
	
	public Session(String name, Node rootNode) {
		sessGraph = new Graph(name, rootNode);
		this.name = name;
	}
	
	public void run() {
		for(Operation i: sessGraph.graphOperations) {i.eval();}
	}
	
	public ArrayList<Matrix> getValues(Node ...vars) {
		ArrayList<Matrix> returnVals = new ArrayList<Matrix>(); //return vals is a list contianing all the node values that need to be returned
		Node.extractIds(vars).stream().forEach(item -> returnVals.add(Node.searchNode(item, sessGraph.graphNodes).value.clone()));
		return returnVals;
	}
	
	public void computeGradients() {
		ArrayList<Operation> temp = new ArrayList<Operation>(sessGraph.graphOperations);
		for(int i = temp.size() - 2; i>=0; i--) {
			temp.get(i).computeGrad();				
		}
	}
	
}
