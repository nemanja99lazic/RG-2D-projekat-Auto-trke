package grafika.kamere;

import grafika.objekti.Formula;
import grafika.staza.Staza;
import javafx.scene.input.RotateEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class FiksiranaKamera extends Kamera{

    public FiksiranaKamera(Formula formula, Staza staza) {
        super(formula, staza);
        this.tipKamere = TipoviKamera.FIKSIRANA;
        this.getChildren().addAll(formula, staza);
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
                new Translate(prikazniProzor.getWidth() /2, prikazniProzor.getHeight() / 2),
                new Rotate(180)
        );
        staza.getTransforms().setAll(
                velicinaPrikaza,
                new Rotate(+ formula.orijentacija().azimutDeg() - 180, prikazniProzor.getWidth() / 2, prikazniProzor.getHeight() / 2),
                new Translate( - prikazniProzor.getX(),  - prikazniProzor.getY())
        );
    }

    @Override
    public String toString()
    {
        return "Fiksirana kamera";
    }
}
