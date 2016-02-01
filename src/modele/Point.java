package modele;

public class Point {

	private double x;
	private double y;

	public Point() {
		this.x = 0;
		this.y = 0;	
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void translaterX(double dx) {
		this.x += dx;
	}

	public void translaterY(double dy) {
		this.y += dy;
	}


	public void translater(double dx, double dy) {
		translaterX(dx);
		translaterY(dy);
	}
	
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point p){
		double d = Math.sqrt((x-p.getX())*(x-p.getX())+(y-p.getY())*(y-p.getY()));
		return d;
	}
}
