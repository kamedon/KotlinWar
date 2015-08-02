package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Group
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.util.kdmap.KdMap

/**
 * Created by kamedon on 8/2/15.
 */
public class Team(val code: TeamCode,val kdMap: KdMap) : Group() {
   val bulletGroup: BulletGroup
   val unitGroup: UnitGroup


    init {
        bulletGroup = BulletGroup(this)
        unitGroup = UnitGroup(this)
        addActor(unitGroup)
        addActor(bulletGroup)
    }

    fun updateNode(gameObject: GameObject) {
        kdMap.update(gameObject)
    }


}

enum class TeamCode {
    RED, GREEN
}

