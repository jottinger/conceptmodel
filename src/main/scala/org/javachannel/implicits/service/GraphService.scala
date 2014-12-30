package org.javachannel.implicits.service

import java.util.UUID

import org.javachannel.implicits.model.Ontology
import org.neo4j.graphdb.DynamicLabel
import org.neo4j.graphdb.factory.GraphDatabaseFactory

trait GraphService {
  def findOntologyById(id: UUID): Option[Ontology]

  def findOntologyByName(name: String): Option[Ontology]

  def createOntology(name:String, username:String):Option[Ontology]

  def updateOntology(ontology:Ontology, username:String):Option[Ontology]

  def removeOntology(ontology:Ontology, username:String):Option[Ontology]

  def findOntologyNames():Set[String]
}
