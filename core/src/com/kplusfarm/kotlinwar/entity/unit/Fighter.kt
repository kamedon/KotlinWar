package com.kplusfarm.kotlinwar.entity.unit

import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage

/**
 * Created by kamedon on 8/2/15.
 */

class Fighter() : WarUnit() {
    private var dir = Vector2()

    override fun moveFor(target: WarUnit, delta: Float) {
        val x = getX()
        val y = getY()
        val r = Vector2(target.centerX - centerX, target.centerY - centerY)
        r.nor()
        dir = Vector2(dir.x * (0.5f - angle), dir.y * (0.5f - angle));
        dir = dir.add(r.x * angle, r.y * angle);
        dir.nor();
        moveBy(dir.x * velocity, dir.y * velocity)
        if (!inside()) {
            setPosition(x, y)
        }
        setRotation(dir.angle() - 90)
    }
}
