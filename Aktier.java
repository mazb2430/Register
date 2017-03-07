
public class Aktier extends Artefakter {

	private int antal;
	private double kurs;

	public Aktier(String namn, int antal, double kurs){

		super(namn);
		this.antal = antal;
		this.kurs = kurs;
	}

	public int getAntal(){
		return antal;
	}

	public double getKurs(){
		return kurs;
	}

	public void setKursTillNoll(){
		kurs = 0.00;
	}

	public double getVärde() {	
		return antal * kurs;
	}

	public String toString() {
		return "Aktie: " + getNamn() + " antal: " + antal + " kurs: " + kurs + "\t" + "Värde: " + getVärdeMedMoms() + " SEK";
	}

}
