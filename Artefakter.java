import java.util.Comparator;

class NamnComparator implements Comparator<Artefakter>{
	public int compare(Artefakter o1, Artefakter o2) {
		int sorteraEfterNamn = o1.getNamn().compareTo(o2.getNamn());
		return sorteraEfterNamn;	
	}

}

class VärdeComparator implements Comparator<Artefakter>{
	public int compare(Artefakter o1, Artefakter o2) {
		int sorteraEfterVärde = (int) (o1.getVärdeMedMoms() - o2.getVärdeMedMoms());
		return sorteraEfterVärde;
	}

}

public abstract class Artefakter {

	private String namn; 
	private static double momsFaktor = 1.25; 

	public Artefakter(String namn){
		this.namn = namn;
	}

	public String getNamn(){
		return namn; 
	}

	public abstract double getVärde();

	public final double getVärdeMedMoms(){
		return getVärde() * momsFaktor;
	}

	public abstract String toString();
}
