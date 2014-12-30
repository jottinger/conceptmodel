package org.javachannel.implicits.model

import java.util.UUID

class BaseEntity {
  var id:Option[UUID]=None
  var name=""
  def this(newId:UUID, newName:String)= {
    this()
    id=Option(newId)
    name=newName
  }
  def setId(newId:UUID)={ id=Option(newId) }
  def setName(newName:String) { name=newName }
}
