package es.rubengarciarios.web.controllers.restful;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.persistence.services.dev.EnterprisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping( "/enterprises" )
public class EnterprisesController {
    @Autowired
    EnterprisesService enterprisesService;

    @RequestMapping( method = RequestMethod.GET )
    Collection< Enterprises > findAll( ) {
        return this.enterprisesService.findAll( ); }

    @RequestMapping( method = RequestMethod.GET, value = "/{id}" )
    Enterprises findOne( @PathVariable String id ) {
        try {
            return this.enterprisesService.findOne( Integer.parseInt( id ) );
        } catch ( Exception e ) {
            return this.enterprisesService.findOneByName( id ); }
    }

    @RequestMapping( method = RequestMethod.POST )
    ResponseEntity< ? > save( @Valid @RequestBody Enterprises enterprise, UriComponentsBuilder uriComponentsBuilder ) {
        enterprisesService.save( enterprise );
        HttpHeaders headers = new HttpHeaders( );
        headers.setLocation( uriComponentsBuilder.path( "/enterprises/{id}" ).buildAndExpand( enterprise.getName( ) ).toUri( ) );
        return new ResponseEntity< >( headers, HttpStatus.CREATED );
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/{obj}" )
    void delete( @PathVariable Object obj ) {
        try {
            this.enterprisesService.delete( ( Integer ) obj );
        } catch ( Exception e1 ) {
            try {
                this.enterprisesService.delete( ( Enterprises ) obj );
            } catch ( Exception e2 ) {
                try {
                    this.enterprisesService.delete( ( Iterable< Enterprises > ) obj );
                } catch ( Exception e3 ) { }
            }
        }
    }

    @RequestMapping( method = RequestMethod.DELETE )
    void deleteAll( ) { this.enterprisesService.deleteAll( ); }

}
