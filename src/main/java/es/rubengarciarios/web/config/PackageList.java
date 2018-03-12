package es.rubengarciarios.web.config;

public class PackageList {
    //////////////////
    /// COMPONENTS ///
    //////////////////
    public static final String COMPONENTS = "es.rubengarciarios.web";
    ///////////////////
    /// PERSISTENCE ///
    ///////////////////
    public static final String PERSISTENCE = COMPONENTS + ".persistence";
    // ENTITIES
    public static final String PERSISTENCE_ENTITIES = PERSISTENCE + ".entities";
    public static final String PERSISTENCE_ENTITIES_DEV = PERSISTENCE_ENTITIES + ".dev";
    // REPOSITORIES
    public static final String PERSISTENCE_REPOSITORIES = PERSISTENCE + ".repositories";
    public static final String PERSISTENCE_REPOSITORIES_DEV = PERSISTENCE_REPOSITORIES + ".dev";
    // SERVICES
    public static final String PERSISTENCE_SERVICES = PERSISTENCE + ".services";
    public static final String PERSISTENCE_SERVICES_DEV = PERSISTENCE_SERVICES + ".dev";
    //////////////////
    /// PROPERTIES ///
    //////////////////
    public static final String PROPERTIES_APPLICATION = "classpath:META-INF/application.properties";
    public static final String PROPERTIES_GLOBAL_EXCEPTIONS = "classpath:exceptions/global_exceptions.properties";
    public static final String PROPERTIES_PERSISTENCE_EXCEPTIONS = "classpath:exceptions/persistence_exceptions.properties";
}
