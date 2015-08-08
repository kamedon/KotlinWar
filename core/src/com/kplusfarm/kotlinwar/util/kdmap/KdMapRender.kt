package com.kplusfarm.kotlinwar.util.kdmap

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4

/**
 * Created by kamedon on 15/04/19.
 */
public object KdMapRender {


    public fun render(kdMap: KdMap, combined: Matrix4) {
        val shapeRenderer = ShapeRenderer()
        shapeRenderer.setProjectionMatrix(combined)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.setColor(1f, 1f, 1f, 1f)
        val line = Math.pow(2.0, kdMap.level.toDouble()).toInt()
        Gdx.gl20.glLineWidth(5f)
        shapeRenderer.setColor(1f, 1f, 1f, 1f)
        for (i in 0..line - 1) {
            shapeRenderer.line(i * kdMap.spanX, 0f, (i) * kdMap.spanX, kdMap.height.toFloat())
        }
        for (i in 0..line - 1) {
            shapeRenderer.line(0f, i * kdMap.spanY, kdMap.width.toFloat(), i * kdMap.spanY)
        }
        shapeRenderer.end()
        shapeRenderer.dispose()
    }
}
