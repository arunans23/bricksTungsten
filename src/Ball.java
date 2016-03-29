/**
 * Description	:Ball
 * Copyright	:Copyright (c) 2014
 * Company		:Embla Software Innovations (Pvt) Ltd
 * Created on	:2014.09.01
 * @author 		:Chandimal
 * @version 	:1.0
 */
public class Ball extends GameEntity {
	
	private int dx;		//>>
	private int dy;		//>>
	
	public Ball(int gameWidth, int gameHeight, int size, int startX, int startY, int speed){
		super(gameWidth, gameHeight, size, size, 250, 550, speed);
		this.dx = this.speed;		//>>
		this.dy = this.speed;		//>>
	}
	
	/**
	 * Update ball location
	 */
	public void update(){		
		x = x + dx;
		y = y + dy;
		
		if ( x + this.width >= gameWidth  ) {
			dx = -this.speed;
		}
		
		if ( x <= 0 ){
			dx = +this.speed;
		}
		
		if ( y + this.height  >= gameHeight ){			
			dy = -this.speed;
		}
		
		if ( y <= 0 ){
			dy = +this.speed;
		}	
	}
	
	public void reverse(){
		//dy = -this.speed;
		//dx = -dx;
		dy = -dy;
	}
	public void leftsidemove(){
		//dy = -this.speed;
		//dx = -dx;
		dx = dx - 3;
	}
        public void rightsidemove(){
		//dy = -this.speed;
		//dx = -dx;
		dx = dx + 3;
	}
	
}
