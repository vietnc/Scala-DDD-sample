package org.com.bbs.domain.shared

/**
 * Created by Viet on 2/23/2016.
 */
trait Specification[T] {
 def isSatisfiedBy(t: T): Boolean
}
