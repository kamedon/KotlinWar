package com.kplusfarm.kotlinwar.entity.ship

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 7/27/15.
 */
public class Ship(image: WarUnitImage) : WarUnit(image) {
    override fun moveFor(target: WarUnit, delta: Float) {
    }

    override fun act(delta: Float) {
        super.act(delta)
//        Gdx.app.log("ship", centerX.toString() + "/" + centerY)
    }
}