package ComputationalGraph;

import java.util.ArrayList;

public abstract class BinaryOperator implements Operator{
	private static Integer nxtBinaryOpID = 0;

	 protected static Operation createConnection(Node a, Node b, BinaryOperator op) {
		Operation c = new Operation("Operation: " + nxtBinaryOpID.toString(), null, op);
		nxtBinaryOpID+=1;
		Operator.connect(a, c);
		Operator.connect(b,c);
		return c;
	}

	@Override
	public abstract Matrix compute(ArrayList<Node> feeders);
	
}
