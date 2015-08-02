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
        return team
    }

    override fun createMyTeam(): Team {
        val team = Team(TeamCode.GREEN, KdMap(width, height, 3))
        return team
    }
}