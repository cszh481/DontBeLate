package com.comp4920.dbl.gameobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lane implements Comparable<Lane>{
	public static final int LANE_MAX_NUM_OBSTACLES = 2;
	private int positionX; //for determining x position of car
	private int maxSpeed; //max speed so far
	private int maxNumObstacles;
	
	private List<Obstacle> obstacles;
	
	public Lane (int positionX){
		this.positionX = positionX;
		obstacles = new ArrayList<Obstacle>();
		this.maxSpeed = Car.MAX_CAR_SPEED;// FIRST CAR'S MAX SPEED;
		this.maxNumObstacles = LANE_MAX_NUM_OBSTACLES;
	}
	
	public boolean canAddObstacle (){
		return (obstacles.size() <= maxNumObstacles);
	}
	
	public void addObstacle (){
		//check max speed and set car speed to that
		Obstacle newObstacle = new Car(positionX,maxSpeed);
		if(newObstacle.getVerticalSpeed() < maxSpeed){
			maxSpeed = (int) newObstacle.getVerticalSpeed(); //TODO: Issue with speed being float or int
		}
		obstacles.add(newObstacle);
	}
	
	public void addRW (){
		//check max speed and set car speed to that
		Obstacle roadwork = new Roadwork(positionX);
		if(roadwork.getVerticalSpeed() < maxSpeed){
			maxSpeed = (int) roadwork.getVerticalSpeed(); //TODO: Issue with speed being float or int
		}
		obstacles.add(roadwork);
	}
	
	public void addObstacle (Obstacle obstacle){
		if(obstacle.getVerticalSpeed() < maxSpeed){
			maxSpeed = (int) obstacle.getVerticalSpeed(); //TODO: Issue with speed being float or int
		}
		obstacles.add(obstacle);
	}
	
	//we need to check if a car has gone off the edge
	//of the screen and remove it, and spawn a new car if needed.
	public void checkObstacleBounds (){
		for (Iterator<Obstacle> iter = obstacles.iterator(); iter.hasNext(); ){
			Obstacle obstacle = iter.next();
			if (obstacle.offScreen()) {
				iter.remove();
			}
		}
		//we need to check if there are no cars on the lane, if so set maxSpeed to normal max
		if(getNumObstacles() == 0){
			maxSpeed = Car.MAX_CAR_SPEED;
		}
				
	}
	/**
	 * Update all the car positions
	 * @param delta
	 */
	
	public void update(float delta) {
		
		//update each obstacle
		for (Obstacle obstacle : obstacles){
			obstacle.update(delta);
		}
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public int getNumObstacles(){
		return obstacles.size();
	}
	
	public int getMaxSpeed(){
		return maxSpeed;
	}
	
	public int getXPosition(){
		return positionX;
	}

	public int getMaxObstacles() {
		return maxNumObstacles;
	}
	
	@Override
	public int compareTo(Lane otherLane) {
		return (obstacles.size() - otherLane.getNumObstacles());
	}
}
