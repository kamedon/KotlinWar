package com.kplusfarm.kotlinwar.entity.ship

import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 8/8/15.
 */
public class TransportCraft() : Ship() {

    override fun produce(delta: Float): WarUnit? {
        return null
    }

    override fun moveFor(target: WarUnit, delta: Float) {
    }
}