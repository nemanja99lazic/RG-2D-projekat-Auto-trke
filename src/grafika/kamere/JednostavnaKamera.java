package grafika.kamere;

import grafika.objekti.Formula;
import grafika.staza.Staza;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class JednostavnaKamera extends Kamera{

    public JednostavnaKamera(Formula formula, Staza staza){
        super(formula, staza);
        this.tipKamere = TipoviKamera.JEDNOSTAVNA;
        this.getChildren().addAll(formula, staza);
    }

    @Override
    public void pomeriKameru(double vreme) {

    }

    @Override
    public void promeniTransformacije(){
        formula.getTransforms().setAll(formula.getTraslacija(), formula.getRotacija());
        staza.getTransforms().setAll(new Translate(0, 0));
    }

    @Override
    public String toString() {
        return "Jednostavna kamera";
    }
}
