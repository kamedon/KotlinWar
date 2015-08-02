package com.kplusfarm.kotlinwar.screen;

import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.scenes.scene2d.Stage
import com.kplusfarm.kotlinwar.KotlinWar
import com.kplusfarm.kotlinwar.service.AssetsLoader
import com.kplusfarm.kotlinwar.service.field.Field001Builder
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

        val builder = Field001Builder(width, height, 3, gameViewPoint)
        stage = builder.build()

        //        stage = Stage(gameViewPoint)
        //        val bg = assetsLoader.getBg()
        //        val bgImage = Image(bg)
        //        stage.addActor(bgImage)
        //
        //        val units = assetsLoader.getUnits()
        //        val unit = Pools.obtain(javaClass<Humanoid>())
        //        unit.image = WarUnitImage(Animation(0.125f, units.findRegions("unit")))
        //        unit.active()
        //
        //        val unit2 = Pools.obtain(javaClass<Fighter>())
        //        unit2.image = WarUnitImage(Animation(0.125f, units.findRegions("unit")))
        //        unit2.angle = 0.1f
        //        unit2.velocity = 4f
        //        unit2.setColor(Color.GREEN)
        //        unit2.active()
        //
        //        val unit3 = Pools.obtain(javaClass<Fighter>())
        //        unit3.image = WarUnitImage(Animation(0.125f, units.findRegions("unit")))
        //        unit3.image = WarUnitImage(Animation(0.125f, units.findRegions("unit")))
        //        unit3.setColor(Color.RED)
        //        unit3.angle = 1f
        //        unit3.velocity = 3f
        //        unit3.active()
        //
        //        stage.addActor(unit)
        //        stage.addActor(unit2)
        //        stage.addActor(unit3)
        //
        //        val ship = Pools.obtain(javaClass<Ship>())
        //        ship.image = WarUnitImage(Animation(0.125f, units.findRegions("ship")))
        //        ship.setPosition(width - ship.getWidth(), 0f)
        //        ship.addAction(Actions.sequence(Actions.moveTo(0f, 500f, 10f),
        //                Actions.moveTo(300f, 0f, 3f), Actions.moveTo(width - ship.getWidth(), height - ship.getHeight(), 9f)))
        //        ship.active()
        //
        //        val beam = Beam()
        //        beam.image = WarUnitImage(Animation(1f, units.findRegion("bullet")))
        //        unit.weapon = BeamRifle(units.findRegion("bullet"), beam)
        //        unit.target = ship
        //        unit2.weapon = BeamRifle(units.findRegion("bullet"), beam)
        //        unit2.target = ship
        //        unit3.weapon = BeamRifle(units.findRegion("bullet"), beam)
        //        unit3.target = ship
        //        stage.addActor(ship)

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
