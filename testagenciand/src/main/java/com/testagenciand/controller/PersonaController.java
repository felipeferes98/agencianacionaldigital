package com.testagenciand.controller;

import com.testagenciand.component.PersonaService;
import com.testagenciand.constant.ViewConstant;
import com.testagenciand.dto.Persona;
import com.testagenciand.entity.PersonaEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/persona")
public class PersonaController {

    private static final Log LOGGER = LogFactory.getLog(PersonaController.class);

    @Autowired
    @Qualifier("personaService")
    private PersonaService personaService;

    @RequestMapping(value = "/")
    public String redirectToListarPersonas() {
        LOGGER.info("Ingresando a metodo [[redirectToListarPersonas]] ...");

        ModelAndView model = new ModelAndView();
        model.addObject("personas",personaService.listarPersona());
        return "redirect:/persona/listarpersonas";
    }

    @RequestMapping(value = "/listarpersonas")
    public ModelAndView listarPersonas() {
        LOGGER.info("ingresando a metodo [[listarPersonas]] ...");
        ModelAndView model = new ModelAndView();

        model.addObject("personas",personaService.listarPersona());
        model.setViewName(ViewConstant.CONTACTS);

        LOGGER.info("personas: metodo contactos");

        return model;
    }

    @GetMapping(value = "/mostrarPersona/{cedula}")
    public String mostrarPersona(@PathVariable(name = "cedula") String cedula, Model model) {
        LOGGER.info("Ingresando a metodo [[mostrarPersona]] ... cedula: " + cedula);
        Persona persona = personaService.consultarPersona(new Long(cedula));

        model.addAttribute("persona", persona);

        /**
        Persona persona = new Persona();
        if(cedula != null) {
            persona = personaService.consultarPersona(new Long(cedula));
        }
        LOGGER.info("atributo nombre: "+ persona.getNombre() );

        //model.addAttribute("persona", persona);
        //ModelAndView model = new ModelAndView(ViewConstant.CONTACT_FORM);
**/
        return ViewConstant.CONTACT_FORM;
    }

    @RequestMapping(value="/agregarContacto")
    public ModelAndView agregarContacto(@ModelAttribute("persona") Persona persona) {
        LOGGER.info("Ingresando a metodo [[agregarContactos]] ... persona: " +persona);

        ModelAndView model = new ModelAndView(ViewConstant.CONTACT_FORM);
        model.addObject("persona", persona);
        return model;

    }


    @PostMapping(value="/guardarContacto")
    public String guardarContacto(@ModelAttribute(name = "persona") Persona persona) {
        LOGGER.info("Ingresando a metodo [[guardarContacto]] ... Persona: " + persona);
        personaService.guardarPersona(persona);
        return "redirect:/persona/listarpersonas";
    }

    @GetMapping(value="/eliminarPersona/{cedula}")
    public String eliminarContacto(@PathVariable(name = "cedula") String cedula) {
        LOGGER.info("Ingresando a metodo [[eliminarPersona]] ... Persona: " + cedula);
        personaService.eliminarPersona(new Long(cedula));
        return "redirect:/persona/listarpersonas";
    }

    @RequestMapping(value = "/cancel")
    public String cancelPersona() {

        return ViewConstant.CONTACTS;
    }

}