package lib.hibernate_tests;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TestBookWeight {

    private int weight;

    @Column(name = "weight", nullable = true)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
