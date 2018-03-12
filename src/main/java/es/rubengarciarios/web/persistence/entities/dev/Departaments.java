package es.rubengarciarios.web.persistence.entities.dev;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Departaments {
    private int id;
    private String code;
    private String name;
    private int idEnterprise;
    private Enterprises enterprisesByIdEnterprise;
    private Collection< Employees > employeesById;

    @Id
    @Column( name = "id" )
    public int getId( ) {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "code" )
    public String getCode( ) {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
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

        Departaments that = ( Departaments ) o;

        return id == that.id && ( code != null ? code.equals( that.code ) : that.code == null ) && ( name != null ?
                name.equals( that.name ) : that.name == null );
    }

    @Override
    public int hashCode( ) {
        int result = id;
        result = 31 * result + ( code != null ? code.hashCode( ) : 0 );
        result = 31 * result + ( name != null ? name.hashCode( ) : 0 );
        return result;
    }

    @Basic
    @Column( name = "id_enterprise", insertable = false, updatable = false )
    public int getIdEnterprise( ) {
        return idEnterprise;
    }

    public void setIdEnterprise( int idEnterprise ) {
        this.idEnterprise = idEnterprise;
    }

    @ManyToOne
    @JoinColumn( name = "id_enterprise", referencedColumnName = "id", nullable = false )
    public Enterprises getEnterprisesByIdEnterprise( ) {
        return enterprisesByIdEnterprise;
    }

    public void setEnterprisesByIdEnterprise( Enterprises enterprisesByIdEnterprise ) {
        this.enterprisesByIdEnterprise = enterprisesByIdEnterprise;
    }

    @OneToMany( mappedBy = "departamentsByIdDepartament" )
    public Collection< Employees > getEmployeesById( ) {
        return employeesById;
    }

    public void setEmployeesById( Collection< Employees > employeesById ) {
        this.employeesById = employeesById;
    }
}
