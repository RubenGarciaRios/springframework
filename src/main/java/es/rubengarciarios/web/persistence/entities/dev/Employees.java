package es.rubengarciarios.web.persistence.entities.dev;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table( name = "employees", schema = "dev", catalog = "" )
public class Employees implements Serializable {
    private static final long serialVersionUID = 0x1L;

    private int id;
    private String name;
    private String firstname;
    private String lastname;
    private String gender;
    private Date birthdate;
    private Date incorporationdate;
    private Date leavingdate;
    private boolean active;
    private Departaments departamentsByIdDepartament;
    private int idDepartament;
    private Collection< EmployeesProjects > employeesProjectsById;

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
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

    @Basic
    @Column( name = "firstname" )
    public String getFirstname( ) {
        return firstname;
    }

    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    @Basic
    @Column( name = "lastname" )
    public String getLastname( ) {
        return lastname;
    }

    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

    @Basic
    @Column( name = "gender", length = 1, columnDefinition = "CHAR" )
    public String getGender( ) { return gender; }

    public void setGender( String gender ) { this.gender = gender; }

    @Basic
    @Column( name = "birthdate" )
    public Date getBirthdate( ) {
        return birthdate;
    }

    public void setBirthdate( Date birthdate ) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column( name = "incorporationdate" )
    public Date getIncorporationdate( ) {
        return incorporationdate;
    }

    public void setIncorporationdate( Date incorporationdate ) {
        this.incorporationdate = incorporationdate;
    }

    @Basic
    @Column( name = "leavingdate" )
    public Date getLeavingdate( ) {
        return leavingdate;
    }

    public void setLeavingdate( Date leavingdate ) {
        this.leavingdate = leavingdate;
    }

    @Basic
    @Column( name = "active" )
    public boolean isActive( ) {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;

        Employees employees = ( Employees ) o;

        return id == employees.id && active == employees.active && ( name != null ? name.equals( employees.name ) :
                employees.name == null ) && ( firstname != null ? firstname.equals( employees.firstname ) : employees
                .firstname == null ) && ( lastname != null ? lastname.equals( employees.lastname ) : employees
                .lastname == null ) && ( gender != null ? gender.equals( employees.gender ) : employees.gender ==
                null ) && ( birthdate != null ? birthdate.equals( employees.birthdate ) : employees.birthdate == null
        ) && ( incorporationdate != null ? incorporationdate.equals( employees.incorporationdate ) : employees
                .incorporationdate == null ) && ( leavingdate != null ? leavingdate.equals( employees.leavingdate ) :
                employees.leavingdate == null );
    }

    @Override
    public int hashCode( ) {
        int result = id;
        result = 31 * result + ( name != null ? name.hashCode( ) : 0 );
        result = 31 * result + ( firstname != null ? firstname.hashCode( ) : 0 );
        result = 31 * result + ( lastname != null ? lastname.hashCode( ) : 0 );
        result = 31 * result + ( gender != null ? gender.hashCode( ) : 0 );
        result = 31 * result + ( birthdate != null ? birthdate.hashCode( ) : 0 );
        result = 31 * result + ( incorporationdate != null ? incorporationdate.hashCode( ) : 0 );
        result = 31 * result + ( leavingdate != null ? leavingdate.hashCode( ) : 0 );
        result = 31 * result + ( active ? 1 : 0 );
        return result;
    }

    @ManyToOne
    @JoinColumn( name = "id_departament", referencedColumnName = "id", nullable = false )
    public Departaments getDepartamentsByIdDepartament( ) {
        return departamentsByIdDepartament;
    }

    public void setDepartamentsByIdDepartament( Departaments departamentsByIdDepartament ) {
        this.departamentsByIdDepartament = departamentsByIdDepartament;
    }

    @Basic
    @Column( name = "id_departament", insertable = false, updatable = false )
    public int getIdDepartament( ) {
        return idDepartament;
    }

    public void setIdDepartament( int idDepartament ) {
        this.idDepartament = idDepartament;
    }

    @OneToMany( mappedBy = "employeesByIdEmployee" )
    public Collection< EmployeesProjects > getEmployeesProjectsById( ) {
        return employeesProjectsById;
    }

    public void setEmployeesProjectsById( Collection< EmployeesProjects > employeesProjectsById ) {
        this.employeesProjectsById = employeesProjectsById;
    }
}
