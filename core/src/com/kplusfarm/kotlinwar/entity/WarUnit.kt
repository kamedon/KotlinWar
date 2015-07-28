package com.kplusfarm.kotlinwar.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.Bullet.Bullet
import com.kplusfarm.kotlinwar.entity.image.WarUnitImage
import com.kplusfarm.kotlinwar.entity.weapon.Weapon

/**
 * Created by kamedon on 7/26/15.
 */
public open class WarUnit(image: WarUnitImage) : GameObject(image) {
    var target: WarUnit? = null
    var weapon: Weapon? = null;
    var velocity = 2f
    var angle = 0.3f

    private var dir = Vector2()
    override fun act(delta: Float) {
        super.act(delta)
        target?.let {
            val r = Vector2(it.centerX - centerX, it.centerY - centerY)
            r.nor()
            dir = Vector2(dir.x * (1.0f - angle), dir.y * (1.0f - angle));
            dir = dir.add(r.x * angle, r.y * angle);
            dir.nor();
            moveBy(dir.x * velocity, dir.y * velocity)
            setRotation(dir.angle() - 90)
            if (hasWeapon()) {
                weapon?.shoot(this, it, delta)?.let {
                    getParent().addActor(it)
                }
            }
        }
    }

    fun hasWeapon() = weapon != null
}
