package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Group
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit
import com.kplusfarm.kotlinwar.util.kdmap.KdMap

/**
 * Created by kamedon on 8/2/15.
 */
public class Team(val code: TeamCode, val kdMap: KdMap) : Group() {
    val bulletGroup: BulletGroup
    val unitGroup: UnitGroup
    val unitSize: Int get() = unitGroup.getChildren().size
    val bulletSize: Int get() = bulletGroup.getChildren().size

    val rect: Rectangle

    init {
        bulletGroup = BulletGroup(this)
        unitGroup = UnitGroup(this)
        addActor(unitGroup)
        addActor(bulletGroup)
        rect = Rectangle(0f, 0f, kdMap.width.toFloat(), kdMap.height.toFloat())
    }


    fun updateNode(gameObject: GameObject) {
        kdMap.update(gameObject)
    }

    fun add(unit: WarUnit) {
        unit.team = this
        unitGroup.add(unit)
    }

    fun add(bullet: Bullet) {
        bullet.team = this
        bulletGroup.add(bullet)
    }

    fun getUnit(i: Int): WarUnit {
        return unitGroup.get(i)
    }

    fun getBullet(i: Int): Bullet {
        return bulletGroup.get(i)
    }

    fun dead() {
        unitGroup.dead()
        bulletGroup.dead()
    }


}

enum class TeamCode {
    RED, GREEN
}

