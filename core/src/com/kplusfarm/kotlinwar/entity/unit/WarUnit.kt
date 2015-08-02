package com.kplusfarm.kotlinwar.entity.unit

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.weapon.Weapon

/**
 * Created by kamedon on 7/26/15.
 */
public abstract class WarUnit(image: WarUnitImage) : GameObject(image) {
    var target: WarUnit? = null
    var weapon: Weapon? = null;
    var velocity = 2f
    var angle = 0.3f


    override fun act(delta: Float) {
        super.act(delta)
        target?.let {
            moveFor(it, delta)
            weapon?.shoot(this, it, delta)?.let {
                getParent().addActor(it)
            }
        }
    }

    abstract fun moveFor(target: WarUnit, delta: Float)

}
