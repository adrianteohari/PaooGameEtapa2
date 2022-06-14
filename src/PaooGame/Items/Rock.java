package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.WaterTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rock extends Projectile {

    private BufferedImage image;
    private int DirectionX;
    private int DirectionY;

    public Rock(RefLinks refLink, float x, float y, int directionX,int directionY) {
        super(refLink, x, y, Projectile.DEFAULT_PROJECTILE_WIDTH, Projectile.DEFAULT_PROJECTILE_HEIGHT);
        DirectionX=directionX;
        DirectionY=directionY;
        if(directionX==-1)
        {
            image = Assets.rockLeft;
        }
        else {
            image = Assets.rockRight;
        }

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 16;
    }

    @Override
    public void Update() {

        xMove=speed*DirectionX;
        yMove=speed*DirectionY;
        var map=refLink.GetMap();
        var tile1=map.GetTile((int)(this.x+xMove+19)/48,(int)(this.y+yMove+16)/48);
        if(!tile1.IsShootable())
        {
            Move();
        }
    }


    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
