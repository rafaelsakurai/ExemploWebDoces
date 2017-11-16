package webdoces.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import webdoces.modelo.Venda;

/**
 * Classe para executar as operações no banco de dados relacionados a Venda.
 *
 * @author Rafael Guimarães Sakurai
 */
public class VendaDAO {
private EntityManager em = null;

  /**
   * Construtor que recebe como parametro a EntityManager.
   *
   * @param em
   */
  public VendaDAO(EntityManager em) {
    this.em = em;
  }

  /**
   * Salva ou atualiza os dados da venda.
   *
   * @param venda - Objeto venda que será salvo ou atualizado.
   * @return O objeto venda após ser salvo.
   * @throws Exception - Lançado caso ocorra algum erro.
   */
  public Venda salvar(Venda venda) throws Exception {
    if(venda.getId() == null) {
      em.persist(venda);
    } else {
      if(!em.contains(venda)) {
        if(em.find(Venda.class, venda.getId()) != null) {
          throw new Exception("Erro ao salvar a venda.");
        }
      }

      venda = em.merge(venda);
    }

    return venda;
  }

  /**
   * Remove uma venda a partir de seu id.
   *
   * @param id
   */
  public void remover(Long id) {
    Venda venda = em.find(Venda.class, id);
    em.remove(venda);
  }

  /**
   * Consultar todas as vendas em determinado período.
   *
   * @param inicio - Data de inicio.
   * @param fim - Data de fim.
   * @return Uma lista de vendas.
   */
  public List<Venda> consultarTodos(Date inicio, Date fim) {
    Query q = em.createNamedQuery("Venda.consultarPorPeriodo");
    q.setParameter("inicio", inicio);
    q.setParameter("fim", fim);

    return q.getResultList();
  }
}
