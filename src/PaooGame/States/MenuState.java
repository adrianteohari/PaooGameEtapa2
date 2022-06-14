package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{


    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    Button []menuList=new Button[4];
    int selected;
    int cooldown=0;
    public MenuState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);

        menuList[0]=new Button("Start");
        menuList[1]=new Button("Info");
        menuList[2]=new Button("Settings");
        menuList[3]=new Button("Exit");
        selected=0;

    }

    public  void Input(){
        if(cooldown==0) {
            if (refLink.GetKeyManager().down) {
                if(selected==3){
                    selected=0;
                }
                else
                    selected++;
            }
            if (refLink.GetKeyManager().up) {
                if(selected==0){
                    selected=3;
                }
                else
                    selected--;
            }
            if (refLink.GetKeyManager().space) {
                switch (selected){
                    case 0:{
                        State.SetState(refLink.GetGame().playState);
                        break;

                    }
                    case 1:{
                        State.SetState(refLink.GetGame().aboutState);
                        break;
                    }
                    case 2:{
                        State.SetState(refLink.GetGame().settingsState);
                        break;
                    }
                    case 3:{
                        System.exit(0);
                        break;
                    }
                }
            }
            cooldown=7;
        }
        else
            cooldown--;
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */

    @Override
    public void Update()
    {
        Input();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(Assets.bg,0,0, refLink.GetWidth(),refLink.GetHeight(),null);
        Font font=new Font("da",Font.TRUETYPE_FONT,40);
        g.setFont(font);
        for (int i=0;i<4;i++){
            menuList[i].Draw(g,400,70*i+150);
        }
        g.drawString("*",370,selected*70+150);
    }
}
