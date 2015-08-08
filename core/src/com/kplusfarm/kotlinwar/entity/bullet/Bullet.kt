package com.kplusfarm.kotlinwar.entity.bullet

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/27/15.
 */
open class Bullet() : GameObject() {
    val attack: Float = 10f
    val velocity: Float = 5f
    var degree: Float = 0f;
    val deadTime: Float = 3f

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

        if (runtime >= deadTime) {
            remove()
            removeRun()
        }

    }


    fun collide(warUnit: WarUnit): Boolean {
        val b = team != warUnit.team && Vector2.len(warUnit.centerX - centerX, warUnit.centerY - centerY) < radius + warUnit.radius
        if(b){
            remove()
            removeRun()
        }
        return b;
    }
}
