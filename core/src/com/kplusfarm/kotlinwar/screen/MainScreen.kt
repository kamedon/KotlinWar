package com.kplusfarm.kotlinwar.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.kplusfarm.kotlinwar.KotlinWar
import com.kplusfarm.kotlinwar.service.AssetsLoader
import kotlin.internal.getProgressionFinalElement
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/25/15.
 */
public class MainScreen(game: KotlinWar) : BaseScreen(game) {

    var stage: Stage by Delegates.notNull()
    //    private var font12: BitmapFont by Delegates.notNull()

    override fun show() {
        val skin = TextButton.TextButtonStyle()
        val assetsLoader = AssetsLoader(asset)
        assetsLoader.loadFont(30)
        assetsLoader.loadBg()
        asset.finishLoading()
        skin.font = assetsLoader.getFont()

        stage = Stage(uiViewPoint)
        //        skin.font = font12
        val bg = assetsLoader.getBg()
        var image = Image(bg)
        stage.addActor(image)

        val text = TextButton("START", skin)
        text.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                this@MainScreen.setScreen(BriefingScreen(game))
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
