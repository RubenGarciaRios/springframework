package es.rubengarciarios.web.persistence.entities.dev;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table( name = "projects", schema = "dev", catalog = "" )
public class Projects implements Serializable {
    private static final long serialVersionUID = 0x1L;

    private int id;
    private String name;
    private Date startdate;
    private Date enddate;
    private boolean active;
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
    @Column( name = "startdate" )
    public Date getStartdate( ) {
        return startdate;
    }

    public void setStartdate( Date startdate ) {
        this.startdate = startdate;
    }

    @Basic
    @Column( name = "enddate" )
    public Date getEnddate( ) {
        return enddate;
    }

    public void setEnddate( Date enddate ) {
        this.enddate = enddate;
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

        Projects projects = ( Projects ) o;

        return id == projects.id && active == projects.active && ( name != null ? name.equals( projects.name ) :
                projects.name == null ) && ( startdate != null ? startdate.equals( projects.startdate ) : projects
                .startdate == null ) && ( enddate != null ? enddate.equals( projects.enddate ) : projects.enddate ==
                null );
    }

    @Override
    public int hashCode( ) {
        int result = id;
        result = 31 * result + ( name != null ? name.hashCode( ) : 0 );
        result = 31 * result + ( startdate != null ? startdate.hashCode( ) : 0 );
        result = 31 * result + ( enddate != null ? enddate.hashCode( ) : 0 );
        result = 31 * result + ( active ? 1 : 0 );
        return result;
    }

    @OneToMany( mappedBy = "projectsByIdProject" )
    public Collection< EmployeesProjects > getEmployeesProjectsById( ) {
        return employeesProjectsById;
    }

    public void setEmployeesProjectsById( Collection< EmployeesProjects > employeesProjectsById ) {
        this.employeesProjectsById = employeesProjectsById;
    }
}
