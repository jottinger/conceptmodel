package org.javachannel.implicits.model

import java.util.UUID

import org.javachannel.implicits.model
import org.neo4j.graphdb.Node

class Ontology extends BaseEntity with HasConcepts {
  def this(id:UUID, name:String)= {
    this()
    setId(id)
    setName(name)
  }
  override def toString={
    s"Ontology[$id,$name]"
  }
}
