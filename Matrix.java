package ComputationalGraph;

import java.util.ArrayList;
import java.util.Random;

//have a look at the clone method cos it looks nasty  //test out reshape
public class Matrix {
	
	double[][] values;
	boolean transposed = false;
	private int dim1;
	private int dim2;
	
	public Matrix(int n, int m) {
		values = new double[n][m];
		dim1 = n;
		dim2 = m;
	}
	
	public Matrix(int n, int m, double v) {
		this(n,m);
		for(int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				values[i][j] = v;
			}
		}
	}
	
	public static Matrix arange(int n, int m) {
		Matrix c = new Matrix(n,m);
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				c.setItem(i, j, i*(m) + j);
			}
		}
		return c;
	}
	
	public static Matrix randn(int n, int m) {
		Random rand = new Random();
		Matrix c = new Matrix(n,m);
		for (int i = 0; i<n;i++) {
			for (int j=0;j<m;j++) {
				c.setItem(i, j, rand.nextGaussian());
			}
		}
		return c;
	}
	
	public double getItem(int i, int j) {
		if (!transposed) {
			return values[i][j];
		} else {
			return values[j][i];
		}
	}
	
	public void setItem(int i, int j, double v) {
		if (!transposed) {
			values[i][j] = v;
		} else {
			values[j][i] = v;
		}
		
	}
	
	public int getDim1() {
		return ((this.transposed) ? this.dim2 : this.dim1);
	}
	
	public int getDim2() {
		return ((this.transposed) ? this.dim1 : this.dim2);
	}
	
	//Matrix Operations
	public static Matrix add(Matrix a, Matrix b) {
		dimEqual(a, b, true, "addition", true);
		
		if(a.IsScaler() && b.IsScaler()) {
			return new Matrix(1, 1, a.getItem(0, 0) + b.getItem(0, 0));
		}
		if (a.IsScaler()) {
			return Matrix.add(new Matrix(b.dim1, b.dim2, a.getItem(0, 0)), b);
		}
		if (b.IsScaler()) {
			return Matrix.add(new Matrix(a.dim1, a.dim2, b.getItem(0, 0)), a);
		}
		
		Matrix c = new Matrix(a.dim1, a.dim2);
		for(int i=0; i<a.dim1;i++) {
			for(int j=0;j<a.dim2;j++) {
				c.setItem(i, j, a.getItem(i,j) + b.getItem(i,j));
			}
		}
		return c;
	}
	
	public static Matrix subtract(Matrix a, Matrix b) {
		dimEqual(a, b, true, "Subtraction", true);
		return add(a, Matrix.multiply(-1,b));
	}
	
	public static Matrix multiply(Matrix a, Matrix b) {
		dimEqual(a,b,true,"Elementwise-multiplication", true);
		
		if(a.IsScaler() && b.IsScaler()) {
			return new Matrix(1, 1, a.getItem(0, 0) * b.getItem(0, 0));
		}
		
		if (a.IsScaler()) {
			return Matrix.multiply(new Matrix(b.dim1, b.dim2, a.getItem(0, 0)), b);
		}
		if (b.IsScaler()) {
			return Matrix.multiply(new Matrix(a.dim1, a.dim2, b.getItem(0, 0)), a);
		}
		
		Matrix c = new Matrix(a.dim1, a.dim2);
		for(int i =0; i < a.dim1; i++) {
			for(int j=0;j<a.dim2;j++) {
				c.setItem(i, j, a.getItem(i,j) * b.getItem(i,j));
			}
		}
		return c;
	}
	
	
	public static Matrix multiply(double a, Matrix b) {
		Matrix c = new Matrix(b.dim1, b.dim2,a);
		return multiply(c,b);
	}
	
	public static Matrix multiply(Matrix b, double a) {
		return multiply(a,b);
	}
	
	public static Matrix divide(Matrix a, Matrix b) {
		dimEqual(a,b,true, "division", true);
		return multiply(a,divide(1,b));
	}
	
	public static Matrix divide(double a, Matrix b) {
		Matrix c = new Matrix(b.dim1, b.dim2);
		for(int i=0;i<b.dim1;i++) {
			for(int j=0;j<b.dim2;j++) {
				c.setItem(i, j, a / b.getItem(i,j));;
			}
		}
		return c;
	}
	
	public static Matrix matmul(Matrix a, Matrix b) {
		if(a.IsScaler() || b.IsScaler()) {
			return Matrix.multiply(a, b);
		}
		
		
		if(a.dim2 != b.dim1) {
			throw new java.lang.Error("Shapes do not conform for Matrix Multiplication");
		}
		Matrix c = new Matrix(a.dim1,b.dim2);
		for(int k=0;k<b.dim2;k++) {
			for(int i=0;i<a.dim1;i++) {
				float temp = 0;
				for(int j=0;j<a.dim2;j++) {
					temp += a.getItem(i, j)*b.getItem(j, k);
				}
				c.setItem(i, k, temp);
			}
		}
		return c;
	}
	
	public void add(Matrix a) {
		this.values = Matrix.add(a, this).values;
		
	}
	
	public void subtract(Matrix a) {
		this.values = Matrix.subtract(this, a).values;
	}
	
	public void multiply(Matrix a) {
		this.values = Matrix.multiply(this, a).values;
	}
	
	public void matmul(Matrix a) {
		this.values = Matrix.matmul(this, a).values;
		this.dim2 = a.dim2;
		this.transposed = false;
	}
	
	public void divide(Matrix a) {
		this.values = Matrix.divide(this, a).values;
	}
	
	
	public void transpose() {
		int temp = dim1;
		dim1 = dim2;
		dim2 = temp;
		transposed =  !transposed; 
	}
	
	
	public static Matrix transpose(Matrix a) {
		Matrix c = a.clone();
		c.transpose();
		return c;
	}
	
	public static Matrix exp(Matrix a) {
		Matrix result = new Matrix(a.dim1, a.dim2);
		for(int i=0;i<a.dim1;i++) {
			for(int j=0;j<a.dim2;j++) {
				result.setItem(i, j, Math.exp(a.getItem(i, j)) );
			}
		}
		return result;
	}
	
	public static boolean dimEqual(Matrix a, Matrix b, boolean handle, String d, boolean allowScaler) {
		if(a.IsScaler() || b.IsScaler()) {
			return true;
		}
		if (a.dim1 == b.dim1 && a.dim2 == b.dim2) {
			return true;
		} else if(handle) {
			throw new java.lang.Error("Shapes do not conform for " + d);
		} else {
			return false;
		}
		
	}
	
	public static Matrix reshape(Matrix a, Matrix b, Matrix c) {
		if(!(b.IsScaler() && c.IsScaler())) {
			throw new java.lang.Error("Matrix b and c must have dimensions (1,1)");
		}
		
		int bval = (int) Math.round(b.getItem(0, 0));
		int cval = (int) Math.round(c.getItem(0, 0));
		
		if(bval*cval != a.dim1 * a.dim2) {
			throw new java.lang.Error("Shapes do not conform for reshaping");
		}
		
		
		Matrix result = new Matrix(bval, cval);
		ArrayList<Double> vals = a.iterVal();
		
		for(int i=0;i<bval;i++) {
			for(int j=0;j<cval;j++) {
				result.setItem(i, j, vals.get(i*cval + j));
			}
		}
		return result;
	}
	
	public boolean IsScaler() {
		if (this.dim1 == this.dim2 && this.dim1 == 1) {
			return true;
		}
		return false;
	}
	
	public void print() {
		System.out.print("[");
		for(int i=0;i<this.dim1;i++) {
			System.out.print("[");
			for(int j=0;j<this.dim2;j++) {
				System.out.print(this.values[i][j] + " ");
			}
			System.out.print("]");
			if(i != this.dim1 -1) {
				System.out.println("");
			} else {
				System.out.print("]");
			}
		}
		System.out.println("");
	}
	
	public ArrayList<Double> iterVal() {
		ArrayList<Double> result = new ArrayList<Double>();
		for(int i=0;i<dim1;i++) {
			for(int j=0;j<dim2;j++) {
				result.add(getItem(i, j));
			}
		}
		return result;
	}
	
	public static void print(ArrayList<Matrix> MatrixList) {
		for (Matrix i : MatrixList) {
			i.print();
			System.out.println("");
		}
	}
	
	public Matrix clone() {
		Matrix c = new Matrix(this.getDim1(),this.getDim2());
		for(int i=0;i<c.dim1;i++) {
			for(int j=0;j<c.dim2;j++) {
				c.setItem(i, j, this.getItem(i, j));
			}
		}
		return c;
	}
	

}
