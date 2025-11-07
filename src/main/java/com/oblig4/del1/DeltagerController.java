package com.oblig4.del1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DeltagerController {
	
	@Autowired
	private DeltagerRepository deltagerRepository;
	
	@Autowired
	private LoggInn loggInn;
	
	@Autowired
	private PassordService passordService;
	
	@Autowired
	private Input inputValidator;
	
	private static final Logger log = LoggerFactory.getLogger(DeltagerController.class);
	
	public DeltagerController() {
	}
	
    @GetMapping("/paamelding")
    public String visSkjema() {
        return "paamelding_med_melding";  
    }
    
    @PostMapping("/paameldt")
    public String naaPaameldt(
    		@ModelAttribute Deltager deltager,
    		HttpSession session, 
    		RedirectAttributes redirectAtt,
    		@RequestParam String passord2,
    		@RequestParam String fornavn) {
    	
    	if (!inputValidator.isValidFornavn(deltager.getFornavn())) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Fornavn må være 2-20 tegn, starte med stor bokstav og kun inneholde bokstaver, bindestrek eller mellomrom.");
            return "redirect:/paamelding";
        }
    	
    	if (!inputValidator.isValidEtternavn(deltager.getEtternavn())) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Etternavn må være 2-20 tegn, starte med stor bokstav og kun inneholde bokstaver eller bindestrek.");
            return "redirect:/paamelding";
        }

        if (!inputValidator.isValidMobil(deltager.getMobil())) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Mobilnummer må være eksakt 8 siffer.");
            return "redirect:/paamelding";
        }

        if (!inputValidator.isValidPassord(deltager.getPassord())) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Passord må være minst 6 tegn og inneholde både bokstaver og tall.");
            return "redirect:/paamelding";
        }

        if (!deltager.getPassord().equals(passord2)) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Passordene er ikke like!");
            return "redirect:/paamelding";
        }

        if (!inputValidator.isValidKjonn(deltager.getKjonn())) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Kjønn må være 'mann' eller 'kvinne'.");
            return "redirect:/paamelding";
        }
    	
    	log.info("Mottatt påmelding: {} {} ({}), kjønn={}",
    		    deltager.getFornavn(), deltager.getEtternavn(), 
    		    deltager.getMobil(), deltager.getKjonn());

        String salt = passordService.genererTilfeldigSalt();
        String hash = passordService.hashMedSalt(deltager.getPassord(), salt);

        deltager.setSalt(salt);
        deltager.setPassord(hash);

        boolean mobilAlleredeRegistrert = deltagerRepository.findById(deltager.getMobil()).isPresent();
        
        if(mobilAlleredeRegistrert) {
        	redirectAtt.addFlashAttribute("feil", true);
        	redirectAtt.addFlashAttribute("errorMessage", 
        			"Mobilnummer er allerede registrert, legg inn nytt nummer");
        	return "redirect:/paamelding";
        }
        
        deltagerRepository.save(deltager);
    		redirectAtt.addFlashAttribute("deltager", deltager);
        
    	return "redirect:/paameldt";
    }
    
    @GetMapping("/paameldt")
    public String visPaameldt(Model model) {
        return "paameldt"; 
    }
    
    @GetMapping("/deltagerliste")
    public String visDeltagerListe(
            Model model,
            HttpSession session,
            RedirectAttributes redirectAtt) {
        if (!loggInn.erBrukerInnlogget(session)) {
            redirectAtt.addFlashAttribute("redirectMessage", "Du må være innlogget for å kunne se deltagerlisten.");
            return "redirect:/innlogging";
        }

        List<Deltager> deltagere = deltagerRepository.findAll();

        Collections.sort(deltagere, Comparator
                .comparing(Deltager::getFornavn, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                .thenComparing(Deltager::getEtternavn, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)));
        model.addAttribute("deltagere", deltagere);

        return "deltagerliste";
    }
    
    @GetMapping("/innlogging")
    	public String visInnlogging(){
    		return "logginn";
    }
    
    @PostMapping("/innlogging")
    public String loggInn(
            HttpSession session,
            RedirectAttributes redirectAtt,
            HttpServletRequest request,
            @RequestParam String mobil,
            @RequestParam String passord) {
    	
        Deltager deltager = deltagerRepository.findById(mobil).orElse(null);

        if (deltager == null) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Fant ingen deltager med dette mobilnummeret.");
            return "redirect:/innlogging";
        }
        boolean riktigPassord = passordService.erKorrektPassord(
                passord,
                deltager.getSalt(),
                deltager.getPassord()
        );
        if (!riktigPassord) {
            redirectAtt.addFlashAttribute("feil", true);
            redirectAtt.addFlashAttribute("errorMessage", "Feil passord. Prøv igjen.");
            return "redirect:/innlogging";
        }
        session.setAttribute("innloggetDeltager", deltager);
        loggInn.loggInnBruker(request, mobil);

        return "redirect:/deltagerliste";
    }
    
	@PostMapping("/loggut")
    public String loggUt(
    		HttpSession session, 
    		RedirectAttributes redirectAtt) {
		
		loggInn.loggUtBruker(session);

		redirectAtt.addFlashAttribute("redirectMessage", "Du er nå logget ut");
		return "redirect:/innlogging";
    }
}
