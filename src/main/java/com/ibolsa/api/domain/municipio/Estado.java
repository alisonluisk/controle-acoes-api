package com.ibolsa.api.domain.municipio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estado {

    @Id
    private Long id;

    private String nome;

    private String sigla;
}
