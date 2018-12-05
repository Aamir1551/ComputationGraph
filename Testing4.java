package ComputationalGraph;

public class Testing4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Matrix p = new Matrix(2, 2, 1);
		p.matmul(new Matrix(2, 2, -4));
		p.print();
		
		Variable a = new Variable("a", new Matrix(1, 1, 5));
		Variable b = new Variable("b", new Matrix(1, 1, 4));
		Operation c = Add.f(a, b);
		
		Session mysess = new Session("mysess", c);
		mysess.run();
		Matrix.print(mysess.getValues(c));
		
		SimpleOptimiser mytrainer = new SimpleOptimiser(mysess);
		
		
		mytrainer.l_r = 0.1;
		
		for(int i=0;i<100;i++) {
			mytrainer.run();
			mysess.run();
			mysess.getValues(c, a, b).forEach(arg0-> {arg0.print();});;
		}
		mysess.run();
		Matrix.print(mysess.getValues(c, a, b));
		
		
	}

}
