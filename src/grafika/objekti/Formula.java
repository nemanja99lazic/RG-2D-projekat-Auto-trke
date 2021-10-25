package grafika.objekti;

import geometrija.Vektor;
import grafika.kamere.Kamera;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public abstract class Formula extends Group implements Orijentisan
{
	protected final Vektor pozicija = new Vektor();
	protected final Vektor brzina = new Vektor();
	protected final Vektor orijentacija = new Vektor(0, 1, 0);

	protected static double MAX_BRZINA;
	protected static double UBRZANJE;
	protected static double BRZINA_SKRETANJA;

	protected double maxBrzina;
	protected double ubrzanje;
	protected double brzinaSkretanja;

	private double smerSkretanja = 0;
	private double smerUbrzanja = 0;

	public static final int BROJ_TOCKOVA = 4;
	protected Point2D[] pomerajTocka;

	private final Translate trans = new Translate();
	private final Rotate rot = new Rotate();
	private Kamera kamera;

	protected boolean poceoTrku = false;

	public Formula(double maxBrzina, double ubrzanje, double brzinaSkretanja)
	{
		MAX_BRZINA = maxBrzina;
		UBRZANJE = ubrzanje;
		BRZINA_SKRETANJA = brzinaSkretanja;

		this.maxBrzina = maxBrzina;
		this.ubrzanje = ubrzanje;
		this.brzinaSkretanja = brzinaSkretanja;
		pomerajTocka = new Point2D[BROJ_TOCKOVA];
		postaviPomerajeTockova();
		getTransforms().addAll(trans, rot);
	}

	protected abstract void postaviPomerajeTockova();

	public void postaviPoceoTrku(boolean poceoTrku)
	{
		this.poceoTrku = poceoTrku;
 	}

 	public boolean dohvatiPoceoTrku()
	{
		return poceoTrku;
	}

	@Override
	public Vektor pozicija()
	{
		return pozicija;
	}
	
	public Vektor brzina()
	{
		return brzina;
	}
	
        @Override
	public Vektor orijentacija()
	{
		return orijentacija;
	}

	public void pomeri(double t) 
	{

		double koeficijentUbrzanja = 1 - brzina.intenzitet() / maxBrzina;
		brzina.dodaj(orijentacija.proizvod(t * smerUbrzanja * ubrzanje * koeficijentUbrzanja));
		pozicija.dodaj(brzina.proizvod(t));

		orijentacija.rotirajOkoZ(t * brzinaSkretanja * smerSkretanja);
		brzina.postavi(orijentacija.proizvod(brzina.intenzitet()));

		trans.setX(pozicija.x());
		trans.setY(pozicija.y());
		rot.setAngle(-orijentacija.azimutDeg());

	}

	public Point2D[] dohvatiPomerajeTockova()
	{
		return this.pomerajTocka;
	}

	public void postaviKameru(Kamera k)
	{
		this.kamera = k;
	}

	public void ubrzaj(double i) 
	{
		smerUbrzanja = i;
	}

	public void skretanje(double i) 
	{
		smerSkretanja = i;
	}

	public Translate getTraslacija()
	{
		return this.trans;
	}

	public Rotate getRotacija()
	{
		return this.rot;
	}

	public void postaviMaxBrzinu(double maxBrzina)
	{
		this.maxBrzina = maxBrzina;
	}

	public double dohvatiInicijalnuMaxBrzinu()
	{
		return MAX_BRZINA;
	}

	public double dohvatiTrenutnuMaxBrzinu()
	{
		return maxBrzina;
	}
}
