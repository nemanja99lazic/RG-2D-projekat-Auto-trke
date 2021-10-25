package grafika.ploce;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Ploca extends Group
{
    public static final double VELICINA = 100;
    protected Group koren = new Group();
    protected int kontrolniId = 0;
    
    public Ploca()
    {
        getChildren().add(koren);
    }

    public Group dohvatiKoren()
    {
        return koren;
    }

    public void postaviKontrolni(int id)
    {
        kontrolniId = id;
    }

    public int dohvatiKontrolniId()
    {
        return kontrolniId;
    }
}
