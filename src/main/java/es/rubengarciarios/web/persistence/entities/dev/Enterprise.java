package es.rubengarciarios.web.persistence.entities.dev;

import javax.persistence.*;
import java.io.Serializable;

import static java.lang.Math.toIntExact;

@Entity
@Table( name = "enterprises" )
public class Enterprise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private long id;
    @Column( name = "name" )
    private String name;

    public Enterprise( ){ }

    public Enterprise( long id, String name ) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString( ) {
        return "Enterprise{" + "id=" + id + ", name='" + name + '\'' + '}'; }

    @Override
    public int hashCode( ) {
        return toIntExact(id); }

    @Override
    public boolean equals( Object obj ) {
        return obj != null && getClass( ) == obj.getClass( ) && id == ( ( Enterprise ) obj ).id; }

    public long getId( ) {
        return id; }

    public void setId( long id ) {
        this.id = id; }

    public String getName( ) {
        return name; }

    public void setName( String name ) {
        this.name = name; }
}
