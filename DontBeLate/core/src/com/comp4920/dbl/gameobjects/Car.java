package com.comp4920.dbl.gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.comp4920.dbl.helpers.InputHandler;

public class Car {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	
	private static int numCars;		// number of cars on the screen at any time
	private static int screenWidth;
	
	// does not take an x coord - this is generated by getRandomX
	public Car(float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.position = new Vector2(getRandomX(), y);
		velocity = new Vector2(0, 20);
        acceleration = new Vector2(0, 100);
	}
	
	public void update(float delta) {
		position.y += delta*100;       
    }
	
	
	// returns a starting x-coord 
	public int getRandomX() {
	
		int min = width/2;
		int max = Gdx.graphics.getWidth()/2 - width/2;
		
		Random rand = new Random();
		int randomX = rand.nextInt((max - min) + 1) + min;
		
		return randomX;
	}
	
	
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


}
