package es.rubengarciarios.web.config.exceptions;

import es.rubengarciarios.web.config.PackageList;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources( {
        @PropertySource( PackageList.PROPERTIES_GLOBAL_EXCEPTIONS ),
        @PropertySource( PackageList.PROPERTIES_PERSISTENCE_EXCEPTIONS ) } )
public class Config { }
