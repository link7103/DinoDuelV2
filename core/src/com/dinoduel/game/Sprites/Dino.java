package com.dinoduel.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Screens.PlayScreen;

public class Dino extends Sprite {


    public enum State { FALLING, JUMPING, STANDING, RUNNING, DUCKING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
   //will need to pass in.
    private TextureRegion dinoIdle0;
    private TextureRegion dinoDuck;

    private Animation<TextureRegion> dinoRun;
    private Animation<TextureRegion> dinoJump;
    private Animation<TextureRegion> dinoDuckRun;
    private float stateTimer;
    private boolean runningRight;

    public Dino(World world, PlayScreen screen) {
        super (screen.getDinoAtlas().findRegion("DinoSprites - doux"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 4; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), i * 24, 0, 24, 24));
        }
        dinoRun = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 11; i < 13; i++) {
            frames.add(new TextureRegion(getTexture(), i * 24, 0, 24, 24));
        }
        dinoJump  = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 18; i < 23; i++) {
            frames.add(new TextureRegion(getTexture(), i * 24, 0, 24, 24));
        }
        dinoDuckRun = new Animation(0.1f, frames);
        frames.clear();

        defineDino();
        dinoIdle0 = new TextureRegion(getTexture(), 0, 0, 24,24);
        dinoDuck = new TextureRegion(getTexture(), 17 * 24, 0, 24, 24);
        setBounds(0,0,24 / DinoDuel.PPM, 24 / DinoDuel.PPM);
        setRegion(dinoIdle0);
    }//end constructor

    public void update (float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y + (float)0.025 - getHeight()/2);
        setRegion(getFrame(dt));
    }//end update

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case JUMPING:
                region = dinoJump.getKeyFrame(stateTimer);
                break;

        }
    }

    public State getState() {
        if(b2body.getLinearVelocity().y>0 || (b2body.getLinearVelocity().y<0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if (b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
        //add ducking here
    }


    public void defineDino() {
        BodyDef bdef = new BodyDef();
        //starting postion. (Pass in?)
        bdef.position.set(32 / DinoDuel.PPM, 32 / DinoDuel.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / DinoDuel.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }//end defineDino
}//end Dino
