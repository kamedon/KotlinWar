package com.kplusfarm.kotlinwar.util.kdmap

import com.badlogic.gdx.math.Vector2
import com.kplusfarm.kotlinwar.entity.unit.WarUnit

/**
 * Created by kamedon on 15/03/18.
 */
public object BitSeparate32 {
    public fun separate2(n: Int): Int {
        var n = n
        n = (n or (n shl 4)) and 0x0f0f0f0f
        n = (n or (n shl 2)) and 0x33333333
        return (n or (n shl 1)) and 0x55555555
    }

    public fun separate3(n: Int): Int {
        var n = n
        n = (n or (n shl 8)) and 0x00ff00ff
        return separate2(n)
    }

    public fun calcPosition(span: Vector2, warObject: WarUnit): Vector2 {
        return Vector2(warObject.getX() / span.x, warObject.getY() / span.y)
    }


    public fun calc2DMortonNumber2(x: Int, y: Int): Int {
        return (separate2(x) or (separate2(y) shl 1))
    }

    public fun calc2DMortonNumber3(x: Int, y: Int): Int {
        return (separate3(x) or (separate3(y) shl 1))

    }

}
