package es.rubengarciarios.web.persistence.entities.dev;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Enterprises {
    private int id;
    private String name;
    private Collection< Departaments > departamentsById;

    public Enterprises( ) { }
    public Enterprises( String name ) { this.name = name; }
    public Enterprises( int id, String name ) {
        this.id = id;
        this.name = name;
    }
    public Enterprises( int id, String name, Collection< Departaments > departamentsById ) {
        this.id = id;
        this.name = name;
        this.departamentsById = departamentsById;
    }

    @Id
    @Column( name = "id" )
    public int getId( ) {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "name" )
    public String getName( ) {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;

        Enterprises that = ( Enterprises ) o;

        return id == that.id && ( name != null ? name.equals( that.name ) : that.name == null );
    }

    @Override
    public int hashCode( ) {
        int result = id;
        result = 31 * result + ( name != null ? name.hashCode( ) : 0 );
        return result;
    }

    @OneToMany( mappedBy = "enterprisesByIdEnterprise" )
    public Collection< Departaments > getDepartamentsById( ) {
        return departamentsById;
    }

    public void setDepartamentsById( Collection< Departaments > departamentsById ) {
        this.departamentsById = departamentsById;
    }
}
