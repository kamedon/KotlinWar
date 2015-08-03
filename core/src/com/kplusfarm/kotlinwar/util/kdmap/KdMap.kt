package com.kplusfarm.kotlinwar.util.kdmap

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.utils.Array  as GdxArray
import com.kplusfarm.kotlinwar.entity.GameObject
import com.kplusfarm.kotlinwar.entity.bullet.Bullet
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 15/03/19.
 */
public class KdMap(public val width: Float, public val height: Float, public val level: Int) {
    public val spanX: Float
    public val spanY: Float
    public val count: Int
    private val line: Int
    private val mortonLine: Int
    private val mLevelCount: IntArray
    private val mMap: Array<MortonQueue>

    init {
        this.line = Math.pow(2.0, level.toDouble()).toInt()
        this.mortonLine = line - 1
        spanX = width / line
        spanY = height / line
        mLevelCount = IntArray(level + 2)
        for (i in 0..mLevelCount.count() - 1) {
            mLevelCount[i] = ((Math.pow(4.0, i.toDouble()) - 1) / 3).toInt()
        }
        //        mMap = Array(mLevelCount[mLevelCount.size() - 1])
        var l = 0
        var c = 0
        mMap = Array(mLevelCount[mLevelCount.size() - 1], {
            if (it >= mLevelCount[l]) {
                c = mLevelCount[l]
                ++l
            }
            val morton = it - c
            MortonQueue(l - 1, it, morton, this)
        })
        this.count = mMap.count()
    }

    public fun collide(index: Int, kdMap: KdMap, bullets: GdxArray<Bullet>, units: GdxArray<WarUnit>, callback: OnCollideCallback) {
        if (index >= mMap.count()) {
            return
        }
        mMap[index].collide(kdMap, bullets, units, callback)
    }

    public fun add(unit: GameObject) {
        val index = calcIndex(unit)
        mMap[index].add(unit)
    }

    public fun cellPositionX(x: Float): Int {
        return Math.max(0f, Math.min(mortonLine.toFloat(), x)).toInt()
    }

    public fun cellPositionY(y: Float): Int {
        return Math.max(0f, Math.min(mortonLine.toFloat(), y)).toInt()
    }

    public fun calcCellPositionY(y: Float): Int {
        return cellPositionY(y / spanY)
    }

    public fun calcCellPositionX(x: Float): Int {
        return cellPositionX(x / spanX)
    }


    public fun calcMorton(x: Float, y: Float): Int {
        return BitSeparate32.calc2DMortonNumber2(calcCellPositionX(x), calcCellPositionY(y))
    }

    jvmOverloads public fun mortonIndex(morton: Int, level: Int = this.level): Int {
        return mLevelCount[level] + morton
    }

    public fun calcIndex(warObject: GameObject): Int {
        val mortonBottom = calcMorton(warObject.getX(), warObject.getY())
        val mortonTop = calcMorton(warObject.getRight(), warObject.getTop())
        if (mortonBottom == mortonTop) {
            return mLevelCount[level] + mortonBottom
        }
        val xor = mortonBottom xor mortonTop
        val upLevel = upLevelMortonNumber(xor, 0, 0)
        val level = this.level - upLevel
        val morton = mortonBottom shr (upLevel * 2)
        //        Gdx.app.log("kdmap", "index" + mLevelCount[level] + morton);
        return mLevelCount[level] + morton
    }

    private fun upLevelMortonNumber(xor: Int, upParent: Int, shiftCount: Int): Int {
        if (xor == 0) {
            return upParent
        } else {
            if ((xor and 3) > 0) {
                return upLevelMortonNumber((xor shr 2), shiftCount + 1, shiftCount + 1)
            } else {
                return upLevelMortonNumber((xor shr 2), upParent, shiftCount + 1)
            }
        }

    }


    public fun update(gameObject: GameObject) {
        val id = calcIndex(gameObject)
        if (gameObject.node?.getIndex() !== id) {
            gameObject.node?.move(mMap[id])
        }
    }

    public fun getLevelCount(level: Int): Int {
        return mLevelCount[level]
    }

    public fun debug(combined: Matrix4) {
        KdMapRender.render(this, combined)
    }

    public fun getQueue(index: Int): MortonQueue {
        return mMap[index]
    }

    public interface OnCollideCallback {
        public fun onCollide(bullet: Bullet, unit: WarUnit, colliding: Boolean)
    }

    public interface OnNearestCallback {
        public fun isTargetUnit(unit: WarUnit, gameObject: GameObject): Boolean

    }

}


