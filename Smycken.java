
public class Smycken extends Artefakter {

	private boolean ädelmetal = false;
	private static int guld = 2000;
	private static int silver = 700;
	private int ädelsten;

	public Smycken(String namn, boolean ädelmetal, int sten){
		super(namn);
		this.ädelmetal = ädelmetal; 
		this.ädelsten = sten;
	}

	public int getÄdelmetal(){
		if(ädelmetal == false){
			return silver;
		}else{
			return guld;
		}
	}

	public int getSten(){
		return ädelsten;
	}

	public double getVärde() {
		int värde = 0; 

		if(ädelmetal == true){
			värde = guld;
		}else{
			värde = silver;
		}

		return (500 * ädelsten) + värde;
	}

	public String toString() {
		String sträng = "Smycke: " + getNamn() + " " + ädelsten + " ädelSt." + " " + "i ";


		if (getÄdelmetal() == guld)
			sträng += "guld";
		else
			sträng += "silver";

		sträng += "\t" + "Värde: " + getVärdeMedMoms() + " SEK";

		return sträng;
	}

}
