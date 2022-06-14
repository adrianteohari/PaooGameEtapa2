package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Items.AntiHero;
import PaooGame.Items.Hero;
import PaooGame.Items.Rock;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import java.awt.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private AntiHero antiHero;

    Button[]list=new Button[1];
    int cooldown=10;
    boolean enabled=false;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
        list[0]=new Button("Back");
            ///Construieste harta jocului
        map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul
        hero = new Hero(refLink,100, 100);

        antiHero= new AntiHero(refLink,600, 300);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    public  void Input() {
        if (enabled) {
            if (cooldown == 0) {

                if (refLink.GetKeyManager().space) {

                    State.SetState(refLink.GetGame().menuState);
                }
                cooldown = 7;
            } else
                cooldown--;
        }
    }
    @Override
    public void Update() {
        Input();
        map.Update();
        hero.Update();
        antiHero.Update();
        if(hero.rock!=null) {
            if (CheckHitHero()) {
                hero.Hit();
            }
        }
        if(antiHero.rock!=null) {
            if (CheckHitAntiHero()) {
                antiHero.Hit();
            }
        }

    }

    public boolean CheckHitHero()
    {

        float heroprojX=hero.rock.GetX();
        float heroprojY=hero.rock.GetY();

        float antiheroX=antiHero.GetX();
        float antiheroY=antiHero.GetY();


        if(heroprojX>=antiheroX&&heroprojX<=antiheroX+16)
            if(heroprojY>=antiheroY-16&&heroprojY<=antiheroY+32)
            {
                return true;
            }

        return false;
    }

    public boolean CheckHitAntiHero()
    {

        float heroX = hero.GetX();
        float heroY = hero.GetY();

        float antiheroprojX=antiHero.rock.GetX();
        float antiheroprojY=antiHero.rock.GetY();

        if(antiheroprojX>=heroX&&antiheroprojX<=heroX+16)
            if(antiheroprojY>=heroY-16&&antiheroprojY<=heroY+32)
            {
                return true;
            }
        return false;
    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        if(hero.GetLife()<=0)
        {
            enabled=true;
            g.drawImage(Assets.bg, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
            Font EndGame = new Font("endgame", Font.TRUETYPE_FONT, 40);
            g.setFont(EndGame);
            g.drawString("GAME OVER", 300, 100);
            g.drawString("RED WINS", 300, 150);

            Font font=new Font("fontAbout",Font.LAYOUT_NO_LIMIT_CONTEXT,40);
            g.setFont(font);
            list[0].Draw(g,800,450);
            g.drawString("*",770,450);

        }
        else if(antiHero.GetLife()<=0)
        {
            enabled=true;
            g.drawImage(Assets.bg, 0, 0, refLink.GetWidth(), refLink.GetHeight(), null);
            Font EndGame = new Font("endgame", Font.TRUETYPE_FONT, 40);
            g.setFont(EndGame);
            g.drawString("GAME OVER", 300, 100);
            g.drawString("BLUE WINS", 300, 150);

            Font font=new Font("fontAbout",Font.LAYOUT_NO_LIMIT_CONTEXT,40);
            g.setFont(font);
            list[0].Draw(g,800,450);
            g.drawString("*",770,450);
        }
        else {
            map.Draw(g);
            hero.Draw(g);
            antiHero.Draw(g);
        }
    }
}
