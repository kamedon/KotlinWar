package com.kplusfarm.kotlinwar.entity.field

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.kplusfarm.kotlinwar.util.kdmap.NearUnit

/**
 * Created by kamedon on 8/2/15.
 */
class Field(viewport: Viewport, public val myTeam: Team, public val enemyTeam: Team) : Stage(viewport: Viewport) {
    init {
        addActor(enemyTeam)
        addActor(myTeam)
    }

    fun update(delta: Float) {
        collide(delta);
        near(delta);
        dead();
    }

    private fun dead() {
        enemyTeam.dead();
        myTeam.dead();
    }

    private fun collide(delta: Float) {
        collide(myTeam.unitGroup, enemyTeam.bulletGroup, delta);
        collide(enemyTeam.unitGroup, myTeam.bulletGroup, delta);
    }

    private fun collide(units: UnitGroup, bullets: BulletGroup, delta: Float) {
        units.collide(bullets)
    }

    private fun near(delta: Float) {
        if (myTeam.unitSize > 0 && enemyTeam.unitSize > 0) {
            near(myTeam, enemyTeam, delta)
            near(enemyTeam, myTeam, delta)
        }
    }

    private fun near(team: Team, enemy: Team, delta: Float) {
        val nearUnit = NearUnit()
        for (i in 0..team.unitSize - 1) {
            var unit = team.getUnit(i)
            unit.nearRuntime(delta);
            if(unit.needNear()){
                for (n in 0..enemy.unitSize - 1) {
                    var enemy = enemy.getUnit(n)
                    if (!enemy.dead) {
                        val len = Vector2.len(unit.centerX - enemy.centerX, unit.centerY - enemy.centerY)
                        if (nearUnit.distance > len) {
                            nearUnit.distance = len
                            nearUnit.unit = enemy
                        }
                    }
                }

            }
            unit.target = nearUnit.unit
        }
    }

    override fun draw() {
        super.draw()
        myTeam.kdMap.debug(getCamera().combined);
    }
}