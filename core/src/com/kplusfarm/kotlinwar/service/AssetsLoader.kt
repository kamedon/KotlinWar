package com.kplusfarm.kotlinwar.service

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader

/**
 * Created by kamedon on 7/26/15.
 */
public class AssetsLoader(val asset: AssetManager) {

    val fontPath: String = "font/font.ttf"

    fun loadFont(size: Int) {
        val resolver = InternalFileHandleResolver();
        asset.setLoader(javaClass<FreeTypeFontGenerator>(), FreeTypeFontGeneratorLoader(resolver));
        asset.setLoader(javaClass<BitmapFont>(), ".ttf", FreetypeFontLoader(resolver));
        val size1Params = FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = fontPath;
        size1Params.fontParameters.size = 30;
        asset.load(fontPath, javaClass<BitmapFont>(), size1Params);
    }

    fun loadCustomFont(char: String, size: Int): BitmapFont? {
        val generator = FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = char
        parameter.size = size;
        val font = generator.generateFont(parameter);
        generator.dispose();
        return font
    }

    fun unLoadFont() {
        asset.unload(fontPath)
    }

    fun getFont(): BitmapFont {
        return asset.get(fontPath, javaClass<BitmapFont>())
    }

    fun loadBg() {
        asset.load("bg/bg_01.jpg", javaClass<Texture>())
    }

    fun getBg(): Texture {
        return asset.get("bg/bg_01.jpg", javaClass<Texture>())
    }

    fun loadUnits() {
        asset.load("unit/pack.atlas", javaClass<TextureAtlas>())
    }

    fun getUnits(): TextureAtlas {
        return asset.get("unit/pack.atlas", javaClass<TextureAtlas>())
    }
}
