package org.javachannel.implicits.service.impl

import java.util.UUID

import org.anormcypher.CypherParser._
import org.anormcypher.{Cypher, Neo4jREST, ~}
import org.javachannel.implicits.model.Ontology
import org.javachannel.implicits.service.GraphService

class GraphServiceImpl extends GraphService {
  val graphDb = init

  def init = {
    for (t <- Set("Ontology", "Concept", "Reference")) {
      for (i <- Set("id", "name")) {
        println(s"CREATE INDEX ON :$t($i)")
        Cypher(s"CREATE INDEX ON :$t($i)")
      }
    }
    Neo4jREST
  }

  override def toString = {
    s"GraphServiceImpl{$graphDb}"
  }

  override def findOntologyById(id: UUID): Option[Ontology] = {
    val ontologies: List[String ~ String] = {
      Cypher( s"MATCH (n:Ontology {id:'$id'}) return n.id, n.name")
        .as(str("n.id") ~ str("n.name") *)
    }
    if (ontologies.length > 0) {
      val o = new Ontology(UUID.fromString(ontologies(0)._1), ontologies(0)._2)
      Option(o)
    } else None
  }

  override def findOntologyByName(name: String): Option[Ontology] = {
    val ontologies: List[String ~ String] = {
      Cypher( s"MATCH (n:Ontology {name:'$name'}) return n.id, n.name")
        .as(str("n.id") ~ str("n.name") *)
    }
    if (ontologies.length > 0) {
      val o = new Ontology(UUID.fromString(ontologies(0)._1), ontologies(0)._2)
      Option(o)
    } else None
  }

  override def createOntology(name: String): Option[Ontology] = {
    val ontology = findOntologyByName(name)
    ontology match {
      case Some(o) => ontology
      case None =>
        val uuid = UUID.randomUUID()
        Cypher(
          s"create (n:Ontology { id:{id}, name:{name}})")
          .on("id" -> uuid.toString, "name" -> name).execute()
        Option(new Ontology(uuid, name))
    }
  }

}
