package com.ibolsa.api.domain.municipio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Municipio {

    @Id
    private Long id;

    private String nome;

    @ManyToOne
    private Estado estado;

    public String getDescricao(){
        return String.format("%s-%s", nome, estado.getSigla());
    }
}
