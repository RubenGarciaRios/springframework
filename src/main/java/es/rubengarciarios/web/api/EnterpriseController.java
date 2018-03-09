package es.rubengarciarios.web.api;

import es.rubengarciarios.web.persistence.services.dev.interfaces.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;
}
