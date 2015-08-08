package com.kplusfarm.kotlinwar.util.kdmap

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 15/03/19.
 */
public class MortonNode(public var mortonQueue: MortonQueue, public var gameObject: GameObject) {
    public var next: MortonNode? = null
    public var prev: MortonNode? = null

    synchronized public fun remove() {
        next?.let {
            it.prev = prev
        }

        prev?.let {
            it.next = next
        }

        if (mortonQueue.firstNode === this) {
            mortonQueue.firstNode = next
        }
        if (mortonQueue.lastNode === this) {
            mortonQueue.lastNode = prev
        }
        prev = null
        next = null
    }

    synchronized public fun move(queue: MortonQueue) {
        remove()
        queue.add(this)
        mortonQueue = queue
    }

    public fun collide(bullets: Array<Bullet>, units: Array<WarUnit>, callback: KdMap.OnCollideCallback) {
        Gdx.app.log("collide", "start")
        if (gameObject is WarUnit) {
            var unit = gameObject as WarUnit;
            for (i in 0..bullets.size - 1) {
                bullets.items[i]?.let {
                    if (it.alive && gameObject.alive) {
                        callback.onCollide(it, unit, it.collide(unit))
                    }
                }
            }
            if (gameObject.alive) {
                units.add(unit)
            }

        } else if (gameObject is Bullet) {
            val bullet = gameObject as Bullet
            for (i in  0..units.size - 1) {
                units.items[i]?.let {
                    if ( it.alive && bullet.alive) {
                        callback.onCollide(bullet, it, bullet.collide(it))
                    }
                }
            }
            if (bullet.alive) {
                bullets.add(bullet)
            }
        }
        next?.collide(bullets, units, callback)
    }

    val index: Int get() = mortonQueue.index

}
