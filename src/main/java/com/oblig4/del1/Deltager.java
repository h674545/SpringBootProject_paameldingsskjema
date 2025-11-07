package com.oblig4.del1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(schema="deltager", name = "DELTAGER")
public class Deltager {
	
	@Column(name="FORNAVN")
	@Size(min=2, max=20, message="Fornavn må inneholde minst 2 tegn") @NotNull(message = "Fornavn er obligatorisk")
	private String fornavn;
	
	@Column(name="ETTERNAVN")
	 @Size(min=2, max= 20, message="Etternavn må inneholde minst 2 tegn") @NotNull(message = "Etternavn er obligatorisk")
	private String etternavn;
	
	@Id
	@Column(name="MOBIL")
	@Pattern(regexp = "^\\d{8}$", message = "Mobil må være eksakt 8 tegn") @NotNull(message = "Mobil er obligatorisk") 
	private String mobil;
	
	@Column(name="KJONN")
	@NotNull(message = "Kjønn må være valgt") @Pattern(regexp = "^(Kvinne|Mann)$", message = "Kjønn må være Kvinne eller Mann")
	private String kjonn;
	
	@Column(name="PASSORD")
	@NotNull(message = "Passord er påkrev") @Size(min = 8, max = 64, message = "Passord må være mellom 8 og 64 tegn")
	private String passord;
	
	@Column(name="SALT")
	private String salt;
	
	public Deltager() {}
	
	public Deltager(String fornavn, String etternavn, String mobil, String kjonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn; 
		this.mobil = mobil;
		this.kjonn = kjonn;
	}
	
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	public String getFornavn() {
		return fornavn;
	}
	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public String getMobil() {
		return mobil;
	}
	
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public String getKjonn() {
		return kjonn;
	}
	
	public void setPassord(String passord) {
		this.passord = passord;
	}
	public String getPassord() {
		return passord;
	}
	public String getSalt() {
	    return salt;
	}
	public void setSalt(String salt) {
	    this.salt = salt;
	}
	
	@Override
	public String toString() {
	    return "Påmelding er mottatt for: " + fornavn + " " + etternavn + " (" + mobil + ")";
	}

}
