package com.dinoduel.game.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dinoduel.game.DinoDuel;
import com.dinoduel.game.Screens.PlayScreen;

public class PPK extends Gun {
    public PPK(int x, int y, World world, PlayScreen screen) {

        super(x, y, world, screen);
        xSize = 16;
        ySize = 12;
        img = new TextureRegion(getTexture(), 18, 37, xSize, ySize);

        //unsure if necessary and will probably go int a method
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / DinoDuel.PPM, 32 / DinoDuel.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        wBody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(3 / DinoDuel.PPM, 8 / DinoDuel.PPM);

        fdef.shape = shape;

        fdef.filter.categoryBits = CATEGORY_WEAPON;
        fdef.filter.maskBits = MASK_WEAPON;
        wBody.createFixture(fdef);





        setBounds(0, 0, xSize / DinoDuel.PPM, ySize / DinoDuel.PPM);
        setRegion(img);
        setPosition(32/DinoDuel.PPM, 32/DinoDuel.PPM);
    }

    @Override
    public void useWeapon() {

    }
}
