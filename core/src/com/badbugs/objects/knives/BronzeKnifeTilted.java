package com.badbugs.objects.knives;

import com.badbugs.Game;
import com.badbugs.util.ObjectsCord;
import com.badbugs.util.Constants;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ashrinag on 9/11/2016.
 */
public class BronzeKnifeTilted extends Knife{

    private float SPEED = 340;
    public BronzeKnifeTilted(Texture texture) {
        super(texture);
        pixelDimensions = new int[] { ObjectsCord.BRONZE_KNIFE_CENTER[0] * 2, ObjectsCord.BRONZE_KNIFE_CENTER[1] * 2 };
        cameraDimensions = new float[] { Constants.BRONZE_KNIFE_WIDTH/1.414f, Constants.BRONZE_KNIFE_HEIGHT};
        screenDimensions = new float[] { cameraDimensions[0] * Game.screenWidth / Game.cam_width,
                cameraDimensions[1] * Game.screenHeight / Game.cam_height };
        init();
    }

    @Override public int[][] getPixelCoords() {
        return ObjectsCord.BRONZE_KNIFE_CORDS;
    }

    public float getInitialAngle() {
        return 180;
    }

    @Override
    public float getSpeed() {
        return SPEED;
    }
}
