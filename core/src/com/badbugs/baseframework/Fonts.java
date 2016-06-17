package com.badbugs.baseframework;

import com.badbugs.Game;
import com.badbugs.MainGameScreen;
import com.badbugs.objects.GameOver;
import com.badbugs.objects.fonts.Font;
import com.badbugs.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by ashrinag on 4/28/2016.
 */
public class Fonts
{

  static BitmapFont font;

  public static void loadAllFonts()
  {
    loadScore();
  }

  private static void loadScore()
  {
    font = new BitmapFont(Gdx.files.internal("fonts/tahoma.fnt"), false);
    font.setColor(Color.BLACK);

  }

  public static void renderScore(Batch batch, int score)
  {
    font.getData().setScale(Constants.SCORE_SCALE);
    font.draw(batch, "Score " + score, Constants.SCORE_X_POS, Game.cam_height / 2, 10, -1, false);
  }

  public static void rendGameOverText(Batch batch, GameOver gameOver, int score)
  {
    float alpha = gameOver.elapsedTime / Constants.GAME_OVER_FADE_IN_TIME;
    if (alpha <= 1)
    {
      font.setColor(0, 0, 0, alpha);
    }
    font.getData().setScale(Constants.GAME_OVER_SCALE);
    font.draw(batch, "Game Over", Constants.GAME_OVER_TEXT_X_POS, Constants.GAME_OVER_TEXT_Y_POS, 13, -1, false);
    font.draw(batch, "Score " + score, Constants.SCORE_TEXT_X_POS, Constants.SCORE_TEXT_Y_POS, 13, -1, false);
  }

  public static void renderText(Batch batch, Font f)
  {
    font.getData().setScale(f.getScale());
    font.draw(batch, f.getText(), f.getX(), f.getY(), 65, -1, false);

  }

}
