package es.rubengarciarios.web.controllers.restful;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.persistence.services.dev.EnterprisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@CrossOrigin( origins = "*", maxAge = 3600 )
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
            return this.enterprisesService.findOne( id ); }
    }

    @RequestMapping( method = RequestMethod.POST )
    ResponseEntity< ? > save( @Valid @RequestBody Enterprises enterprise ) {
        return new ResponseEntity< Enterprises >( enterprisesService.save( enterprise ), HttpStatus.CREATED ); }

    @RequestMapping( method = RequestMethod.PUT, value = "/{id}" )
    ResponseEntity< ? > update( @PathVariable("id") String id, @Valid @RequestBody Enterprises enterprise ) {
        try {
            enterprise.setId( this.enterprisesService.findOne( Integer.parseInt( id ) ).getId( ) );
        } catch ( Exception e ) {
            enterprise.setId( this.enterprisesService.findOne( id ).getId( ) ); }
        return new ResponseEntity< Enterprises >( enterprisesService.update( enterprise ), HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.PUT )
    ResponseEntity< ? > update( @Valid @RequestBody Enterprises enterprise ) {
        return new ResponseEntity< Enterprises >( enterprisesService.update( enterprise ), HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/{id}" )
    void delete( @PathVariable String id ) {
        try {
            this.enterprisesService.delete( Integer.parseInt( id ) );
        } catch ( Exception e ) {
            this.enterprisesService.delete( id ); }
    }

    @RequestMapping( method = RequestMethod.DELETE )
    void delete( @RequestBody( required = false ) Object obj ) {
        if ( obj == null )
            this.enterprisesService.deleteAll( );
        try {
            Enterprises enterprise = ( Enterprises ) obj;
            this.enterprisesService.delete( enterprise );
        } catch ( Exception e ) {
            try {
                /*
                ObjectMapper objectMapper = new ObjectMapper( );
                List< Enterprises > enterprises = objectMapper.readValue( obj.toString( ), new TypeReference< List< Enterprises > >( ){ } );
                this.enterprisesService.delete( enterprises );*/
            } catch ( Exception ioe ) { }
        }
    }
}
