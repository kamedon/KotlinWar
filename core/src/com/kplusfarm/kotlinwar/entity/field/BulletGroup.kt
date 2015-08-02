package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.SnapshotArray
import com.kplusfarm.kotlinwar.entity.bullet.Bullet

/**
 * Created by kamedon on 8/2/15.
 */
public class BulletGroup(val team: Team) : Group() {
    public val bullets: SnapshotArray<Bullet> = SnapshotArray<Bullet>()

    public fun add(bullet: Bullet) {
        bullet.team = team
        addActor(bullet)
        bullets.add(bullet)
    }

}