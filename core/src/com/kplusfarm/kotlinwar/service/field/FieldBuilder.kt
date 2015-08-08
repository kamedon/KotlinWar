package com.kplusfarm.kotlinwar.service.field

import com.badlogic.gdx.utils.viewport.Viewport
import com.kplusfarm.kotlinwar.entity.field.Field
import com.kplusfarm.kotlinwar.entity.field.Team

/**
 * Created by kamedon on 8/3/15.
 */
abstract class FieldBuilder(val width: Float, val height: Float, val level: Int, val viewport: Viewport) {

    abstract public fun createMyTeam(): Team ;

    abstract public fun createEnemyTeam(): Team ;

    public fun build(): Field {
        return Field(viewport, createMyTeam(), createEnemyTeam());
    }

}

