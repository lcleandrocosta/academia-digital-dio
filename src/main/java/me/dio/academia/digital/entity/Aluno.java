package me.dio.academia.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data // Anotação do lombok, não precisa declarar os métodos get,set,hashcode
@NoArgsConstructor // Anotação do lombok, cria um construtor vazio, pois o hibernate necessita
@AllArgsConstructor // Anotação do lombok, construtor com todos os atributos
@Entity // Anotação JPA, para persistência no BD, solicita que seja identificada a coluna chave primária(@Id)
@Table(name = "tb_alunos") //Identifica a tabela
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Referente ao FetchType LAZY
public class Aluno {

  @Id // Identifica a coluna como chave primária
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática da PK
  private Long id;

  private String nome;

  @Column(unique = true)
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY) // Relacionamento entre tabelas
  @JsonIgnore // Trata possível exceção
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

}
