package com.comp4920.dbl.screens;

import javax.swing.SpringLayout;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.comp4920.dbl.helpers.AssetLoader;

public class SplashScreen implements Screen {
	private Image startButton;
	private Image quitButton;
	private Image instructionButton;
	private Image soundButton;
	
	private Game myGame;
	private Stage stage;
	private final int width = 600;
    private final int height = 800;
    private OrthographicCamera camera;
    private SpriteBatch batch;
	
	public SplashScreen(Game g) {
		Gdx.app.log("SplashScreen", "created");
		myGame = g;
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false, width, height);
		stage = new Stage(new FitViewport(width,height,camera));
		startButton = new Image(AssetLoader.startGameButton);
		quitButton = new Image(AssetLoader.quitButton);
		instructionButton = new Image(AssetLoader.instructionButton);
		soundButton = new Image(AssetLoader.soundEffectButton);
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(AssetLoader.startMenuBackground, 0, 0);
        batch.end();
        stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		stage.addActor(startButton);
		stage.addActor(quitButton); 
//		stage.addActor(instructionButton); 
		stage.addActor(soundButton);
		//stage.addActor(instructionButton); 

//		instructionButton.setScale((float)0.5);
//		instructionButton.setPosition(70, 185);
		startButton.setPosition(70, 240);
		quitButton.setPosition(70, 120);
		soundButton.setPosition(490, 150);
		
		Gdx.input.setInputProcessor(stage);
		
		startButton.addListener(new InputListener() {
			@Override
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        return true;
		    }
			
		    @Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        myGame.setScreen(new GameScreen(myGame));
		    }
		});
		
		instructionButton.addListener(new InputListener() {
			@Override
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        return true;
		    }
			
		    @Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        //TODO: add instruction page
		    }
		});
		
		quitButton.addListener(new InputListener() {
			@Override
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        return true;
		    }
			
		    @Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        Gdx.app.exit();
		    }
		});
		
		soundButton.addListener(new InputListener() {
			@Override
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) 
		    {
		        return true;
		    }
			
		    @Override
		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) 
		    {
//		        Gdx.app.exit();
		    }
		});
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
