package com.oblig4.del1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class deltagerTest {

	@Test
	void deltagerTestOppsett() {
		Deltager d = new Deltager("Ole", "Lukk", "90909090", "Mann");
		assertEquals("Ole", d.getFornavn());
		assertEquals("Lukk", d.getEtternavn());
		assertEquals("90909090", d.getMobil());
		assertEquals("Mann", d.getKjonn());
	}
	
	@Test
	void deltagerListeErIkkeTom() {
	        List<Deltager> liste = new ArrayList<>();
	        liste.add(new Deltager("Silje", "Silesen", "90807060", "Kjonn"));

	        boolean finnes = liste.stream()
	            .anyMatch(d -> d.getFornavn().equals("Silje"));

	        assertTrue(finnes);
	}
	
	@Test
	void mobilNummerRegistrertAlleredeTest() {
		List<Deltager> deltagere = new ArrayList<>();
		deltagere.add(new Deltager("Kari", "Karensen", "90909090", "Kvinne"));
		
		boolean finnesAllerede = deltagere.stream()
				.anyMatch(d -> d.getMobil().equals("90909090"));
		
		assertTrue(finnesAllerede);
	}
}
