package org.com.bbs.domain.service

trait ConvertEntity[DTO,DomainObj] {
  def toEntity(dto : DTO): DomainObj

  def toDataTrasferObject(ar: DomainObj): DTO
}
