package com.thens.example.domain;

import com.thens.example.dto.FooDTO;
import com.thens.generic.util.GenericEntity;

import javax.persistence.*;

/**
 * Created by Enes KURU on 3.6.2016.
 */
@Entity
@Table(name = "FOO")
public class Foo implements GenericEntity<FooDTO>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "FOO")
    private String foo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foo foo = (Foo) o;

        return !(id != null ? !id.equals(foo.id) : foo.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public FooDTO toDTO() {
        FooDTO dto = new FooDTO();

        dto.setId(getId());
        dto.setFoo(getFoo());

        return dto;
    }
}
