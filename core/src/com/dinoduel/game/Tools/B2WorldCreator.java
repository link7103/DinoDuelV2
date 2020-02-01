package com.dinoduel.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Sprites.GunBox;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        //the first get(x); x = layer number in tiled counting from bottom up starting at 0
        //Ground layer
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / DinoDuel.PPM, (rect.getY() + rect.getHeight() / 2) / DinoDuel.PPM);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2 / DinoDuel.PPM, rect.getHeight() / 2 / DinoDuel.PPM);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

        //Guns 5
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rect.getX() + rect.getWidth() / 2) / DinoDuel.PPM, (rect.getY() + rect.getHeight() / 2) / DinoDuel.PPM);

            body = world.createBody(bDef);

            shape.setAsBox(rect.getWidth() / 2 / DinoDuel.PPM, rect.getHeight() / 2 / DinoDuel.PPM);
            fDef.shape = shape;
            body.createFixture(fDef);
        }
        //GunBox 6
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)
        ) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new GunBox(world, map, rect);
        }

    }
}
