package io.over.org;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private SpriteBatch batch;
    private Texture image;
    private MovingActor player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport(), batch);

        image = new Texture("libgdx.png");
        player = new MovingActor(image);
        player.setPosition(140, 210); // starting position

        stage.addActor(player);
        Gdx.input.setInputProcessor(stage); // not strictly needed for polling, but good practice
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        handleInput(); // move the actor

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void handleInput() {
        float speed = 200 * Gdx.graphics.getDeltaTime(); // movement speed per frame
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveBy(0, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveBy(0, -speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveBy(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveBy(speed, 0);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        stage.dispose();
    }

    // Custom Actor class
    public static class MovingActor extends Actor {
        private Texture texture;

        public MovingActor(Texture texture) {
            this.texture = texture;
            setSize(texture.getWidth(), texture.getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, getX(), getY());
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            // You can add animations here later
        }
    }
}
