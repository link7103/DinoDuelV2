package com.dinoduel.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Screens.PlayScreen;
import com.dinoduel.game.Weapons.AK;
import com.dinoduel.game.Weapons.Barrett;
import com.dinoduel.game.Weapons.Mossberg;
import com.dinoduel.game.Weapons.PPK;
import com.dinoduel.game.Weapons.Weapon;

public class GunBox extends InteractiveTileObject {

    public PlayScreen screen;

    public GunBox(World world, TiledMap map, Rectangle bounds, PlayScreen screen) {
        super(world, map, bounds, screen);
        fixture.setUserData(this);
        this.screen = screen;


    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Gun Box", "Collision");
        screen.spawnWeapon(body.getPosition().x, body.getPosition().y+20+bounds.getHeight());
        //create random weapon

    }

}
