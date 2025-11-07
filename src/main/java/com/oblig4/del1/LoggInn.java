package com.oblig4.del1;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoggInn {

    public void loggUtBruker(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }

    public void loggInnBruker(HttpServletRequest request, String mobil) {
        HttpSession sesjon = request.getSession(true); 
        sesjon.setAttribute("mobil", mobil); 
        sesjon.setMaxInactiveInterval(20);
    }

    public boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("mobil") != null;
    }
}