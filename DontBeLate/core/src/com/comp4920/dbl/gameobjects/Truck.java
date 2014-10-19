package com.comp4920.dbl.gameobjects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.comp4920.dbl.gameobjects.Car.CarColour;
import com.comp4920.dbl.helpers.AssetLoader;

public class Truck implements Obstacle{
	public static final int MAX_TRUCK_SPEED = 280;
	public static final int MIN_TRUCK_SPEED = 250;

	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;

	private Animation truckAnimation;
	
	protected Rectangle boundingRectangle;
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 80;
	
	protected static final int TRUCK_WIDTH = 36;
	protected static final int TRUCK_HEIGHT = 90;	

	protected static boolean randomStartSpeed = true;
	private boolean stopped;
	private int maxSpeed;
	private int minSpeed;

	private boolean isHit = false;
	protected int defaultSpeed = 100;	//used if randomStartSpeed = false
	
	// does not take an x or y coord
	// x is generated by getRandomX
	// y is the same every time
	public Truck() {
		stopped = false;
        maxSpeed = MAX_TRUCK_SPEED;
        minSpeed = MIN_TRUCK_SPEED;
		int x = 10;
		int y = 400;
		this.position = new Vector2(x, y);
		velocity = new Vector2(0, 250);
        acceleration = new Vector2(0, 100);
        velocity.y = genStartSpeed();
        boundingRectangle = new Rectangle();
        truckAnimation = AssetLoader.truckAnimation;
	}
	
	
	//generates a car with a minimum speed with default colour
	public Truck(int x_position, int minSpeed) {
		maxSpeed = MAX_TRUCK_SPEED;
        minSpeed = MIN_TRUCK_SPEED;
		int y = 400;
		this.position = new Vector2(x_position, y);
		velocity = new Vector2(0, 250);
        acceleration = new Vector2(0, 100);
        velocity.y = genStartSpeed();
        boundingRectangle = new Rectangle();
        truckAnimation = AssetLoader.truckAnimation;
	}
	
	public void update(float delta) {
		if(!stopped){
			position.y -= delta*(-velocity.y + (Road.getRoadSpeed()));
			boundingRectangle.set(position.x, position.y, TRUCK_WIDTH, TRUCK_HEIGHT);	//TODO: check these numbers
		}
			
	}
	
	// returns a random starting x-coord
	//TODO: 'assign' columns to cars so they never overlap - maybe 'lanes'?
	// Cars do not generate own starting position anymore. Determined by which lane it belongs to.
	public int getStartX() {
		//Random rand = new Random();
		//int randomX = rand.nextInt((max - min) + 1) + min;
		//TODO: How to use this unused method?
		return 10;
	}
	
	
	public int genStartSpeed() {
		int speed = defaultSpeed;
		if (randomStartSpeed) {
			Random rand = new Random();
			speed = rand.nextInt((maxSpeed - minSpeed) + 1) + minSpeed;
		}
		return speed;
	}
	
	// Returns true if the coords of the car are offscreen.
	public boolean offScreen() {
		return (this.getY() < -HEIGHT || this.getY() > 1000); 
		//to prevent car popping out although cars now stay offscreen for a while
	}
	
	public void stop() {
		stopped = true;
	}
	
	public void start() {
		stopped = false;
	}
		
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return TRUCK_WIDTH;
    }

    public float getHeight() {
        return TRUCK_HEIGHT;
    }

    public Rectangle getHitBox() {
    	return boundingRectangle;
    }
    
    public float getVerticalSpeed(){
    	return velocity.y;
    }

    public Animation getAnimation() {
    	return truckAnimation;
    }
    
    public void hit(){
    	System.out.println("hit!");
    	isHit = true;
    }
    
    public boolean isHit(){
    	return isHit;
    }
    
    public void setSpeed(float speed) {
    	this.velocity.y = speed;
    }
}
