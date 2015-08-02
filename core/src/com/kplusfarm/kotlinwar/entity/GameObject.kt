package com.kplusfarm.kotlinwar.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Pool
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage

/**
 * Created by kamedon on 7/26/15.
 */
abstract public class GameObject() : Actor(), Pool.Poolable {

    protected var runtime: Float = 0f

    var image: WarUnitImage? = null
    var region: TextureRegion? = null

    val centerX: Float get() = getX() + getOriginX()
    val centerY: Float get() = getY() + getOriginY()

    open public fun active() {
        sizeChanged()
        positionChanged()
    }

    override fun sizeChanged() {
        image?.let {
            val img = it.acts.getKeyFrame(0f)
            setSize(img.getRegionWidth().toFloat(), img.getRegionHeight().toFloat())
            setOrigin(img.getRegionWidth() / 2f, img.getRegionHeight() / 2f)
        }

    }

    override fun act(delta: Float) {
        super<Actor>.act(delta)
        runtime += delta
        region = image?.acts?.getKeyFrame(runtime, true)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        region ?: return
        batch?.let {
            val color = getColor();
            it.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            it.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }


    override fun reset() {
        image = null
        region = null
        runtime = 0f;
    }
}


