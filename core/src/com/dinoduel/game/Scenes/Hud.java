package com.dinoduel.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dinoduel.game.DinoDuel;


public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;


    Label dino1Label;
    Label dino1scoreLabel;
    Label timeLabel;
    Label stageLabel;
    Label stageNameLabel;
    Label countDownLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(DinoDuel.V_WIDTH, DinoDuel.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stageLabel = new Label("STAGE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stageNameLabel = new Label("StageName", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        dino1Label = new Label("Dino1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        dino1scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
//Add more for 2 player dino Label

        table.add(dino1Label).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(stageLabel).expandX().padTop(10);
        table.row();
        table.add(dino1scoreLabel).expandX();
        table.add(countDownLabel).expandX();
        table.add(stageNameLabel).expandX().padTop(10);


        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}//end class
