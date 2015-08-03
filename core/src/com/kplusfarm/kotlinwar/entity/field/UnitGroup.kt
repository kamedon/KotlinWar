package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.Gdx
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

    val kdMap : KdMap get() = team.kdMap
    val cacheBullets: SnapshotArray<Bullet> = SnapshotArray()
    val cacheUnits: SnapshotArray<WarUnit> = SnapshotArray()
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

    fun collide(bullets: BulletGroup) {
        Gdx.app.log("collide","start")
        team.kdMap.collide(0, bullets.kdMap, GdxArray(cacheBullets.begin()), GdxArray(cacheUnits.begin()), callback);
        cacheBullets.end();
        cacheUnits.end();
    }
}
