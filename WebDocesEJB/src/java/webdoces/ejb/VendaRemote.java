package webdoces.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import webdoces.modelo.Venda;

/**
 * Interface Remota do EJB de Venda.
 *
 * @author Rafael Guimarães Sakurai
 */
@Remote
public interface VendaRemote {
  /**
   * Salva ou atualiza os dados da venda.
   *
   * @param venda - Objeto venda que será salvo ou atualizado.
   * @return O objeto venda após ser salvo.
   * @throws Exception - Lançado caso ocorra algum erro.
   */
  public Venda salvar(Venda venda) throws Exception;

  /**
   * Remove uma venda a partir de seu id.
   *
   * @param id
   */
  public void remover(Long id);

  /**
   * Consultar todas as vendas em determinado período.
   *
   * @param inicio - Data de inicio.
   * @param fim - Data de fim.
   * @return Uma lista de vendas.
   */
  public List<Venda> consultarPorPeriodo(Date inicio, Date fim);
}
