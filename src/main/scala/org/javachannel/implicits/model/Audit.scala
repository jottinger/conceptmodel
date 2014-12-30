package org.javachannel.implicits.model

import java.util.Date

class Audit extends BaseEntity {
  var notation:Option[String]=None
  var modifiedBy:Option[String]=None
  var timestamp:Option[Date]=None
}
