package com.kplusfarm.kotlinwar.entity.ship

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/27/15.
 */
public abstract class Ship() : WarUnit() {
    var produceUnit: WarUnit? = null

    override fun act(delta: Float) {
        super.act(delta)
        val unit = produce(delta)
        unit?.let {
            team?.add(it)
        }
    }

    abstract fun produce(delta: Float): WarUnit?;

    override fun reset() {
        super.reset()
        produceUnit = null
    }
}