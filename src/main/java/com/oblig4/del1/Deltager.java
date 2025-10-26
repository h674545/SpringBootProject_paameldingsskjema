package com.oblig4.del1;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Deltager {
	
	private String fornavn;
	private String etternavn;
	private String mobil;
	private String kjonn;
	private String passord;
	private String passord2;
	
	public Deltager(String fornavn, String etternavn, String mobil, String kjonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn; 
		this.mobil = mobil;
		this.kjonn = kjonn;
	}
	
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public	@Size(min=2, max=20, message="Fornavn må inneholde minst 2 tegn") @NotNull(message = "Fornavn er obligatorisk") String getFornavn() {
		return fornavn;
	}
	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public @Size(min=2, max= 20, message="Etternavn må inneholde minst 2 tegn") @NotNull(message = "Etternavn er obligatorisk") String getEtternavn() {
		return etternavn;
	}
	
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public @Pattern(regexp = "^\\d{8}$", message = "Mobil må være eksakt 8 tegn") @NotNull(message = "Mobil er obligatorisk") String getMobil() {
		return mobil;
	}
	
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public	@NotNull(message = "Kjønn må være valgt") @Pattern(regexp = "^(Kvinne|Mann)$", message = "Kjønn må være Kvinne eller Mann") String getKjonn() {
		return kjonn;
	}
	
	public void setPassord(String passord) {
		this.passord = passord;
	}
	public 	@NotNull(message = "Passord er påkrevd") @Size(min = 8, max = 50, message = "Passord må være mellom 8 og 50 tegn") String getPassord() {
		return passord;
	}
	
	public void setPassord2(String passord2) {
		this.passord2 = passord2;
	}
	public 	@NotNull(message = "Passord må være like") @Size(min = 8, max = 50, message = "Passord må være mellom 8 og 50 tegn") String getPassord2() {
		return passord2;
	}
	
	@Override
	public String toString() {
	    return "Påmelding er mottatt for: " + fornavn + " " + etternavn + " (" + mobil + ")";
	}

}
