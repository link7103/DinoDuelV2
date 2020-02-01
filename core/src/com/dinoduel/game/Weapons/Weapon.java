package com.dinoduel.game.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.dinoduel.game.Sprites.Dino;

public interface Weapon {
    public Dino user = null;

    public void defineWeapon(int instruction);
    public TextureRegion getFrame();
    public void useWeapon();


}
