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
	
	// does not take an x or y coord
	// x is generated by getRandomX
	// y is the same every time
	public Car() {
		this.width = 40;
		this.height = 40;
		int x = getRandomX();
		int y = height/2;
		this.position = new Vector2(x, y);
		velocity = new Vector2(0, 20);
        acceleration = new Vector2(0, 100);
	}
	
	public void update(float delta) {
		position.y += delta*100;       
    }
	
	
	// returns a random starting x-coord
	//TODO: 'assign' columns to cars so they never overlap - maybe 'lanes'?
	public int getRandomX() {
		int min = width/2;
		int max = Gdx.graphics.getWidth()/2 - width/2;
		
		Random rand = new Random();
		int randomX = rand.nextInt((max - min) + 1) + min;
		
		return randomX;
	}
	
	// Returns true if the coords of the car are offscreen.
	public boolean offScreen() {
		int screenHeight = Gdx.graphics.getHeight();
		return (this.getY()-this.height/2 > screenHeight/2);
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
