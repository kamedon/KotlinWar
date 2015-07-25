package com.kplusfarm.kotlinwar.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.kplusfarm.kotlinwar.KotlinWar
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/25/15.
 */
public class MainScreen(game: KotlinWar) : BaseScreen(game) {
    private var batch: SpriteBatch by Delegates.notNull()

    override fun show() {

        val resolver = InternalFileHandleResolver();
        game.asset.setLoader(javaClass<FreeTypeFontGenerator>(), FreeTypeFontGeneratorLoader(resolver));
        game.asset.setLoader(javaClass<BitmapFont>(), ".ttf", FreetypeFontLoader(resolver));

        val size1Params = FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "font/font.ttf";
        size1Params.fontParameters.size = 30;
        game.asset.load("font/font.ttf", javaClass<BitmapFont>(), size1Params);
        batch = SpriteBatch();

    }

    override fun render(delta: Float) {
        game.gameCamera.update();
        batch.setProjectionMatrix(game.gameCamera.combined);
        if (game.asset.update() ) {
            batch.begin();
            val font = game.asset.get("font/font.ttf", javaClass<BitmapFont>())
            font.draw(batch, "First font!", game.width / 2, game.height / 2);
            batch.end();
        }
    }

    override fun hide() {
        game.asset.unload("font/yutapon.ttf")
    }
}
