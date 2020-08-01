package br.com.colaboradoresapi.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cargo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
}