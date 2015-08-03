package com.kplusfarm.kotlinwar.util.kdmap

import com.badlogic.gdx.utils.Array as GdxArray
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 15/03/19.
 */
public class MortonQueue(public val level: Int, public val index: Int, public val morton: Int, private val mMap: KdMap) {
    public var firstNode: MortonNode? = null
    public var lastNode: MortonNode? = null
    private val mCacheBullets = GdxArray<Bullet>(javaClass<Bullet>())
    private val mCacheUnits = GdxArray<WarUnit>(javaClass<WarUnit>())

    public fun add(node: MortonNode) {
        if (firstNode == null) {
            firstNode = node
        } else {
            lastNode!!.next = node
            node.prev = lastNode
        }
        lastNode = node
    }

    public fun add(warObject: GameObject) {
        val node = MortonNode(this, warObject)
        add(node)
        warObject.node = node
    }

    public fun collide(kdMap: KdMap, bullets: GdxArray<Bullet>, units: GdxArray<WarUnit>, callback: KdMap.OnCollideCallback) {
        mCacheBullets.addAll(bullets)
        mCacheUnits.addAll(units)

        collide(mCacheBullets, mCacheUnits, callback)
        val q = kdMap.getQueue(index)
        q.collide(mCacheBullets, mCacheUnits, callback)
        //
        val start = (morton shl 2) + mMap.getLevelCount(level + 1)
        for (i in 0..3) {
            val next = start + i
            if (mMap.count >= next) {
                mMap.collide(next, kdMap, mCacheBullets, mCacheUnits, callback)
            }
        }
        mCacheBullets.clear()
        mCacheUnits.clear()

    }

    private fun collide(bullets: GdxArray<Bullet>, units: GdxArray<WarUnit>, callback: KdMap.OnCollideCallback) {
        if (hasFirstNode()) {
            firstNode!!.collide(bullets, units, callback)
        }
    }

    public fun hasFirstNode(): Boolean {
        return firstNode != null
    }

    public fun lastNode(): Boolean {
        return lastNode != null
    }

}
