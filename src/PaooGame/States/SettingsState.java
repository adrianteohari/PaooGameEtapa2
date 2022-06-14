package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    Button[]list=new Button[1];
    int cooldown=10;
    public SettingsState(RefLinks refLink)
    {
            ///Apel al construcotrului clasei de baza.
        super(refLink);
        list[0]=new Button("Back");
    }


    public  void Input(){
        if(cooldown==0) {

            if (refLink.GetKeyManager().space) {

                State.SetState(refLink.GetGame().menuState);
            }
            cooldown=7;
        }
        else
            cooldown--;
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {
        Input();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.bg,0,0, refLink.GetWidth(),refLink.GetHeight(),null);
        Font font0=new Font("fontSettings",Font.LAYOUT_NO_LIMIT_CONTEXT,25);
        g.setFont(font0);
        g.drawString("No settings yet, Sorry!",50,100);
        Font font=new Font("fontSettings",Font.LAYOUT_NO_LIMIT_CONTEXT,40);
        g.setFont(font);
        list[0].Draw(g,800,450);
        g.drawString("*",770,450);
    }
}
