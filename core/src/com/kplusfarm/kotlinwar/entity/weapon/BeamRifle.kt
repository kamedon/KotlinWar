package com.kplusfarm.kotlinwar.entity.weapon

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pools
import com.kplusfarm.kotlinwar.entity.bullet.Beam
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/28/15.
 */
public class BeamRifle(region: TextureAtlas.AtlasRegion?, bullet: Bullet?) : Weapon(region, bullet) {

    override fun createBullet(self: WarUnit, target: WarUnit): Bullet {
        val b = Pools.obtain(javaClass<Beam>())
        b.image = bullet?.image
        b.setPosition(self.centerX, self.centerY)
        b.to = Vector2(target.centerX, target.centerY)
        b.active()
        return b
    }

}