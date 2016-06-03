package com.thens.example.dto;

import com.thens.example.domain.Foo;
import com.thens.generic.util.GenericDTO;

import java.io.Serializable;

/**
 * Created by Enes KURU on 3.6.2016.
 */
public class FooDTO implements Serializable, GenericDTO<Foo> {
    private Integer id;

    private String foo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FooDTO foo = (FooDTO) o;

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

    public Foo toEntity() {
        Foo foo = new Foo();

        foo.setId(getId());
        foo.setFoo(getFoo());

        return foo;
    }
}
