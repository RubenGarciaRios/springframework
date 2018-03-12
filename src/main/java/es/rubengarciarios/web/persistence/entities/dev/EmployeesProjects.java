package es.rubengarciarios.web.persistence.entities.dev;

import javax.persistence.*;

@Entity
@Table( name = "employees_projects", schema = "dev", catalog = "" )
public class EmployeesProjects {
    private int id;
    private Employees employeesByIdEmployee;
    private int idEmployee;
    private int idProject;
    private Projects projectsByIdProject;

    @Id
    @Column( name = "id" )
    public int getId( ) {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;

        EmployeesProjects that = ( EmployeesProjects ) o;

        return id == that.id;
    }

    @Override
    public int hashCode( ) {
        return id;
    }

    @ManyToOne
    @JoinColumn( name = "id_employee", referencedColumnName = "id", nullable = false )
    public Employees getEmployeesByIdEmployee( ) {
        return employeesByIdEmployee;
    }

    public void setEmployeesByIdEmployee( Employees employeesByIdEmployee ) {
        this.employeesByIdEmployee = employeesByIdEmployee;
    }

    @Basic
    @Column( name = "id_employee", insertable = false, updatable = false )
    public int getIdEmployee( ) {
        return idEmployee;
    }

    public void setIdEmployee( int idEmployee ) {
        this.idEmployee = idEmployee;
    }

    @Basic
    @Column( name = "id_project", insertable = false, updatable = false )
    public int getIdProject( ) {
        return idProject;
    }

    public void setIdProject( int idProject ) {
        this.idProject = idProject;
    }

    @ManyToOne
    @JoinColumn( name = "id_project", referencedColumnName = "id", nullable = false )
    public Projects getProjectsByIdProject( ) {
        return projectsByIdProject;
    }

    public void setProjectsByIdProject( Projects projectsByIdProject ) {
        this.projectsByIdProject = projectsByIdProject;
    }
}
