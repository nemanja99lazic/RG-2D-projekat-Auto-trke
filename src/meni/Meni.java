package meni;

import javafx.scene.Group;

public abstract class Meni extends Group {

    protected int izbor;

    public int VELICINA = 150;
    public static final int DOLE = 1;
    public static final int GORE = 2;
    protected Group koren = new Group();

    public abstract void pomeri(int smer);

}
