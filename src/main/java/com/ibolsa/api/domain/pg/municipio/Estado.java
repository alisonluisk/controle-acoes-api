package com.ibolsa.api.domain.pg.municipio;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estado  extends BaseEntity {

    @Id
    private Long id;

    private String nome;

    private String sigla;
}
