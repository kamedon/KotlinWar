package com.kplusfarm.kotlinwar.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.image.WarUnitImage

/**
 * Created by kamedon on 7/26/15.
 */
public open class WarUnit(image: WarUnitImage) : GameObject(image) {
    var target: WarUnit? = null
    val span: Float = 3f;
    var r: Float = 0f;

    override fun act(delta: Float) {
        super.act(delta)
        target?.let {
            r += delta;
            if (r >= span) {
                val b = Bullet(image)
                b.setPosition(centerX, centerY)
                b.to = Vector2(it.centerX, it.centerY)
                getParent().addActor(b)
                r = 0f
            }
            val degree = 180 * MathUtils.atan2(it.centerY - centerY, it.centerX - centerX) / Math.PI
            setRotation(degree.toFloat() - 90)
        }
    }
}