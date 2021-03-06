package no.hvl.dat107;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class BrukerGrenseSnitt {
	
	
	
	public void grenseSnitt()
	{
		String[] operasjoner = {"Ansatt", "Avdeling", "Prosjekt", "Avslutt"}; 
		String s = (String) JOptionPane.showInputDialog(null, "Velg entitet", "Eniteter", JOptionPane.QUESTION_MESSAGE, null, operasjoner, operasjoner[0]);
		if(s.equals("Ansatt"))
		{
			AnsattDAO ansattDAO = new AnsattDAO();
			String[] ansattOperasjoner = {"Finn Ansatt", "Finn Initialer", "Oppdater", "Legg Til", "Avslutt"}; 
			String l = (String) JOptionPane.showInputDialog(null, "Velg handling", "Handlinger", JOptionPane.QUESTION_MESSAGE, null, ansattOperasjoner, ansattOperasjoner[0]);
			if(l.equals("Finn Ansatt"))
			{
				int nr = Integer.parseInt(JOptionPane.showInputDialog("Skriv in ansattnr"));
				System.out.println(ansattDAO.finnAnsatt(nr));
			}
			else if(l.equals("Finn Initialer"))
			{
				String brukernavn = (JOptionPane.showInputDialog("Skriv in initialene til ansatt"));
				System.out.println(ansattDAO.finnInitialer(brukernavn));
			}
			else if(l.equals("Oppdater"))
			{
				Ansatt ansatt = new Ansatt();
				int id = Integer.parseInt(JOptionPane.showInputDialog("Skriv Id til den Ansatte"));
				ansatt.setStilling(JOptionPane.showInputDialog("Skriv in stillingstittel"));
				ansatt.setLonn(Integer.parseInt(JOptionPane.showInputDialog("Skriv in lonn")));
				ansattDAO.oppdaterAnsatt(id, ansatt.getStilling(), ansatt.getLonn());	
			}
			else if(l.equals("Legg Til"))
			{
				Ansatt ansatt = new Ansatt();
				ansatt.setBrukerNavn(JOptionPane.showInputDialog("Skriv in initialer"));
				ansatt.setForNavn(JOptionPane.showInputDialog("Skriv in fornavn"));
				ansatt.setEtterNavn(JOptionPane.showInputDialog("Skriv in etternavn"));
				LocalDate date = null;
				date.parse(JOptionPane.showInputDialog("Skriv in dato på formen: yyyy-mm-dd"));
				ansatt.setAnsettelsesDato(date);
				ansatt.setStilling(JOptionPane.showInputDialog("Skriv in stillingstittel"));
				ansatt.setLonn(Integer.parseInt(JOptionPane.showInputDialog("Skriv in lonn")));
				
				AvdelingDAO finnAvdeling = new AvdelingDAO();
				Avdeling avdelingen = finnAvdeling.finnAvdelingMedId(Integer.parseInt(JOptionPane.showInputDialog("Skriv in avdelings id")));
				ansatt.setAvdeling(avdelingen);
				
				ansattDAO.LeggTilAnsatt(ansatt);
				
				System.out.println("Ansatt ble lagt til!");
			}
			else
			{
				System.exit(0);
			}
			
		}
		else if(s.equals("Avdeling"))
		{
			AvdelingDAO avdelingDAO = new AvdelingDAO();
			String[] avdelingOperasjoner = {"Finn Avdeling med Id", "List alle Ansatte i Avdeling", "Legg til Avdeling", "Avslutt"}; 
			String a = (String) JOptionPane.showInputDialog(null, "Velg handling", "Handlinger", JOptionPane.QUESTION_MESSAGE, null, avdelingOperasjoner, avdelingOperasjoner[0]);
			if(a.equals("Finn Avdeling med Id"))
			{
				System.out.println(avdelingDAO.finnAvdelingMedId(Integer.parseInt(JOptionPane.showInputDialog("Skriv in avdelingsnummer"))));
			}
			else if(a.equals("List alle Ansatte i Avdeling"))
			{
				System.out.println(avdelingDAO.alleAnsatte(Integer.parseInt(JOptionPane.showInputDialog("Skriv in avdelingsnummer"))).toString());
			}
			else if(a.equals("Legg til Avdeling"))
			{
				AnsattDAO ansattDAO = new AnsattDAO();
				Avdeling avdelingen = new Avdeling();
				avdelingen.setNavn(JOptionPane.showInputDialog("Skriv in navn paa avdelingen"));
				Ansatt sjefen = ansattDAO.finnAnsatt(Integer.parseInt(JOptionPane.showInputDialog("Skriv in id til sjef")));
				avdelingen.setSjef(sjefen);
				avdelingDAO.LeggTilAvdeling(avdelingen.getNavn(), avdelingen.getSjef());
			
				
			}
			else
			{
				System.exit(0);
			}
		}
		else if(s.equals("Prosjekt"))
		{
			String[] prosjektOperasjoner = {"Legg til Prosjekt", "Registrer deltakelse", "Fore Timer", "Utskrift Prosjekt", "Avslutt"}; 
			String p = (String) JOptionPane.showInputDialog(null, "Velg handling", "Handlinger", JOptionPane.QUESTION_MESSAGE, null, prosjektOperasjoner, prosjektOperasjoner[0]);
			ProsjektDAO prosjektDAO = new ProsjektDAO();
			if(p.equals("Legg til Prosjekt"))
			{
				Prosjekt prosjektet = new Prosjekt();
				prosjektet.setNavn(JOptionPane.showInputDialog("Skriv in prosjekt navn"));
				prosjektet.setBeskrivelse(JOptionPane.showInputDialog("Skriv en beskrivelse av prosjektet"));
				System.out.println(prosjektDAO.LeggTilProsjekt(prosjektet));
			}
			else if(p.equals("Registrer deltakelse"))
			{
				int ansatteId = Integer.parseInt(JOptionPane.showInputDialog("Skriv in ansattid"));
				int prosjektId = Integer.parseInt(JOptionPane.showInputDialog("Skriv in prosjektid"));
				String rolle = JOptionPane.showInputDialog("Skriv rollen til den ansatte i prosjektet");
				int timer = Integer.parseInt(JOptionPane.showInputDialog("Skriv antall timer ansatte har jobbet paa prosjektet"));
				prosjektDAO.RegistrerDeltakelse(prosjektId, ansatteId, rolle, timer);
				System.out.println("Prosjektet ble registrert!");
			}
			else if(p.equals("Fore Timer"))
			{
				int ansatteId = Integer.parseInt(JOptionPane.showInputDialog("Skriv in ansattid"));
				int prosjektId = Integer.parseInt(JOptionPane.showInputDialog("Skriv in prosjektid"));
				int timer = Integer.parseInt(JOptionPane.showInputDialog("Skriv antall timer ansatte har jobbet paa prosjektet"));
				prosjektDAO.ForeTimer(prosjektId, ansatteId, timer);
				System.out.println("Antall timer er oppdatert!");
			}
			else if(p.equals("Utskrift Prosjekt"))
			{
				System.out.println(prosjektDAO.alleProsjekter().toString());
			}
		}
		else
		{
			System.exit(0);
		}
		
		grenseSnitt();
	
	}
	
	

}
