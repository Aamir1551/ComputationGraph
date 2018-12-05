package ComputationalGraph;

public class Testing3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Variable x = new Variable("x", new Matrix(1, 1, -1.2));
		Operation x2 = Multiply.f(x, x);
		x2.name = "x2";
		Operation xtimes3 = Multiply.f(x, new Constant("c1" ,new Matrix(1,1,3)));
		xtimes3.name = "xtimes3";
		Constant con5 =  new Constant("c2", new Matrix(1, 1, 5));
		Operation z1 = Add.f(x2, xtimes3);
		z1.name = "z1";
		Operation z2 = Add.f(z1, con5);
		z2.name = "z2";
		
		//Operation z = Add.f(Multiply.f(x, x), Add.f(Multiply.f(x, new Constant("c1" ,new Matrix(1,1,3))), new Constant("c2", new Matrix(1, 1, 5))));
		
		Session mysess = new Session("mysess", z2);
		mysess.run();
		Matrix.print(mysess.getValues(z2));
		
		SimpleOptimiser mytrainer = new SimpleOptimiser(mysess);
		mytrainer.l_r = 0.01;
		
		for(int i=0;i<10000;i++) {
			mytrainer.run();
		}
		
		mysess.run();
		Matrix.print(mysess.getValues(z2, x));
		System.out.println("asds");

	}

}
