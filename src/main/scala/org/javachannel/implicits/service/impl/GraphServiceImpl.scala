package org.javachannel.implicits.service.impl

import java.util.UUID

import org.anormcypher.CypherParser._
import org.anormcypher.{Cypher, Neo4jREST, ~}
import org.javachannel.implicits.model.Ontology
import org.javachannel.implicits.service.GraphService

class GraphServiceImpl extends GraphService {
  val graphDb = init

  def init = {
    for (t <- Set("Ontology", "Concept", "Reference", "Audit")) {
      for (i <- Set("id", "name")) {
        println(s"CREATE INDEX ON :$t($i)")
        Cypher(s"CREATE INDEX ON :$t($i)").execute()
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

  def addAudit(id:UUID, name:String, notation:String) = {
    val auditId=UUID.randomUUID()
    val now=System.currentTimeMillis()

    Cypher(s"create (n:Audit { id:'$auditId', modifiedBy:'$name', notation:'$notation', timestamp:'$now'})").execute()
    Cypher(s"match (n),(a:Audit) where n.id='$id' and (a.id='$auditId') create (n)-[r:AUDIT]->(a)").execute()
  }

  override def createOntology(name: String, username:String): Option[Ontology] = {
    val ontology = findOntologyByName(name)
    ontology match {
      case Some(o) => ontology
      case None =>
        val uuid = UUID.randomUUID()
        Cypher(
          s"create (n:Ontology { id:{id}, name:{name}})")
          .on("id" -> uuid.toString, "name" -> name).execute()
        addAudit(uuid, username, "create")
        Option(new Ontology(uuid, name))
    }
  }

}
