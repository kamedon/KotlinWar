package com.kplusfarm.kotlinwar.screen

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.utils.viewport.Viewport
import com.kplusfarm.kotlinwar.KotlinWar

/**
 * Created by kamedon on 7/25/15.
 */
open public class BaseScreen(val game: KotlinWar) : ScreenAdapter() {
    val asset: AssetManager get() = game.asset
    val uiViewPoint: Viewport get() = game.camera.uiViewport
    val gameViewPoint: Viewport get() = game.camera.gameViewport
    val gameCamera: Camera  get() = game.camera.gameCamera
    val uiCamera: Camera  get() = game.camera.uiCamera
    val width: Float get() = game.camera.width
    val height: Float get() = game.camera.height

    public fun setScreen(screen: BaseScreen) {
        game.setScreen(screen)
    }
}