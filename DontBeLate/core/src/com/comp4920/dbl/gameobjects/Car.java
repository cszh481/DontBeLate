package com.comp4920.dbl.gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.comp4920.dbl.helpers.AssetLoader;

public class Car implements Obstacle{
	public static enum CarColour {
	    RED, REAL1, REAL2, REAL3, REAL4, REAL5, REAL6, REAL7
	}
	
	public static final int MAX_CAR_SPEED = 450;
	public static final int MIN_CAR_SPEED = 400;

	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;

	private Animation carAnimation;
	
	protected Rectangle boundingRectangle;
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 80;
	
	protected static final int CAR_WIDTH = 32;
	protected static final int CAR_HEIGHT = 70;	

	protected static boolean randomStartSpeed = true;
	private boolean stopped;
	private int maxSpeed;
	private int minSpeed;

	private boolean isHit = false;
	protected int defaultSpeed = 100;	//used if randomStartSpeed = false
	
	// does not take an x or y coord
	// x is generated by getRandomX
	// y is the same every time
	public Car() {
		stopped = false;
        maxSpeed = MAX_CAR_SPEED;
        minSpeed = MIN_CAR_SPEED;
		int x = 10;
		int y = 400;
		this.position = new Vector2(x, y);
		velocity = new Vector2(0, 20);
        acceleration = new Vector2(0, 100);
        velocity.y = genStartSpeed();
        boundingRectangle = new Rectangle();
        carAnimation = AssetLoader.carAnimation;
	}
	
	
	
	//generates a car with a minimum speed with default colour
	public Car(int x_position, int minSpeed) {
		maxSpeed = MAX_CAR_SPEED;
        this.minSpeed = minSpeed;
		int x = x_position;
		int y = 400;
		this.position = new Vector2(x, y);
		velocity = new Vector2(0, 20);
        acceleration = new Vector2(0, 100);
        velocity.y = genStartSpeed();
        boundingRectangle = new Rectangle();
        carAnimation = AssetLoader.carAnimation;
	}
	
	
	//generates a car with a minimum speed
	public Car(int x_position, int minSpeed, CarColour colour) {
		maxSpeed = MAX_CAR_SPEED;
        this.minSpeed = minSpeed;
		int x = x_position;
		int y = 400;
		this.position = new Vector2(x, y);
		velocity = new Vector2(0, 20);
        acceleration = new Vector2(0, 100);
        velocity.y = genStartSpeed();
        boundingRectangle = new Rectangle();
        switch (colour) {
        	case RED: carAnimation = AssetLoader.carAnimation; break;
        	case REAL1:carAnimation = AssetLoader.carAnimation1; break;
        	case REAL2:carAnimation = AssetLoader.carAnimation2; break;
        	case REAL3:carAnimation = AssetLoader.carAnimation3; break;
        	case REAL4:carAnimation = AssetLoader.carAnimation4; break;
        	case REAL5:carAnimation = AssetLoader.carAnimation5; break;
        	case REAL6:carAnimation = AssetLoader.carAnimation6; break;
        	case REAL7:carAnimation = AssetLoader.carAnimation7; break;
        }		
	}
	
	
	
	public void update(float delta) {
		if(!stopped){
			position.y -= delta*(-velocity.y + (Road.getRoadSpeed()));
			boundingRectangle.set(position.x, position.y, CAR_WIDTH, CAR_HEIGHT);	//TODO: check these numbers
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
        return CAR_WIDTH;
    }

    public float getHeight() {
        return CAR_HEIGHT;
    }

    public Rectangle getHitBox() {
    	return boundingRectangle;
    }
    
    public float getVerticalSpeed(){
    	return velocity.y;
    }

    public Animation getAnimation() {
    	return carAnimation;
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
