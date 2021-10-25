package grafika.kamere;

import grafika.objekti.Formula;
import grafika.staza.Staza;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class PratecaKamera extends Kamera{

    public PratecaKamera(Formula formula, Staza staza) {
        super(formula, staza);
        this.tipKamere = TipoviKamera.PRATECA;
        this.getChildren().addAll(formula, staza, prikazniProzor);
        prikazniProzor.setFill(null);
    }

    @Override
    public void pomeriKameru(double vreme) {
        prikazniProzor.setX(formula.pozicija().x() - prikazniProzor.getWidth() / 2);
        prikazniProzor.setY(formula.pozicija().y() - prikazniProzor.getHeight() / 2);

        promeniTransformacije();
    }

    @Override
    public void promeniTransformacije(){
        formula.getTransforms().setAll(
                velicinaPrikaza,
                new Translate(prikazniProzor.getWidth() / 2, prikazniProzor.getHeight() / 2),
                formula.getRotacija()
        );
        staza.getTransforms().setAll(
                velicinaPrikaza,
                new Translate(- prikazniProzor.getX(), -prikazniProzor.getY()));
    }

    public String toString()
    {
        return "Prateca kamera";
    }

}
