package com.kplusfarm.kotlinwar.entity.ship

import com.badlogic.gdx.utils.Pools
import com.kplusfarm.kotlinwar.entity.unit.Humanoid
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 8/8/15.
 */
public class TransportCraft() : Ship() {

    init {
        hp = 500f
        produceTime = 5f
    }

    override fun produce(): WarUnit? {
        return produceUnit?.copy()?.let {
            it.setPosition(getX(), getY())
            it
        }
    }


}
