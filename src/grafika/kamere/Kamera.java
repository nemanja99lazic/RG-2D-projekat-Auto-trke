package grafika.kamere;

import grafika.objekti.Formula;
import grafika.staza.Staza;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public abstract class Kamera extends Group {

    public enum TipoviKamera{
        JEDNOSTAVNA, PRATECA, FIKSIRANA
    }

    protected TipoviKamera tipKamere;
    protected Text natpisTipKamere;
    protected Formula formula;
    protected Staza staza;
    protected Rectangle prikazniProzor = new Rectangle(0, 0, 900, 700);

    protected double faktorUvecanja;
    protected Scale velicinaPrikaza = new Scale(1, 1);

    public static double SCENE_WIDTH = 900;
    public static double SCENE_HEIGHT = 700;

    protected Kamera(Formula formula, Staza staza){
        this.formula = formula;
        this.staza = staza;
        faktorUvecanja = 1;

        natpisTipKamere = new Text(this.toString());
        this.getChildren().add(natpisTipKamere);
        this.getTransforms().addAll(
                new Translate(30, 10)
        );
    }

    public void promeniVelicinuPrikaza(double faktor)
    {
        faktorUvecanja += faktor;
        velicinaPrikaza.setX(faktorUvecanja);
        velicinaPrikaza.setY(faktorUvecanja);
        /*prikazniProzor.setX(prikazniProzor.getX() + prikazniProzor.getWidth() * faktor);
        prikazniProzor.setY(prikazniProzor.getY() + prikazniProzor.getHeight() * faktor);*/
        promeniTransformacije();
    }

    public TipoviKamera dohvatiTipKamere()
    {
        return tipKamere;
    }

    public Rectangle dohvatiPrikazniProzor(){
        return this.prikazniProzor;
    }

    public abstract void pomeriKameru(double vreme);
    public abstract void promeniTransformacije();
}
