package br.com.colaboradoresapi.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Time {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String imagem;
}