package ComputationalGraph;
import java.util.*;

public interface Operator {
	Operator myop = null;
	
	public static void connect(Node feeder, Node feeding) {
		feeder.feeds.add(feeding);
		feeding.feeders.add(feeder);
	}
	
	public Matrix compute(ArrayList<Node> feeders);

	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders);
	
}