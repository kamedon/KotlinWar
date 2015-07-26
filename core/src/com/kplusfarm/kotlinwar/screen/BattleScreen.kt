package com.kplusfarm.kotlinwar.screen;

import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.kplusfarm.kotlinwar.KotlinWar
import com.kplusfarm.kotlinwar.entity.WarUnit
import com.kplusfarm.kotlinwar.entity.image.WarUnitImage
import com.kplusfarm.kotlinwar.service.AssetsLoader
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/26/15.
 */
public class BattleScreen(game: KotlinWar) : BaseScreen(game) {
    var stage: Stage by Delegates.notNull()
    private var fpsLogger: FPSLogger by Delegates.notNull()

    override fun show() {
        fpsLogger = FPSLogger()

        val assetsLoader = AssetsLoader(asset)
        assetsLoader.loadBg()
        assetsLoader.loadUnits()
        asset.finishLoading()

        stage = Stage(gameViewPoint)
        val bg = assetsLoader.getBg()
        val bgImage = Image(bg)
        stage.addActor(bgImage)

        val units = assetsLoader.getUnits()
        val unit = WarUnit(WarUnitImage(Animation(0.25f, units.findRegions("unit"))))
        stage.addActor(unit)

        val ship = WarUnit(WarUnitImage(Animation(0.25f, units.findRegions("ship"))))
        ship.setPosition(width - ship.getWidth(), 0f)
        stage.addActor(ship)


        //Gdx.input.setInputProcessor(stage)
    }

    override fun render(delta: Float) {
        gameCamera.update();
        if (asset.update()) {
            stage.act(delta)
            stage.draw()
        }
        fpsLogger.log()
    }

    override fun hide() {
        stage.dispose()
        asset.clear()
    }
}
