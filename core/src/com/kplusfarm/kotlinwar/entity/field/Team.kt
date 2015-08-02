package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.scenes.scene2d.Group

/**
 * Created by kamedon on 8/2/15.
 */
public class Team(val code: TeamCode) : Group() {
    public val bulletGroup: BulletGroup
    public val unitGroup: UnitGroup

    init {
        bulletGroup = BulletGroup(this)
        unitGroup = UnitGroup(this)
        addActor(unitGroup)
        addActor(bulletGroup)
    }

}

enum class TeamCode {
    RED, GREEN
}

