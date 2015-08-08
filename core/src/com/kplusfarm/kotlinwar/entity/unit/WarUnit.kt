package com.kplusfarm.kotlinwar.entity.unit

import com.badlogic.gdx.graphics.Color
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.weapon.Weapon

/**
 * Created by kamedon on 7/26/15.
 */
public abstract class WarUnit() : GameObject() {
    var target: WarUnit? = null
    var weapon: Weapon? = null;
    var velocity = 2f
    var angle = 0.3f

    override fun act(delta: Float) {
        super.act(delta)
        target?.let {
            it.dead ?: return
            moveFor(it, delta)
            weapon?.shoot(this, it, delta)?.let {
                team?.add(it)
            }
        }
    }

    abstract fun moveFor(target: WarUnit, delta: Float)

    fun hit(bullet : Bullet){
        setColor(Color.BLUE)
    }

}
