package com.kplusfarm.kotlinwar.entity

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.kplusfarm.kotlinwar.entity.image.WarUnitImage

/**
 * Created by kamedon on 7/26/15.
 */
open public class GameObject(val image: WarUnitImage) : Actor() {

    protected var runtime: Float = 0f

    private var region: TextureRegion? = null

    init {
        val img = image.acts.getKeyFrame(0f)
        setSize(img.getRegionWidth().toFloat(), img.getRegionHeight().toFloat())
    }

    override fun act(delta: Float) {
        super.act(delta)
        runtime += delta
        region = image.acts.getKeyFrame(runtime, true)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        region.let {
            val color = getColor();
            batch?.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            batch?.draw(it, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
