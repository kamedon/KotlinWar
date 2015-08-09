package com.kplusfarm.kotlinwar.entity.weapon

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Pool
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/28/15.
 */
abstract class Weapon(var region: TextureAtlas.AtlasRegion?, var bullet: Bullet?) : Pool.Poolable {
    var span: Float = 3f
    var r = 0f;

    fun shoot(self: WarUnit, target: WarUnit, delta: Float): Bullet? {
        r += delta
        if (r > span) {
            r = 0f
            return createBullet(self, target)
        }
        return null
    }

    abstract fun createBullet(self: WarUnit, target: WarUnit): Bullet

    override fun reset() {
        bullet = null
        region = null
        r = 0f
    }
}
