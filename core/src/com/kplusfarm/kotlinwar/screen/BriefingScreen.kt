package com.kplusfarm.kotlinwar.screen;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.kplusfarm.kotlinwar.KotlinWar
import com.kplusfarm.kotlinwar.entity.Funds
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.ship.Ship
import com.kplusfarm.kotlinwar.entity.unit.Humanoid
import com.kplusfarm.kotlinwar.entity.unit.WarUnit
import com.kplusfarm.kotlinwar.service.AssetsLoader
import kotlin.properties.Delegates

/**
 * Created by kamedon on 7/25/15.
 */
public class BriefingScreen(game: KotlinWar) : BaseScreen(game) {
    var stage: Stage by Delegates.notNull()
    var menuLayout: Table = Table()
    var battleFieldLayout: Group = Group()


    var funds: Funds by Delegates.notNull()

    override fun show() {
        val assetsLoader = loadAsset()
        stage = Stage(uiViewPoint)
        val bg = assetsLoader.getBg()
        val bgImage = Image(bg)
        stage.addActor(bgImage)
        val title = Label(" Briefing Room - Stage 1- ", Label.LabelStyle(assetsLoader.getFont(), Color.MAROON))
        title.setPosition(0f, height - title.getPrefHeight())
        stage.addActor(title)

        menuLayout.setPosition(0f, 0f)
        menuLayout.setSize(width, 100f)
        stage.addActor(menuLayout)

        battleFieldLayout.setPosition(0f, menuLayout.getHeight())
        battleFieldLayout.setSize(width, height - menuLayout.getHeight() - title.getHeight())
        stage.addActor(battleFieldLayout)


        setupUI(assetsLoader)
        setupBattlefield(assetsLoader)
    }

    private fun setupBattlefield(assetsLoader: AssetsLoader) {
        val unitAsset = assetsLoader.getUnits()
        var warUnitAsset = WarUnitImage(Animation(0.125f, unitAsset.findRegions("ship")))
        repeat(5) {
            val unit = Ship(warUnitAsset)
            unit.setPosition(50f, it * 65f)
            unit.region = warUnitAsset.acts.getKeyFrame(0f)

            battleFieldLayout.addActor(unit)
        }

        repeat(5) {
            val unit = Ship(warUnitAsset)
            unit.setPosition(battleFieldLayout.getWidth() - unit.getWidth() -50, it * 65f)
            unit.setColor(Color.GREEN)
            unit.region = warUnitAsset.acts.getKeyFrame(0f)

            battleFieldLayout.addActor(unit)
        }
        val btnModify = TextButton("Modify", skin)
        btnModify.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
            }
        })

    }

    private fun setupUI(assetsLoader: AssetsLoader) {
        menuLayout.left().bottom()
        funds = Funds(1000000)

        val style = Label.LabelStyle(assetsLoader.getFont(), Color.WHITE)
        val textFunds = Label(java.lang.String.format("Funds : %d$", funds.value), style)
        menuLayout.add(textFunds).colspan(2).row()

        val btnModify = TextButton("MODIFY", skin)
        btnModify.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
            }
        })
        menuLayout.add(btnModify)
        val btnStart = TextButton("START", skin)
        btnStart.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                this@BriefingScreen.setScreen(BattleScreen(game))
            }
        })
        menuLayout.add(btnStart)


        Gdx.input.setInputProcessor(stage)
    }

    private var skin: TextButton.TextButtonStyle = TextButton.TextButtonStyle()


    private fun loadAsset(): AssetsLoader {
        val assetsLoader = AssetsLoader(asset)
        assetsLoader.loadBg()
        assetsLoader.loadFont(30)
        assetsLoader.loadUnits()
        asset.finishLoading()
        skin.font = assetsLoader.getFont()
        return assetsLoader;
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
