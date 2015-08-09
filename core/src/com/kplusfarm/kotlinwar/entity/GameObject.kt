package com.kplusfarm.kotlinwar.entity

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Pool
import com.badlogic.gdx.utils.Pools
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.field.Team
import com.kplusfarm.kotlinwar.util.kdmap.MortonNode


/**
 * Created by kamedon on 7/26/15.
 */
abstract public class GameObject() : Actor(), Pool.Poolable {

    protected var runtime: Float = 0f
    //
    var image: WarUnitImage? = null
    var region: TextureRegion? = null
    var circle: Circle = Circle()

    var team: Team? = null

    //
    val centerX: Float get() = circle.x
    val centerY: Float get() = circle.y
    val radius: Float get() = circle.radius

    //
    val alive: Boolean get() = !dead
    var dead = false

    //
    var node: MortonNode? = null

    open public fun active() {
        dead = false
        sizeChanged()
        positionChanged()
    }

    override fun sizeChanged() {
        image?.let {
            val img = it.acts.getKeyFrame(0f)
            setSize(img.getRegionWidth().toFloat(), img.getRegionHeight().toFloat())
            setOrigin(img.getRegionWidth() / 2f, img.getRegionHeight() / 2f)
            circle.radius = getWidth() / 2
            updateNode();
        }

    }

    override fun positionChanged() {
        circle.x = getX() + getOriginX()
        circle.y = getY() + getOriginY()
        updateNode()
    }

    private fun updateNode() {
        if (node != null) {
            team?.updateNode(this);
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

    fun removeRun() {
        Pools.free(this)
        setColor(Color.OLIVE)
        node?.remove();
        node?.destory()
    }


    override fun reset() {
        dead = true
        image = null
        region = null
        runtime = 0f;
        node = null
        team = null
    }

}


