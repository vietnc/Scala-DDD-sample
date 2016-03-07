package sep.com.bbs.domain.service

trait TransferDTO[DTO,DomainObj] {
  def loadDTO(dto : DTO): DomainObj

  def getDTO(ar: DomainObj): DTO
}
