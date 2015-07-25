package com.kplusfarm.kotlinwar.service;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by kamedon on 7/25/15.
 */
public class CameraService(val width: Float) {
    public val height: Float
    public val gameCamera: OrthographicCamera
    public val uiCamera: OrthographicCamera
    public val gameViewport: FitViewport
    public val uiViewport: FitViewport
    public val displayRect: Rectangle

    init {
        var displayRate = width / Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight() * displayRate;
        displayRect = Rectangle(0f, 0f, width, height);
        gameCamera = OrthographicCamera(width, height);
        gameCamera.setToOrtho(false, width, height);
        uiCamera = OrthographicCamera();
        gameViewport = FitViewport(width, height, gameCamera);
        uiViewport = FitViewport(width, height, uiCamera);
    }

}
