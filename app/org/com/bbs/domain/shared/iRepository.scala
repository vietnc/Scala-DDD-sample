package org.com.bbs.domain.shared

import scala.util.Try

trait iRepository[V<: ID,U] {

  def resolveById(id : V): Try[Option[U]]

  def resolveAll(): Try[List[U]]

  def store(article: U): Try[Boolean]

}