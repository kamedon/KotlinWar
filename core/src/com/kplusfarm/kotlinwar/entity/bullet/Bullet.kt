package com.kplusfarm.kotlinwar.entity.bullet

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage

/**
 * Created by kamedon on 7/27/15.
 */
open class Bullet(image: WarUnitImage) : GameObject(image) {
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
        }

    }
}
