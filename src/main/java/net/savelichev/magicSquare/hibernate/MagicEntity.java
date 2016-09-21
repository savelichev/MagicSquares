package net.savelichev.magicSquare.hibernate;

import javax.persistence.*;


/**
 * Entity for saving into database through the hibernate.
 */
@Entity
@Table(name = "magic", schema = "public", catalog = "squaress")
public class MagicEntity {

    /**
     * Result id.
     */
    private int id;
    /**
     * Current result to save.
     */
    private String result;

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="magic_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MagicEntity that = (MagicEntity) o;

        if (id != that.id) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
