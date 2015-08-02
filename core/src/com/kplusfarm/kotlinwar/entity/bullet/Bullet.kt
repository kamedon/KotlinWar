package com.kplusfarm.kotlinwar.entity.bullet

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pools
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/27/15.
 */
open class Bullet() : GameObject() {
    val velocity: Float = 5f
    var degree: Float = 0f;

    var to: Vector2? = null
        set(value) {
            $to = value
            value?.let {
                degree = MathUtils.atan2(it.y - centerY, it.x - centerX)
            }
        }

    override fun act(delta: Float) {
        super.act(delta)
        to?.let {
            moveBy(velocity * MathUtils.cos(degree), velocity * MathUtils.sin(degree))
        }

        if (runtime > 3f) {
            remove()
            Pools.free(this)
            setColor(Color.OLIVE)
        }

    }

    fun collide(warUnit: WarUnit): Boolean {
        return  false
    }
}
