package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.sun.prism.image.ViewPort

/**
 * Created by kamedon on 8/2/15.
 */
class Field(viewport: Viewport, public val myTeam: Team, public val enemyTeam: Team) : Stage(viewport: Viewport) {
    init {
        addActor(enemyTeam)
        addActor(myTeam)
    }
}