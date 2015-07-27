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

    override fun act(delta: Float) {
        super.act(delta)
        target?.let {
            if (hasWeapon()) {
                val b = weapon?.shoot(this, it, delta)
                b?.let {
                    getParent().addActor(it)
                }
            }
            val degree = 180 * MathUtils.atan2(it.centerY - centerY, it.centerX - centerX) / Math.PI
            setRotation(degree.toFloat() - 90)
        }
    }

    fun hasWeapon() = weapon != null

}