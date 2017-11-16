package webdoces.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * Classe para representar um Doce.
 *
 * @author Rafael Guimarães Sakurai
 */
@Entity
@NamedQueries({
  @NamedQuery(name="Doce.consultarTodos",
        query="SELECT d FROM Doce d")
})
@SequenceGenerator(name="DOCE_SEQ", sequenceName="DOCE_SEQ", allocationSize=1, initialValue=1)
public class Doce implements Serializable {
  private static final long serialVersionUID = -5390433464807891629L;  

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCE_SEQ") // Versão para Oracle.
  private Long id;
  private String descricao;
  private Double valor;

  public Doce() {

  }

  public Doce(Long id, String descricao, double valor) {
    this.id = id;
    this.descricao = descricao;
    this.valor = valor;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
