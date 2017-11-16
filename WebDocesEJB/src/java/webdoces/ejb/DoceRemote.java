package webdoces.ejb;

import java.util.List;
import javax.ejb.Remote;
import webdoces.modelo.Doce;

/**
 * Interface remota do EJB de Doce.
 *
 * @author Rafael Guimarães Sakurai
 */
@Remote
public interface DoceRemote {
  /**
   * Salva ou atualiza os dados do doce.
   *
   * @param doce - Objeto doce que será salvo ou atualizado.
   * @return O objeto doce após ser salvo.
   * @throws Exception - Lançado caso ocorra algum erro.
   */
  public Doce salvar(Doce doce) throws Exception;

  /**
   * Remove um doce a partir de seu id.
   *
   * @param id
   */
  public void remover(Long id);

  /**
   * Consulta todos os doces.
   *
   * @return uma lista com os doces cadastrados.
   */
  public List<Doce> consultarTodos();
}
