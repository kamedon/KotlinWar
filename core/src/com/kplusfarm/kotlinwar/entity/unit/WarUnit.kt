package com.kplusfarm.kotlinwar.entity.unit

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.utils.Pools
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.weapon.Weapon

/**
 * Created by kamedon on 7/26/15.
 */
public abstract class WarUnit() : GameObject() {
    var target: WarUnit? = null
        set(value) {
            $target = value
            nearTime = 0f
        }
    var weapon: Weapon? = null;
    var hp = 100f
    var velocity = 2f
    var angle = 0.3f
    var nearTime: Float = 1f;
    private var nearSpan: Float = 0f;

    override fun act(delta: Float) {
        super.act(delta)
        target?.let {
            if (it.alive) {
                moveFor(it, delta)
                weapon?.shoot(this, it, delta)?.add(team)
            } else {
                target = null
            }
        }
    }

    abstract fun moveFor(target: WarUnit, delta: Float)

    fun inside(): Boolean {
        return Intersector.overlaps(circle, team!!.rect)
    }

    fun hit(bullet: Bullet) {
        hp -= bullet.attack;
        if (hp <= 0) {
            die();
        }
    }

    private fun die() {
        dead = true
    }

    fun copy(): WarUnit {
        val unit = Pools.obtain(this.javaClass);
        unit.image = image
        unit.hp = hp
        unit.setColor(getColor())
        unit.weapon = weapon
        unit.active()
        return unit
    }

    override fun reset() {
        super.reset()
        weapon = null
        target = null
    }

    fun needNear(): Boolean {
        return target == null || nearTime >= nearSpan
    }

    fun nearRuntime(delta: Float) {
        nearTime += delta
    }
}
