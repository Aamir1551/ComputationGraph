package ComputationalGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Transpose implements Operator{  //needs to be done
	public static final Transpose staticTranspose = new Transpose();
	private static Integer nxtTransposeOpID = 0;
	
	public static Operation f(Node a)
	{
		Operation c = new Operation("TransposeOp" + nxtTransposeOpID, null, staticTranspose);
		c.feeders.add(a);
		nxtTransposeOpID+=1;
		return c;
	}
	
	private Transpose() {}

	@Override
	public Matrix compute(ArrayList<Node> feeders) {
		return Matrix.transpose(feeders.get(0).value);
	}

	@Override
	public ArrayList<Matrix> d(Matrix currentGrad, ArrayList<Node> feeders) {
		return new ArrayList<Matrix>(Arrays.asList(Matrix.transpose(currentGrad)));
	}
	
}
