package com.ibolsa.api.domain.pg.usuario;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Perfil extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

//    @ElementCollection(fetch=FetchType.EAGER)
//    @CollectionTable(name="perfil_acesso")
//    private Set<Integer> perfilAcesso = new HashSet<>();
}
