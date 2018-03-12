package es.rubengarciarios.web.controllers.rest;

import es.rubengarciarios.web.RESTfulApi;
import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.persistence.repositories.dev.EnterprisesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = RESTfulApi.class )
@WebAppConfiguration
public class EnterprisesControllerTest {
    private MediaType contentType = new MediaType( MediaType.APPLICATION_JSON.getType( ),
                                                   MediaType.APPLICATION_JSON.getSubtype( ),
                                                   Charset.forName( "utf8" ) );
    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    private Enterprises enterprise;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EnterprisesRepository enterprisesRepository;

    @Autowired
    void setConverters( HttpMessageConverter< ? >[] converters ) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList( converters ).stream( )
                .filter( hmc -> hmc instanceof MappingJackson2HttpMessageConverter )
                .findAny( )
                .orElse( null );

        assertNotNull( "the JSON message converter must not be null",
                       this.mappingJackson2HttpMessageConverter );
    }

    @Before
    public void setup( ) throws Exception {
        this.mockMvc = webAppContextSetup( webApplicationContext ).build( );
        this.enterprisesRepository.deleteAllInBatch( );
        this.enterprise = enterprisesRepository.save( new Enterprises( "SOAINT" ) );
    }

    @Test
    public void save( ) throws Exception {
        String name = "TEST";
        mockMvc.perform( post( "/enterprises" )
                                 .content( this.json( new Enterprises( name ) ) )
                                 .contentType( contentType ) )
                .andExpect( status( ).isCreated( ) )
                .andExpect( jsonPath( "$.name", is( name ) ) );
    }

    @Test
    public void findOne( ) throws Exception {
        mockMvc.perform( get( "/enterprises/" + enterprise.getId( ) ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( content( ).contentType( contentType ) )
                .andExpect( jsonPath( "$.id", is( enterprise.getId( ) ) ) )
                .andExpect( jsonPath( "$.name", is( enterprise.getName( ) ) ) );
    }

    @Test
    public void findOneByName( ) throws Exception {
        mockMvc.perform( get( "/enterprises/" + enterprise.getName( ) ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( content( ).contentType( contentType ) )
                .andExpect( jsonPath( "$.name", is( enterprise.getName( ) ) ) );
    }

    /*
    @Test
    public void findAll( ) throws Exception {

    }
    */

    protected String json( Object o ) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage( );
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage );
        return mockHttpOutputMessage.getBodyAsString( );
    }

}
