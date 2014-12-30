package org.javachannel.implicits.model

import java.util.UUID

class Concept extends BaseEntity with HasReferences {
  var dataType=""
  var parentId:Option[UUID]=None
  var description=""
}
