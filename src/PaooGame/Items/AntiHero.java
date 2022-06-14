package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class AntiHero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    public Rock rock;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public AntiHero(RefLinks refLink, float x, float y)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image = Assets.heroLeft;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitia
        var map=refLink.GetMap();
        var tile1=map.GetTile((int)(this.x+xMove+19)/48,(int)(this.y+yMove+16)/48);
        var tile2=map.GetTile((int)(this.x+xMove+28)/48,(int)(this.y+yMove+16)/48);
        var tile3=map.GetTile((int)(this.x+xMove+19)/48,(int)(this.y+yMove+40)/48);
        var tile4=map.GetTile((int)(this.x+xMove+28)/48,(int)(this.y+yMove+40)/48);
        // System.out.println(""+tile.IsSolid());
        if(!(tile1.IsSolid()||tile2.IsSolid()||tile3.IsSolid()||tile4.IsSolid()))
        {
            Move();
        }
        ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left2 == true)
        {
            image = Assets.heroLeft;
        }
        if(refLink.GetKeyManager().right2 == true) {
            image = Assets.heroRight;
        }
        if(rock!=null)
        {
            rock.Update();
        }
    }

    public void Hit()
    {
        life-=10;
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up2)
        {
            yMove = -speed;
        }
        ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down2)
        {
            yMove = speed;
        }
        ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left2)
        {
            xMove = -speed;
        }
        ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right2)
        {
            xMove = speed;
        }
        if(refLink.GetKeyManager().Enter)
        {
            if(image==Assets.heroRight) {
                rock = new Rock(refLink, x, y, 1, 0);
            }
            if(image==Assets.heroLeft) {
                rock = new Rock(refLink, x, y, -1, 0);
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);
        if(rock!=null)
        {
            rock.Draw(g);
        }

        Font font = new Font("life", Font.TRUETYPE_FONT, 30);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(life), 900, 25);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        // g.setColor(Color.blue);
        // g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}
