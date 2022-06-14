package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    Button[]list=new Button[1];
    int cooldown=10;
    public AboutState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
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
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.bg,0,0, refLink.GetWidth(),refLink.GetHeight(),null);
        Font font0=new Font("fontAbout",Font.LAYOUT_NO_LIMIT_CONTEXT,25);
        g.setFont(font0);
        g.drawString("Proiect realizat de Teohari Adrian",50,100);

        g.drawString("Story: doi gemeni au ceva de impartit asa ca se bat cu pietre. Oare cine castiga?",50,200);
        g.drawString("Univeritatea Tehnica Gheorghe Asachi",50,250);
        Font font=new Font("fontAbout",Font.LAYOUT_NO_LIMIT_CONTEXT,40);
        g.setFont(font);
        list[0].Draw(g,800,450);
        g.drawString("*",770,450);
    }
}
