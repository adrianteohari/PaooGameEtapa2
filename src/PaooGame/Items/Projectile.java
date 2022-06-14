package PaooGame.Items;

import PaooGame.RefLinks;

public abstract class Projectile extends Item {

    public static final float DEFAULT_SPEED         = 6.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_PROJECTILE_WIDTH  = 48;   /*!< Latimea implicita a imaginii proiectilului.*/
    public static final int DEFAULT_PROJECTILE_HEIGHT = 48;   /*!< Inaltimea implicita a imaginii proiectilului.*/

    protected float speed;  /*!< Retine viteza de deplasare proiectilului.*/
    protected float xMove;  /*!< Retine noua pozitie a proiectilului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a proiectilului pe axa Y.*/

    public Projectile(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);

        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }
    public void Move()
    {
        ///Modifica pozitia proiectilului pe axa X.
        ///Modifica pozitia proiectilului pe axa Y.
        MoveX();
        MoveY();
    }

    public void MoveX()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.


        x += xMove;
    }

    public void MoveY()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
    }
    public float GetSpeed()
    {
        return speed;
    }

    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()

     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()

     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)

     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)

     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
}
