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
    var produceSpan: Float = 3f
    var produceTime: Float = 0f

    override fun act(delta: Float) {
        super.act(delta)
        produceTime += delta
        if (produceTime >= produceSpan) {
            produce()?.let {
                team?.add(it)
            }
            produceTime = 0f;
        }
    }

    abstract fun produce(): WarUnit?;

    override fun reset() {
        super.reset()
        produceUnit = null
    }

    override fun moveFor(target: WarUnit, delta: Float) {
    }
}