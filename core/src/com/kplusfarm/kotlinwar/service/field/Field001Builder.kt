package com.kplusfarm.kotlinwar.service.field

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.utils.Pools
import com.badlogic.gdx.utils.viewport.Viewport
import com.kplusfarm.kotlinwar.entity.asset.WarUnitImage
import com.kplusfarm.kotlinwar.entity.bullet.Beam
import com.kplusfarm.kotlinwar.entity.field.Team
import com.kplusfarm.kotlinwar.entity.field.TeamCode
import com.kplusfarm.kotlinwar.entity.ship.TransportCraft
import com.kplusfarm.kotlinwar.entity.unit.Humanoid
import com.kplusfarm.kotlinwar.entity.weapon.BeamRifle
import com.kplusfarm.kotlinwar.service.AssetsLoader
import com.kplusfarm.kotlinwar.util.kdmap.KdMap

/**
 * Created by kamedon on 8/3/15.
 */
public class Field001Builder(width: Float, height: Float, level: Int, viewport: Viewport, val  assetsLoader: AssetsLoader) : FieldBuilder(width, height, level, viewport) {
    override fun createEnemyTeam(): Team {
        val team = Team(TeamCode.RED, KdMap(width, height, 3))

        val unitAsset = assetsLoader.getUnits()
        val unit = Pools.obtain(javaClass<Humanoid>())
        val image = WarUnitImage(Animation(0.125f, unitAsset.findRegions("unit")))
        unit.image = image;

        val beam = Beam()
        beam.image = WarUnitImage(Animation(1f, unitAsset.findRegion("bullet")))

        unit.weapon = BeamRifle(unitAsset.findRegion("bullet"), beam)
        unit.active()

        team.add(unit)

        val produceUnit = Humanoid();
        var shipImage= WarUnitImage(Animation(0.125f, unitAsset.findRegions("ship")))
        produceUnit.image = image;

        val ship = TransportCraft();
        ship.produceUnit = produceUnit
        ship.image = shipImage
        ship.active()

        team.add(ship)


        return team
    }

    override fun createMyTeam(): Team {
        val team = Team(TeamCode.GREEN, KdMap(width, height, 3))
        val unitAsset = assetsLoader.getUnits()
        val unit = Pools.obtain(javaClass<Humanoid>())
        unit.image = WarUnitImage(Animation(0.125f, unitAsset.findRegions("unit")))
        val beam = Beam()
        beam.image = WarUnitImage(Animation(1f, unitAsset.findRegion("bullet")))
        unit.weapon = BeamRifle(unitAsset.findRegion("bullet"), beam)
        unit.active()
        unit.setPosition(width - unit.getWidth(), 600f)
        unit.setColor(Color.RED)
        team.add(unit)
        return team
    }
}