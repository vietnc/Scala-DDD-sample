package sep.com.bbs.domain.shared

trait iRepository[V<: ID,U] {

  def findById(id : V): Option[U]

  def findAll(): List[U]

  def store(article: U)

}