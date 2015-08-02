package com.kplusfarm.kotlinwar.entity.weapon

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/28/15.
 */
public class BeamRifle(thumb: TextureAtlas.AtlasRegion, bullet: Bullet) : Weapon(thumb, bullet) {
    override fun createBullet(self: WarUnit, target: WarUnit): Bullet {
        val b = Bullet(bullet.image)
        b.setPosition(self.centerX, self.centerY)
        b.to = Vector2(target.centerX, target.centerY)
        return b
    }
}