package com.kplusfarm.kotlinwar.entity

import com.kplusfarm.kotlinwar.entity.image.WarUnitImage

/**
 * Created by kamedon on 7/26/15.
 */
public class WarUnit(image : WarUnitImage) : GameObject(image) {

    override fun act(delta: Float) {
        super.act(delta)
    }
}