package com.comp4920.dbl.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.comp4920.dbl.gameobjects.Bus;
import com.comp4920.dbl.gameobjects.Car;
import com.comp4920.dbl.helpers.InputHandler;

public class GameWorld {
	private Bus bus;	
	
	private List<Car> cars;
	private Car car;
	private int testing;
	
	public GameWorld(int midPointX) {
		bus = new Bus(midPointX, 330, 50, 60);
		cars = new ArrayList<Car>();
		cars.add(new Car());
	}
	
	public void update(float delta, InputHandler busInputHandler) {
		bus.update(delta, busInputHandler);
		for (Car car : cars) {
			car.update(delta);
		}
	}
	
	
	public Bus getBus() {
		return bus;
	}

	public List<Car> getCars() {
		return cars;
	}
}