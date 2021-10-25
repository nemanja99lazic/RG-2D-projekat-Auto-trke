package grafika.staza;

import grafika.ploce.Ploca;
import javafx.scene.Group;
import javafx.scene.transform.Translate;


public abstract class Staza extends Group
{
    protected Ploca[][]	matricaPloca;
    public int START_POLJE_X = 1;
    public int START_POLJE_Y = 3;

    public Staza(int velX, int velY)
    {
        matricaPloca = new Ploca[velX][velY];
    }

    public Staza()
    {
        this(20, 20);
    }

    public void postaviPlocu(int vrsta, int kolona, Ploca p)
    {
        if( matricaPloca[vrsta][kolona] != null )
            getChildren().remove( matricaPloca[vrsta][kolona] );
        
        matricaPloca[vrsta][kolona] = p;
        getChildren().add(p);
        p.getTransforms().setAll( new Translate(kolona*Ploca.VELICINA, vrsta*Ploca.VELICINA) );        
    }

    public void postaviKontrolni(int vrsta, int kolona, int id)
    {
        matricaPloca[vrsta][kolona].postaviKontrolni(id);
    }

    public abstract void napraviStazu();

    protected void postaviKontrolnePloce()
    {

    }

    public Ploca[][] dohvatiMatricuPloca()
    {
        return matricaPloca;
    }
}
