package br.com.colaboradoresapi.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Colaborador {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Lob
    @Column(columnDefinition="text")
    private String foto;

    private String endereco;

    @OneToOne(cascade = {CascadeType.MERGE})
    private Cargo cargo;

    @OneToOne(cascade = {CascadeType.MERGE})
    private Time time;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Competencia> competencias;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Experiencia> experiencias;

    @OneToMany(cascade = {CascadeType.MERGE})
    private List<Contato> contatos;
}