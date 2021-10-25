import grafika.kamere.FiksiranaKamera;
import grafika.kamere.JednostavnaKamera;
import grafika.kamere.Kamera;
import grafika.kamere.PratecaKamera;
import grafika.objekti.*;
import grafika.ploce.Ploca;
import grafika.ploce.PlocaSaStartom;
import grafika.ploce.PlocaSaStazomKrivina;
import grafika.ploce.PlocaSaStazomRavna;
import grafika.staza.Staza;
import grafika.staza.StazaDefault;
import grafika.staza.StazaOsmica;
import grafika.staza.StazaTeska;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import meni.Meni;
import meni.MeniIzborFormule;
import meni.MeniIzborStaze;
import senzori.DetektorPozicijeTockova;
import senzori.Senzor;

public class Autotrke_skelet extends Application {

    private Stage primaryStage = null;

    private Group root;
    private Group rootIzborStaze;
    private Group rootIzborFormule;
    private Group rootZavrsneScene;

    private MeniIzborStaze meniIzborStaze;
    private int izborStaze;

    private MeniIzborFormule meniIzborFormule;
    private int izborFormule;

    private Formula formula;
    private Staza staza;
    private final Rectangle pozadina = new Rectangle(1000, 800);

    private IndikatorBrzine indikatorBrzine;

    public static int BROJ_KAMERA = 3;
    private Kamera kamere[];
    private Kamera kamera;

    private MyTimer timer;

    private Senzor senzor;
    private DetektorPozicijeTockova detektorPozicijeTockova;

    public static double SCENE_WIDTH = 900;
    public static double SCENE_HEIGHT = 700;

    private Circle krug;

    private class MyTimer extends AnimationTimer {
        private long prev = 0;
        public boolean pauseTimer = false;

        @Override
        public void handle(long now) {
            if (prev == 0)
                prev = now;

            long delta = now - prev;

            formula.pomeri(delta * 10e-9f);
            kamera.pomeriKameru(delta * 10e-9f);
            senzor.proveriSenzor();
            detektorPozicijeTockova.proveri();

            prev = now;

            indikatorBrzine.reagujNaPromenuBrzine();

            // Senzor se ugasio -----> Trka je zavrsena
            if(senzor != null && senzor.dohvatiZavrsioTrku()) {
                this.stop();
                primaryStage.setScene(napraviZavrsnuScenu());
            }

        }
    }

    private void napraviFormulu() {

        switch (this.izborFormule)
        {
            case 1:
                formula = new DefaultFormula();
                break;
            case 2:
                formula = new SporoVozilo();
                break;
            case 3:
                formula = new StaroVozilo();
                break;
            default:
                System.out.println("IZBOR FORMULE NIJE DOBAR");
        }
        formula.pozicija().postaviX(3 * Ploca.VELICINA);
        formula.pozicija().postaviY(3 * Ploca.VELICINA / 2);
        formula.orijentacija().rotirajOkoZ(Math.PI / 2);
        formula.pomeri(0);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.root = new Group();
        this.rootIzborStaze = new Group();
        this.rootIzborFormule = new Group();
        this.rootZavrsneScene = new Group();

        Scene scenaIzborStaze = napraviScenuIzborStaze();

        primaryStage.setTitle("Trke");
        primaryStage.setScene(scenaIzborStaze);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        /*if(senzor != null)
            senzor.interrupt();
        if(detektorPozicijeTockova != null)
            detektorPozicijeTockova.interrupt();*/
    }

    private Scene napraviScenuIzborStaze()
    {
        meniIzborStaze = new MeniIzborStaze();
        izborStaze = 0;
        rootIzborStaze.getChildren().add(meniIzborStaze);
        Scene scena = new Scene(rootIzborStaze, SCENE_WIDTH, SCENE_HEIGHT);
        scena.setOnKeyPressed(e -> onKeyPressedScenaIzborStaze(e));

        return scena;
    }

    private void onKeyPressedScenaIzborStaze(KeyEvent e)
    {
        switch (e.getCode())
        {
            case UP:
                meniIzborStaze.pomeri(Meni.GORE);
                break;
            case DOWN:
                meniIzborStaze.pomeri(Meni.DOLE);
                break;
            case ENTER:
                this.izborStaze = meniIzborStaze.dohvatiIzbor();
                primaryStage.setScene(null);
                Scene scenaIzborFormule = napraviScenuIzborFormule();
                primaryStage.setScene(scenaIzborFormule);
                break;
        }
    }

    private Scene napraviScenuIzborFormule()
    {
        meniIzborFormule = new MeniIzborFormule();
        izborFormule = 0;
        rootIzborFormule.getChildren().add(meniIzborFormule);
        Scene scena = new Scene(rootIzborFormule, SCENE_WIDTH, SCENE_HEIGHT);
        scena.setOnKeyPressed(e -> onKeyPressedScenaIzborFormule(e));

        return scena;
    }

    private void onKeyPressedScenaIzborFormule(KeyEvent e)
    {
        switch (e.getCode())
        {
            case UP:
                meniIzborFormule.pomeri(Meni.GORE);
                break;
            case DOWN:
                meniIzborFormule.pomeri(Meni.DOLE);
                break;
            case ENTER:
                this.izborFormule = meniIzborFormule.dohvatiIzbor();
                primaryStage.setScene(null);
                Scene glavnaScena = napraviGlavnuScenu();
                primaryStage.setScene(glavnaScena);
                break;
        }
    }

    private Scene napraviGlavnuScenu()
    {
        switch (izborStaze)
        {
            case 1:
                staza = new StazaDefault();
                break;
            case 2:
                staza = new StazaOsmica();
                break;
            case 3:
                staza = new StazaTeska();
                break;
        }
        staza.napraviStazu();
        napraviFormulu();

        pozadina.setFill(Color.GREEN);

        indikatorBrzine = new IndikatorBrzine(formula);

        kamere = new Kamera[BROJ_KAMERA];
        kamere[0] = new JednostavnaKamera(formula, staza);
        kamere[1] = new PratecaKamera(formula, staza);
        kamere[2] = new FiksiranaKamera(formula, staza);

        kamera = kamere[0];

        this.krug = new Circle(10);
        krug.setFill(Color.VIOLET);
        krug.getTransforms().add(
                new Translate(107.5, 107.5)
        );

        Circle testKrug = new Circle(10);
        testKrug.setFill(Color.DARKBLUE);
        testKrug.getTransforms().add(
                //new Translate(staza.dohvatiMatricuPloca()[2][7].getBoundsInParent().getMinX(), staza.dohvatiMatricuPloca()[2][7].getBoundsInParent().getMinY())
                new Translate(160, 150)
        );

        root.getChildren().addAll(pozadina, staza, formula, indikatorBrzine, kamera, krug, testKrug);

        senzor = new Senzor(staza, formula);
        detektorPozicijeTockova = new DetektorPozicijeTockova(staza, formula);

        Scene scena = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        scena.setOnKeyPressed(e -> onKeyPressed(e));
        scena.setOnKeyReleased(e -> onKeyReleased(e));

        MyTimer timer = new MyTimer();
        this.timer = timer;
        timer.start();

        /*senzor.start();
        detektorPozicijeTockova.start();*/

        return scena;
    }

    private Scene napraviZavrsnuScenu()
    {
        final int velicinaFontaVelikiText = 40;
        final int velicinaFontaMaliText = 30;

        Group textGrupa = new Group();
        Rectangle pozadina = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        pozadina.setFill(Color.LIGHTYELLOW);
        rootZavrsneScene.getChildren().add(pozadina);

        Text textZavrsenaTrka = new Text("Trka je zavrsena!");
        textZavrsenaTrka.setFont(Font.font(velicinaFontaVelikiText));

        textGrupa.getChildren().add(textZavrsenaTrka);

        if(senzor != null && senzor.dohvatiIgracVarao())
        {
            Text textIgracVarao = new Text("Igrac je varao.");
            textIgracVarao.setFont(Font.font(velicinaFontaMaliText));
            textIgracVarao.getTransforms().add(
                    new Translate(50, 50)
            );
            textGrupa.getChildren().add(textIgracVarao);
        }
        textGrupa.getTransforms().add(
                new Translate(SCENE_WIDTH / 2, SCENE_HEIGHT / 2)
        );
        rootZavrsneScene.getChildren().add(textGrupa);

        return new Scene(this.rootZavrsneScene, SCENE_WIDTH, SCENE_HEIGHT);
    }

    private Group dohvatiRoot()
    {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void onKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                formula.ubrzaj(1);
                break;

            case DOWN:
                formula.ubrzaj(-1);
                break;

            case RIGHT:
                formula.skretanje(-1.5);
                break;

            case LEFT:
                formula.skretanje(1.5);
                break;

            case NUMPAD1:
            case DIGIT1:
                this.root.getChildren().remove(kamera);
                kamera = kamere[0]; // Jednostavna kamera
                this.root.getChildren().add(kamera);
                kamera.promeniTransformacije();
                break;

            case NUMPAD2:
            case DIGIT2:
                this.root.getChildren().remove(kamera);
                kamera = kamere[1]; // Prateca kamera
                this.root.getChildren().add(kamera);
                kamera.promeniTransformacije();
                break;

            case NUMPAD3:
            case DIGIT3:
                this.root.getChildren().remove(kamera);
                kamera = kamere[2]; // Fiksirana kamera
                this.root.getChildren().add(kamera);
                kamera.promeniTransformacije();
                break;
            case DIGIT4:
                System.out.println("PLUS");
                if(kamera != kamere[0])
                    for(int i = 1; i < BROJ_KAMERA; i++)
                        kamere[i].promeniVelicinuPrikaza(0.05);
                break;
            case DIGIT5:
                if(kamera != kamere[0])
                    for(int i = 1; i < BROJ_KAMERA; i++)
                        kamere[i].promeniVelicinuPrikaza(-0.05);
                break;
        }
    }

    private void onKeyReleased(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                formula.ubrzaj(0);
                break;


            case RIGHT:
            case LEFT:
                formula.skretanje(0);
                break;
        }
    }
}
