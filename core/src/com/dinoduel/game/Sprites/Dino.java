package com.dinoduel.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.dinoduel.game.DinoDuel;

public class Dino extends Sprite {
    public World world;
    public Body b2body;

    public Dino(World world) {
        this.world = world;
        defineDino();
    }//end constructor


    public void defineDino() {
        BodyDef bdef = new BodyDef();
        //starting postion. (Pass in?)
        bdef.position.set(32 / DinoDuel.PPM, 32 / DinoDuel.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / DinoDuel.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }//end defineDino
}//end Dino
