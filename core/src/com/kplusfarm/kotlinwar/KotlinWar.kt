package com.kplusfarm.kotlinwar

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.kplusfarm.kotlinwar.screen.MainScreen
import com.kplusfarm.kotlinwar.service.CameraService
import kotlin.properties.Delegates

public class KotlinWar : Game() {
    private var camera: CameraService by Delegates.notNull()
    val gameCamera: Camera  get() = camera.gameCamera
    val uiCamera: Camera  get() = camera.uiCamera
    val width : Float get() = camera.width
    val height: Float get() = camera.height


    var asset: AssetManager by Delegates.notNull()

    override fun create() {
        asset = AssetManager()
        camera = CameraService(540f);


        setScreen(MainScreen(this))
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        super.render()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        camera.gameViewport.update(width, height)
        camera.uiViewport.update(width, height)
    }
}
