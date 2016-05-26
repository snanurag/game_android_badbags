package com.badbugs;

import com.badbugs.baseframework.Renderers;
import com.badbugs.baseframework.SpritesCreator;
import com.badbugs.objects.BasicObject;
import com.badbugs.objects.Shop;
import com.badbugs.payment.GamePurchaseObserver;
import com.badbugs.payment.PlatformBuilder;
import com.badbugs.payment.PlatformResolver;
import com.badbugs.util.Constants;
import com.badbugs.util.Inputs;
import com.badbugs.util.TouchInfo;
import com.badbugs.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by ashrinag on 5/14/2016.
 */
public class ShopScreen extends ScreenAdapter
{
  Rectangle knifeBoosterBounds;
  Rectangle backButtonBounds;
  Game game;
  TouchInfo touchInfo;
  private static Shop shop;

  static float[] KNIFE_BOOSTER_BUTTON;
  static float[] BACK_BUTTON;

  public PurchaseManagerConfig purchaseManagerConfig;

  public ShopScreen(Game game)
  {
    this.game = game;
    Gdx.input.setInputProcessor(new Inputs());
    IAPinit();
    defineBounds();
  }

  public static void load()
  {
    try
    {
      shop = SpritesCreator.loadShop();
    } catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  private void defineBounds()
  {
    // Top left coordinates are the pivots
    KNIFE_BOOSTER_BUTTON = new float[] { 468 * Game.screenWidth / Constants.HOME_SCREEN_W,
        340 * Game.screenHeight / Constants.HOME_SCREEN_H, 200 * Game.screenWidth / Constants.HOME_SCREEN_W,
        200 * Game.screenHeight / Constants.HOME_SCREEN_H };
    BACK_BUTTON = new float[] { 2032 * Game.screenWidth / Constants.HOME_SCREEN_W,
        1156 * Game.screenHeight / Constants.HOME_SCREEN_H, 270 * Game.screenWidth / Constants.HOME_SCREEN_W,
        116 * Game.screenHeight / Constants.HOME_SCREEN_H };

    knifeBoosterBounds = new Rectangle(KNIFE_BOOSTER_BUTTON[0], KNIFE_BOOSTER_BUTTON[1], KNIFE_BOOSTER_BUTTON[2],
        KNIFE_BOOSTER_BUTTON[3]);
    backButtonBounds = new Rectangle(BACK_BUTTON[0], BACK_BUTTON[1], BACK_BUTTON[2], BACK_BUTTON[3]);
  }

  private void IAPinit()
  {
    // ---- IAP: define products ---------------------
    purchaseManagerConfig = new PurchaseManagerConfig();
    purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(Constants.double_speed));
    GamePurchaseObserver purchaseObserver = new GamePurchaseObserver();
    PlatformBuilder.setComponents(null, purchaseObserver, purchaseManagerConfig);
    try
    {
      PlatformBuilder.build();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void render(float delta)
  {
    Renderers.renderShopScreen(Game.batch, shop);
    touchInfo = Util.getFromTouchEventsQueue();
    if (touchInfo != null)
    {
      if (knifeBoosterBounds.contains(touchInfo.touchX, touchInfo.touchY))
      {
        //TODO click sound here.
        PlatformBuilder.getPlatformResolver().requestPurchase(Constants.double_speed);
        return;
      } else if (backButtonBounds.contains(touchInfo.touchX, touchInfo.touchY))
      {
        //TODO click sound here.
        game.setScreen(new MainMenuScreen(game));
      }
    }
  }

}

