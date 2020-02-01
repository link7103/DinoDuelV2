package com.dinoduel.game.Weapons;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Screens.PlayScreen;

import java.util.ArrayList;

public abstract class Gun extends Sprite implements Weapon  {
    public Body wBody;
    public World world;
    private TextureRegion img;
    private int ammo;
    private int magCap;
    ArrayList<Bullet> mag = new ArrayList<Bullet>();
    private int speed;
    private int duration;
    private int damage;
    public int x;
    public int y;
    private int xSize;
    private int ySize;

    public Gun (int x, int y, World world, PlayScreen screen) {
        super();
        this.x = x;
        this.y = y;
        this.world = world;
    }


    public void defineWeapon(int instruction) {
        BodyDef bdef = new BodyDef();

        if (instruction == 0) {

            bdef.position.set(x / DinoDuel.PPM, y / DinoDuel.PPM);
            bdef.type = BodyDef.BodyType.DynamicBody;
            wBody = world.createBody(bdef);

            FixtureDef fdef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(xSize / DinoDuel.PPM, ySize / DinoDuel.PPM);

            fdef.shape = shape;
            wBody.createFixture(fdef);
        }
    }
}
