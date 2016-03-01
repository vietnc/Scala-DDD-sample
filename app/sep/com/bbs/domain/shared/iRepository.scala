package sep.com.bbs.domain.shared

trait iRepository[V<: ID,U] {

  def resolveById(id : V): Option[U]

  def resolveAll(): List[U]

  def store(article: U): Boolean

}