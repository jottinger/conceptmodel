package org.javachannel.implicits.model

import java.util.UUID

trait HasReferences {
  var referenceIds:Set[UUID]=Set()
}

trait HasConcepts {
  var conceptIds:Set[UUID]=Set()
}
