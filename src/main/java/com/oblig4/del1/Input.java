package com.oblig4.del1;

import org.springframework.stereotype.Service;

@Service
public class Input {

    private final String bokstaverLov = "[a-zA-ZæøåÆØÅ-]";
    private final String bindestrekOgMellomrom = "[a-zA-ZæøåÆØÅ\\s-]";
    private final String tall = "[0-9]";
    private final String toTilTyve = "{2,20}";
    private final String aatte = "{8}";
    private final String seksEllerMer= "{6,}"; 


    public boolean isValidFornavn(String fornavn) {
        if (fornavn == null) {
            return false;
        }
        return fornavn.matches("^[A-ZÆØÅ]" + bindestrekOgMellomrom + toTilTyve + "$");
    }

    public boolean isValidEtternavn(String etternavn) {
        if (etternavn == null) {
            return false;
        }
        return etternavn.matches("^[A-ZÆØÅ]" + bokstaverLov + toTilTyve + "$");
    }

    public boolean isValidMobil(String mobil) {
        if (mobil == null) {
            return false;
        }
        return mobil.matches("^" + tall + aatte+ "$");
    }

    public boolean isValidPassord(String passord) {
        if (passord == null) {
            return false;
        }
        return passord.matches("^(?=.*[a-zA-Z])(?=.*[0-9])." + seksEllerMer + "$");
    }

    public boolean isValidKjonn(String kjonn) {
        if (kjonn == null) {
            return false;
        }
        return kjonn.equalsIgnoreCase("mann") || kjonn.equalsIgnoreCase("kvinne");
    }
}