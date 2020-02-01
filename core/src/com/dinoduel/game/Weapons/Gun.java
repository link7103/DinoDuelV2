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
    public World world;
    public Body wBody;
    protected TextureRegion img;
    protected int ammo;
    protected int magCap;
    ArrayList<Bullet> mag = new ArrayList<Bullet>();
    protected int speed;
    protected int duration;
    protected int damage;
    protected int x;
    public int y;
    protected int xSize;
    protected int ySize;
    protected boolean right;

    public Gun (int x, int y, World world, PlayScreen screen) {
        super(screen.getweaponAtlas().findRegion("guns"));
        this.x = x;
        this.y = y;
        this.world = world;
        right = true;


    }




    public void update() {

        //based off dino update class, unsure if it works. Should move it with a user if it has one.
        if (user != null) {
            wBody.setLinearVelocity(user.b2body.getLinearVelocity());
            setPosition(user.b2body.getPosition().x, user.b2body.getPosition().y);
            setRegion(getFrame());
        } else {
            setPosition(wBody.getPosition().x, wBody.getPosition().y);
        }


    }

    public TextureRegion getFrame() {
        TextureRegion region = img;
        if ((user.b2body.getLinearVelocity().x < 0 || !right) && !region.isFlipX()) {
            region.flip(true, false);
            right = false;
        } else if ((user.b2body.getLinearVelocity().x > 0 || right) && region.isFlipX()) {
            region.flip(true, false);
            right = true;
        }

        return region;
    }
}
