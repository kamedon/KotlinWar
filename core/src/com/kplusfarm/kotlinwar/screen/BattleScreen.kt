package com.kplusfarm.kotlinwar.screen;

import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.kplusfarm.kotlinwar.KotlinWar
import com.kplusfarm.kotlinwar.service.AssetsLoader
import com.kplusfarm.kotlinwar.service.field.Field001Builder
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/26/15.
 */
public class BattleScreen(game: KotlinWar) : BaseScreen(game) {
    private val batch = SpriteBatch();
    private var stage: Stage by Delegates.notNull()
    private var uiStage: Stage by Delegates.notNull()
    private var fpsLogger: FPSLogger by Delegates.notNull()
    private var bg: Sprite by Delegates.notNull()

    override fun show() {
        fpsLogger = FPSLogger()

        val assetsLoader = AssetsLoader(asset)
        assetsLoader.loadBg()
        assetsLoader.loadUnits()
        asset.finishLoading()

        val builder = Field001Builder(width, height, 3, gameViewPoint, assetsLoader)
        stage = builder.build()
        uiStage = Stage(uiViewPoint, batch)

        bg = Sprite(assetsLoader.getBg());

    }

    override fun render(delta: Float) {
        gameCamera.update()
        uiCamera.update()
        batch.setProjectionMatrix(uiCamera.combined)
        if (asset.update()) {
            batch.begin()
            batch.draw(bg, 0f, 0f)
            batch.end()
            stage.act(delta)
            stage.draw()
        }
        fpsLogger.log()
    }

    override fun hide() {
        stage.dispose()
        uiStage.dispose()
        asset.clear()
        batch.dispose()
    }
}
