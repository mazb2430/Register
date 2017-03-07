import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

public class ArtefaktRegister extends JFrame{

	ArrayList<Artefakter> artefakter = new ArrayList<>();
	ArrayList<Aktier> aktier = new ArrayList<>();

	JList innehåll = new JList(artefakter.toArray());

	ButtonGroup sorteringsKnappar = new ButtonGroup();

	String[] rullgardinsInnehåll = {"Smycke", "Aktie", "Apparat"};
	JComboBox<String> rullgardin = new JComboBox<String>(rullgardinsInnehåll);
	JButton visaKnapp = new JButton("Visa");
	JButton börskraschKnapp = new JButton("Börskrasch");
	JScrollPane scrolla = new JScrollPane();
	JRadioButton namnSortering = new JRadioButton("Namn");
	JRadioButton värdeSortering = new JRadioButton("Värde");
	Comparator<Artefakter> valdRadioKnapp = new NamnComparator();

	public ArtefaktRegister(){
		super("Sakregister");
		setLayout(new BorderLayout());

		// Norra panelen
		JPanel norraPanelen = new JPanel(new FlowLayout(FlowLayout.CENTER));
		add(norraPanelen, BorderLayout.NORTH);
		JLabel rubrik = new JLabel("Värdesaker");
		norraPanelen.add(rubrik);

		// Östra panelen
		JPanel östraPanelen = new JPanel();
		östraPanelen.setLayout(new BoxLayout(östraPanelen, BoxLayout.Y_AXIS));
		östraPanelen.add(Box.createGlue());
		östraPanelen.add(new JLabel("Sortering"));
		namnSortering.setSelected(true);
		sorteringsKnappar.add(namnSortering);
		sorteringsKnappar.add(värdeSortering);
		östraPanelen.add(namnSortering);
		östraPanelen.add(värdeSortering);
		add(östraPanelen, BorderLayout.EAST);

		//Västra panelen
		JPanel västrapanelen = new JPanel();
		add(västrapanelen, BorderLayout.WEST);

		// Södra panelen
		JPanel södraPanelen = new JPanel(new FlowLayout(FlowLayout.CENTER));
		södraPanelen.add(new JLabel("Nytt:"));
		södraPanelen.add(rullgardin);
		södraPanelen.add(visaKnapp);
		södraPanelen.add(börskraschKnapp);
		add(södraPanelen, BorderLayout.SOUTH);

		// Centrala panelen
		innehåll.setFocusable(false);
		scrolla.setViewportView(innehåll);
		add(scrolla, BorderLayout.CENTER);

		// Lägger till funktionalitet för att lägga till artefakt
		rullgardin.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String valdArtefakttyp = (String) rullgardin.getSelectedItem();

				switch(valdArtefakttyp){
				case "Smycke":
					SmyckeFormulär smyckeFormulär = new SmyckeFormulär();

					int i = JOptionPane.showConfirmDialog(ArtefaktRegister.this, smyckeFormulär, "Nytt smycke", JOptionPane.OK_CANCEL_OPTION);

					if (i != 0)
						return;

					if (smyckeFormulär.getAntalÄdelstenar() < 0){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Du angav ett felaktigt antal.", "Fel!", JOptionPane.OK_OPTION);
						break;
					}

					if(smyckeFormulär.getArtefaktNamn().isEmpty() || smyckeFormulär.getAntalÄdelstenar() == 0){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Ett fält var tomt.", "Fel!", JOptionPane.OK_OPTION);
						break;	
					}

					Smycken nyttSmycke = new Smycken(smyckeFormulär.getArtefaktNamn(), smyckeFormulär.ärGuld(),
							smyckeFormulär.getAntalÄdelstenar());
					artefakter.add(nyttSmycke);
					break;

				case "Aktie":
					AktieFormulär aktieFormulär = new AktieFormulär();

					int k = JOptionPane.showConfirmDialog(ArtefaktRegister.this, aktieFormulär, "Ny aktie", JOptionPane.OK_CANCEL_OPTION);

					if(k != 0)
						return;

					if(aktieFormulär.getArtefaktNamn().isEmpty() || aktieFormulär.getAntalAktier() == 0 || aktieFormulär.getKurs() == 0){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Ett fält var tomt.", "Fel!", JOptionPane.OK_OPTION);
						break;	
					}

					if(aktieFormulär.getAntalAktier() <= 0 || aktieFormulär.getKurs() <= 0){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Du angav ett felaktigt antal.", "Fel!", JOptionPane.OK_OPTION);
						break;
					}

					Aktier nyAktie = new Aktier(aktieFormulär.getArtefaktNamn(), aktieFormulär.getAntalAktier(), aktieFormulär.getKurs());
					aktier.add(nyAktie);
					artefakter.add(nyAktie);
					break;

				case "Apparat":
					ApparatFormulär apparatFormulär = new ApparatFormulär();

					int a = JOptionPane.showConfirmDialog(ArtefaktRegister.this, apparatFormulär, "Ny apparat", JOptionPane.OK_CANCEL_OPTION);

					if(a != 0)
						return;

					if(apparatFormulär.getArtefaktNamn().isEmpty() || apparatFormulär.getApparatPris() == 0 || apparatFormulär.getApparatSlitage() == 0){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Ett fält var tomt.", "Fel!", JOptionPane.OK_OPTION);
						break;	
					}

					if(apparatFormulär.getApparatPris() < 0){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Du angav ett felaktigt antal.", "Fel!", JOptionPane.OK_OPTION);
						break;
					}

					if(apparatFormulär.getApparatSlitage() < 1 || apparatFormulär.getApparatSlitage() > 10){
						JOptionPane.showMessageDialog(ArtefaktRegister.this, "Nummer mellan 1 och 10 måste anges", "Fel!", JOptionPane.OK_OPTION);
						break;
					}

					Apparater nyApparat = new Apparater(apparatFormulär.getArtefaktNamn(), apparatFormulär.getApparatPris(),
							apparatFormulär.getApparatSlitage());
					artefakter.add(nyApparat);

					break;
				}
			}
		});

		visaKnapp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) { 

				Collections.sort(artefakter, valdRadioKnapp);
				innehåll.removeAll();
				innehåll.setListData(artefakter.toArray());

			}
		});

		börskraschKnapp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e){
				for(Aktier aktie : aktier){
					aktie.setKursTillNoll();
				}
			}
		});

		namnSortering.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				valdRadioKnapp = new NamnComparator();
			}
		});

		värdeSortering.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				valdRadioKnapp = new VärdeComparator();

			}

		});


		pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}


	public static void main(String[] args){	
		new ArtefaktRegister();
	}

}

class SmyckeFormulär extends JPanel{
	JTextField namnFält = new JTextField(15);
	JTextField antalÄdelstenarFält = new JTextField(5);
	JCheckBox ärÄdelMetall = new JCheckBox("Av guld");

	public SmyckeFormulär(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel namnPanel = new JPanel();
		namnPanel.add(new JLabel("Namn: "));
		namnPanel.add(namnFält);

		JPanel ädelstenarPanel = new JPanel();
		ädelstenarPanel.add(new JLabel("Antal ädelstenar: "));
		ädelstenarPanel.add(antalÄdelstenarFält);

		JPanel materialPanel = new JPanel();
		materialPanel.add(ärÄdelMetall);

		add(namnPanel);
		add(ädelstenarPanel);
		add(materialPanel);

	}

	public String getArtefaktNamn(){
		return namnFält.getText();
	}

	public int getAntalÄdelstenar(){
		if(antalÄdelstenarFält.getText().isEmpty()){
			return 0;
		}

		int antal=0;
		try{
			antal=Integer.parseInt(antalÄdelstenarFält.getText());
		}catch(NumberFormatException e){
			return -1;
		}

		return Integer.parseInt(antalÄdelstenarFält.getText());

	}

	public boolean ärGuld(){
		return ärÄdelMetall.isSelected();
	}
}

class AktieFormulär extends JPanel{
	JTextField namnFält = new JTextField(15);
	JTextField antalFält = new JTextField(5);
	JTextField kursFält = new JTextField(5);

	public AktieFormulär(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel namnPanel = new JPanel();
		namnPanel.add(new JLabel("Namn: "));
		namnPanel.add(namnFält);

		JPanel antalPanel = new JPanel();
		antalPanel.add(new JLabel("Antal: "));
		antalPanel.add(antalFält);

		JPanel kursPanel = new JPanel();
		kursPanel.add(new JLabel("Kurs: "));
		kursPanel.add(kursFält);

		add(namnPanel);
		add(antalPanel);
		add(kursPanel);

	}

	public String getArtefaktNamn(){
		return namnFält.getText();
	}

	public int getAntalAktier(){
		if(antalFält.getText().isEmpty()){
			return 0;
		}

		int antal=0;
		try{
			antal=Integer.parseInt(antalFält.getText());
		}catch(NumberFormatException e){
			return -1;
		}
		return Integer.parseInt(antalFält.getText());
	}

	public double getKurs(){
		if(kursFält.getText().isEmpty()){
			return 0;
		}
		double antal=0;
		try{
			antal=Double.parseDouble(antalFält.getText());
		}catch(NumberFormatException e){
			return -1;
		}
		return Double.parseDouble(kursFält.getText());
	}
}

class ApparatFormulär extends JPanel{
	JTextField namnFält = new JTextField(15);
	JTextField prisFält = new JTextField(5);
	JTextField slitageFält = new JTextField(5);

	public ApparatFormulär(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel namnPanel = new JPanel();
		namnPanel.add(new JLabel("Namn: "));
		namnPanel.add(namnFält);

		JPanel prisPanel = new JPanel();
		prisPanel.add(new JLabel("Pris: "));
		prisPanel.add(prisFält);

		JPanel slitagePanel = new JPanel();
		slitagePanel.add(new JLabel("Slitage: "));
		slitagePanel.add(slitageFält);

		add(namnPanel);
		add(prisPanel);
		add(slitagePanel);
	}

	public String getArtefaktNamn(){
		return namnFält.getText();
	}

	public int getApparatPris(){
		if(prisFält.getText().isEmpty()){
			return 0;
		}
		int antal=0;
		try{
			antal=Integer.parseInt(prisFält.getText());
		}catch(NumberFormatException e){
			return -1;
		}
		return Integer.parseInt(prisFält.getText());
	}

	public double getApparatSlitage(){
		if(slitageFält.getText().isEmpty()){
			return 0;
		}
		double antal=0;
		try{
			antal=Double.parseDouble(slitageFält.getText());
		}catch(NumberFormatException e){
			return -1;
		}
		return Double.parseDouble(slitageFält.getText());
	}

}


