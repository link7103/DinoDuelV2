package com.dinoduel.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.dinoduel.game.Sprites.Dino;
import com.dinoduel.game.Sprites.InteractiveTileObject;
import com.dinoduel.game.Weapons.Bullet;
import com.dinoduel.game.Weapons.Gun;
import com.dinoduel.game.Weapons.Weapon;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        //Detects hitting a gun box
        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && object.getUserData() instanceof InteractiveTileObject) {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }

        //Bullet collision detection
        if ((fixA.getUserData() == "side" || fixB.getUserData() == "side") && (fixA.getUserData() == "bullet" || fixB.getUserData() == "bullet")) {
            Fixture side = fixA.getUserData() == "side" ? fixA : fixB;
            Fixture bullet = side == fixA ? fixB : fixA;

            ((Bullet) bullet.getUserData()).hit();
        }

        //pickuo detection
        if ((fixA.getUserData() == "gun" || fixB.getUserData() == "gun") && (fixA.getUserData() == "body" || fixB.getUserData() == "body")) {
            Fixture body = fixA.getUserData() == "gun" ? fixA : fixB;
            Fixture gun = body == fixA ? fixB : fixA;

            //((Dino) body.getUserData()).pickupGun(((Gun)gun));
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
