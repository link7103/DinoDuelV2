package com.dinoduel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Scenes.Hud;

import java.security.Policy;

public class PlayScreen implements Screen {

    private DinoDuel game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(DinoDuel game) {
        this.game = game;
        //Camera that follows the players
        gameCam = new OrthographicCamera();
        //Fits the proper aspect ratio
        gamePort = new FitViewport(DinoDuel.V_WIDTH, DinoDuel.V_HEIGHT, gameCam);
        //Creates the hud
        hud = new Hud(game.batch);
        //Renders the map
        maploader = new TmxMapLoader();
        map = maploader.load("DinoDuel Basic Tilesets/testLevel.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        //Will be moved later into objects
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        //the first get(x); x = layer number in tiled counting from bottom up starting at 0
        //Ground layer
        for (MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)
             ) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

        //Guns 5
        for (MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }
        //GunBox 6
        for (MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rect.getX()+rect.getWidth()/2, rect.getY()+rect.getHeight()/2);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

    }
    @Override
    public void show() {

    }
//dt = delta time
    public void update(float dt) {
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }//end update

    private void handleInput(float dt) {
        if(Gdx.input.isTouched()){
            gameCam.position.x += 100 * dt;
        }
    }//end handleInput

    @Override
    public void render(float deltaTime) {
        update(deltaTime);

       //clears the game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //renderer our Box2DDebugLines
        b2dr.render(world, gameCam.combined);
        //sets the batch to draw what the camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
