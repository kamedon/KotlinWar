package com.kplusfarm.kotlinwar.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.kplusfarm.kotlinwar.KotlinWar
import kotlin.internal.getProgressionFinalElement
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/25/15.
 */
public class MainScreen(game: KotlinWar) : BaseScreen(game) {

    var stage: Stage by Delegates.notNull()
    override fun show() {
        val resolver = InternalFileHandleResolver();
        asset.setLoader(javaClass<FreeTypeFontGenerator>(), FreeTypeFontGeneratorLoader(resolver));
        asset.setLoader(javaClass<BitmapFont>(), ".ttf", FreetypeFontLoader(resolver));

        val size1Params = FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "font/font.ttf";
        size1Params.fontParameters.size = 30;
        asset.load("font/font.ttf", javaClass<BitmapFont>(), size1Params);

        stage = Stage(uiViewPoint)
        val skin = TextButton.TextButtonStyle()
        asset.finishLoading()
        skin.font = asset.get("font/font.ttf", javaClass<BitmapFont>())
        val text = TextButton("text", skin)
        val group = Group();
        group.addActor(text)
        group.setPosition((width - text.getPrefWidth()) / 2, (height - text.getPrefHeight()) / 2)
        stage.addActor(group)

    }

    override fun render(delta: Float) {
        gameCamera.update();
        if (asset.update() ) {
            stage.draw()
        }
    }

    override fun hide() {
        stage.dispose()
        asset.unload("font/font.ttf")
    }
}
