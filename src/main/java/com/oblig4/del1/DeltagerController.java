package com.oblig4.del1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeltagerController {
	
	private static final Logger log = LoggerFactory.getLogger(DeltagerController.class);
	private final List<Deltager> deltagere = Collections.synchronizedList(new ArrayList<>());
	
	public DeltagerController() {
		deltagere.add(new Deltager("Silje", "Silesen", "90897867", "Kvinne"));
		deltagere.add(new Deltager("Simen", "Simensen", "40302010", "Mann"));
		deltagere.add(new Deltager("Kurt", "Kur", "90902010", "Mann"));
		deltagere.add(new Deltager("Jenny", "Sen", "904402010", "Kvinne"));
		deltagere.add(new Deltager("Pia", "Peek", "97602010", "Kvinne"));
		deltagere.add(new Deltager("Sondre", "Sims", "95432010", "Mann"));
	}
	
    @GetMapping("/paamelding")
    public String visSkjema() {
        return "paamelding_med_melding";  
    }
    
    @PostMapping("/paameldt")
    public String naaPaameldt(@ModelAttribute Deltager deltager, RedirectAttributes redirectAtt) {
    	log.info("Mottatt påmelding: {} {} ({}), kjønn={}",
    		    deltager.getFornavn(), deltager.getEtternavn(), deltager.getMobil(), deltager.getKjonn());

        if (!deltager.getPassord().equals(deltager.getPassord2())) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Passordene er ikke like!");
            return "redirect:/paamelding"; 
        }
        
        boolean mobilAlleredeRegistrert = deltagere.stream()
        		.anyMatch(d -> d.getMobil().equals(deltager.getMobil()));
        
        if(mobilAlleredeRegistrert) {
        	redirectAtt.addFlashAttribute("feil", true);
        	redirectAtt.addFlashAttribute("errorMessage", "Mobilnummer er allerede registrert");
        	return "redirect:/paamelding";
        }
        		
    		deltagere.add(deltager);
    		redirectAtt.addFlashAttribute("deltager", deltager);
    	return "redirect:/paameldt";
    }
    
    @GetMapping("/paameldt")
    public String visPaameldt(Model model) {
        return "paameldt"; 
    }
    
    @GetMapping("/deltagerliste")
    public String visDeltagerListe(Model model) {
    	model.addAttribute("deltagere", deltagere);
    	return "deltagerliste";
    }
}
