package com.kplusfarm.kotlinwar

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.kplusfarm.kotlinwar.screen.MainScreen
import com.kplusfarm.kotlinwar.service.CameraService
import kotlin.properties.Delegates

public class KotlinWar : Game() {
    var camera: CameraService by Delegates.notNull()

    override fun create() {
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
