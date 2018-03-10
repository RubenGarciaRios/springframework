package es.rubengarciarios.web.controllers;

import es.rubengarciarios.web.persistence.services.dev.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;
}
