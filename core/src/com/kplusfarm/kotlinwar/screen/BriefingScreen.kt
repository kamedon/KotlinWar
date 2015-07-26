package com.kplusfarm.kotlinwar.screen;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.kplusfarm.kotlinwar.KotlinWar
import com.kplusfarm.kotlinwar.service.AssetsLoader
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/25/15.
 */
public class BriefingScreen(game: KotlinWar) : BaseScreen(game) {
    var stage: Stage by Delegates.notNull()

    override fun show() {
        val skin = TextButton.TextButtonStyle()
        val assetsLoader = AssetsLoader(asset)
        assetsLoader.loadBg()
        assetsLoader.loadFont(30)
        asset.finishLoading()
        skin.font = assetsLoader.getFont()

        stage = Stage(uiViewPoint)
        val bg = assetsLoader.getBg()
        val bgImage = Image(bg)
        stage.addActor(bgImage)


        val title = TextButton(" Briefing Room", skin)
        title.setPosition(0f, height - title.getPrefHeight())
        stage.addActor(title)


        val text = TextButton("START", skin)
        text.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                this@BriefingScreen.setScreen(BattleScreen(game))
            }
        })
        val group = Group();
        group.addActor(text)
        group.setPosition((width - text.getPrefWidth()) / 2, (height - text.getPrefHeight()) / 2)
        stage.addActor(group)


        Gdx.input.setInputProcessor(stage)
    }

    override fun render(delta: Float) {
        gameCamera.update();
        if (asset.update()) {
            stage.act(delta)
            stage.draw()
        }
    }

    override fun hide() {
        stage.dispose()
        asset.clear()
    }
}
