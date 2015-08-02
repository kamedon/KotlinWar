package com.kplusfarm.kotlinwar.entity.unit

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage

/**
 * Created by kamedon on 8/2/15.
 */
class Humanoid(image: WarUnitImage) : WarUnit(image) {

    override fun moveFor(target: WarUnit, delta: Float) {
        val point = Vector2(target.centerX - centerX, target.centerY - centerY)
        val r = point.angleRad()
        val d = point.angle()
        moveBy(velocity * MathUtils.cos(r), velocity * MathUtils.sin(r))
        setRotation(d - 90)
    }

}