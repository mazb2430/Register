
public class Apparater extends Artefakter {

	private int inköpspris;
	private double slitage;

	public Apparater(String namn, int inköpspris, double slitage){

		super(namn);
		this.inköpspris = inköpspris;
		this.slitage = slitage;
	}

	public int getInköpspris(){
		return inköpspris;
	}

	public double getSlitage(){
		return slitage;
	}

	public String getSlitageProcent(){
		return slitage*10 + "%";
	}

	public double getVärde() {
		return inköpspris * (slitage/10);
	}

	public String toString() {
		String sträng = "Apparat: " + getNamn() + " " + "pris: " + inköpspris + " slitage: " +
				getSlitageProcent() + "\t" + "Värde: " + getVärdeMedMoms() + " SEK";
		return sträng;
	}


}
