package com.kplusfarm.kotlinwar.util.kdmap

import com.badlogic.gdx.Game
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
        if (next != null) {
            next!!.prev = prev
        }
        if (prev != null) {
            prev!!.next = next
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
        callback.onCollideNode(this)
        if (gameObject is WarUnit) {
            var unit = gameObject as WarUnit;
            for (i in bullets.items.indices) {
                if (bullets.items[i] != null && bullets.items[i].alive && gameObject.alive) {
                    callback.onCollide(bullets.items[i], gameObject, bullets.items[i].collide(unit))
                }
            }
            if (gameObject.alive) {
                units.add(unit)
            }

        } else if (gameObject is Bullet) {
            val bullet = gameObject as Bullet
            for (i in units.items.indices) {
                if (units.items[i] != null && units.items[i].alive && bullet.alive) {
                    callback.onCollide(bullet, units.items[i], bullet.collide(units.items[i]))
                }
            }
            if (bullet.alive) {
                bullets.add(bullet)
            }
        }
        if (hasNext()) {
            next!!.collide(bullets, units, callback)
        }
    }

    public fun getIndex(): Int {
        return mortonQueue.index
    }

    public fun hasNext(): Boolean {
        return next != null
    }

}
