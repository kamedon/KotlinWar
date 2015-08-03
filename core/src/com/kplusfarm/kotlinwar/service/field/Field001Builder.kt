package com.kplusfarm.kotlinwar.service.field

import com.badlogic.gdx.utils.viewport.Viewport
import com.kplusfarm.kotlinwar.entity.field.Team
import com.kplusfarm.kotlinwar.entity.field.TeamCode
import com.kplusfarm.kotlinwar.util.kdmap.KdMap

/**
 * Created by kamedon on 8/3/15.
 */
public class Field001Builder(width: Float, height: Float, level: Int, viewport: Viewport) : FieldBuilder(width, height, level, viewport) {
    override fun createEnemyTeam(): Team {
        val team = Team(TeamCode.RED, KdMap(width, height, 3))

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

        return team
    }

    override fun createMyTeam(): Team {
        val team = Team(TeamCode.GREEN, KdMap(width, height, 3))
        return team
    }
}