package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.SnapshotArray
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 8/2/15.
 */
class UnitGroup(val team: Team) : Group() {

    public val units: SnapshotArray<WarUnit> = SnapshotArray<WarUnit>()

    public fun add(unit: WarUnit) {
        unit.team = team
        addActor(unit)
        units.add(unit)
    }
}
