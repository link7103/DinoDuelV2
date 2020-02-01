package com.dinoduel.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Screens.PlayScreen;

public class Dino extends Sprite {
    public World world;
    public Body b2body;
   //will need to pass in.
    private TextureRegion dinoIdle0;

    public Dino(World world, PlayScreen screen) {
        super (screen.getDinoAtlas().findRegion("DinoSprites - doux"));
        this.world = world;
        defineDino();
        dinoIdle0 = new TextureRegion(getTexture(), 0, 0, 24,24);
        setBounds(0,0,24 / DinoDuel.PPM, 24 / DinoDuel.PPM);
        setRegion(dinoIdle0);
    }//end constructor

    public void update (float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y + (float)0.025 - getHeight()/2);
    }//end update


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
