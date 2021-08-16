package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

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
        image = Assets.rockLeft;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 4;
        normalBounds.y = 4;
        normalBounds.width = 4;
        normalBounds.height = 4;
    }

    @Override
    public void Update() {
        Fly();
    }

    private void Fly(){
        xMove=speed*DirectionX;
        yMove=speed*DirectionY;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
