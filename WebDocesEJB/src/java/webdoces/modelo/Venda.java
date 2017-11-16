package webdoces.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe para representar uma Venda de doces.
 *
 * @author Rafael Guimarães Sakurai
 */
@Entity
@NamedQueries({
  @NamedQuery(name="Venda.consultarPorPeriodo",
        query="SELECT v FROM Venda v WHERE v.data >= :inicio and v.data <= :fim ")
})
@SequenceGenerator(name="VENDA_SEQ", sequenceName="VENDA_SEQ", allocationSize=1, initialValue=1)
public class Venda implements Serializable {
  private static final long serialVersionUID = 9055359278465871281L;

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VENDA_SEQ") // Versão para Oracle.
  private Long id;

  @Temporal(TemporalType.DATE)
  private Date data;

  @OneToMany(cascade=CascadeType.REFRESH)
  @JoinTable(name="Venda_Doce",
          joinColumns={@JoinColumn(name="venda_id")},
          inverseJoinColumns={@JoinColumn(name="doce_id")})
  private List<Doce> doces;

  private Double valorTotal;

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public List<Doce> getDoces() {
    return doces;
  }

  public void setDoces(List<Doce> doces) {
    this.doces = doces;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
  }
}
