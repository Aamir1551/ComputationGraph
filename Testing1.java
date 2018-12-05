package ComputationalGraph;

public class Testing1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Variable a = new Variable("a", new Matrix(3, 4, 2));
		Variable b = new Variable("b", new Matrix(4, 6, 8));
		
		Operation c = MatMul.f(a, b);
		
		Session sess = new Session("mysess", c);
		sess.run();
		Matrix.print(sess.getValues(c));
		
		SimpleOptimiser mytrainer = new SimpleOptimiser(sess);
		mytrainer.l_r = 0.1;
		
		for(int i=0;i<20;i++) {
			mytrainer.run();
		}
		
		sess.run();
		Matrix.print(sess.getValues(c));
		
		
		
	}

}
