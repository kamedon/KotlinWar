package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.SnapshotArray
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit
import com.kplusfarm.kotlinwar.util.kdmap.KdMap
import com.badlogic.gdx.utils.Array as GdxArray

/**
 * Created by kamedon on 8/2/15.
 */
class UnitGroup(val team: Team) : Group() {

    val kdMap: KdMap get() = team.kdMap
    val cacheBullets: GdxArray<Bullet> = GdxArray()
    val cacheUnits: GdxArray<WarUnit> = GdxArray()
    val callback = object : KdMap.OnCollideCallback {

        override fun onCollide(bullet: Bullet, unit: WarUnit, colliding: Boolean) {
            if (colliding) {
                unit.hit(bullet);
            }
        }
    }


    public fun add(unit: WarUnit) {
        unit.team = team
        addActor(unit)
        kdMap.add(unit)
    }

    fun get(i: Int): WarUnit {
        return getChildren().get(i) as WarUnit;
    }

    fun collide(bullets: BulletGroup) {
        team.kdMap.collide(0, bullets.kdMap, cacheBullets, cacheUnits, callback);
    }

    fun dead() {
        val units = getChildren().begin();
        for (i in 0..units.size() - 1) {
            val unit = units[i] as WarUnit?
            if (unit != null && unit.dead) {
                unit.target = null
                unit.remove()
                unit.removeRun()
            }
        }
        getChildren().end()
    }
}
