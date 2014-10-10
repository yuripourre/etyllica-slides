package aula4.model;

import br.com.etyllica.layer.GeometricLayer;

public class Ball extends GeometricLayer {
	
	private double dx = 0;
	private double dy = 0;
	
	private double realX = 0;
	private double realY = 0;
	
	private int speed = 5;
	
	public Ball(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		realX = x;
		realY = y;
		
		dx = 1;
		dy = 0;
	}
	
	public void setAngle(double angle) {
				
	}
	
	public void update() {
				
		realX += dx*speed;
		realY += dy*speed;
		
		x = (int)realX;
		y = (int)realY;		
	}
		
}
