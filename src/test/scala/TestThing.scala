import java.util.UUID

import org.javachannel.implicits.service.impl.GraphServiceImpl

/**
 * Created by Joseph on 12/29/2014.
 */
object TestThing {
  def main(args: Array[String]) {
    val service=new GraphServiceImpl
    val ontology=service.createOntology("test ontology", "Joseph Ottinger")
    println(ontology)
    println(service.findOntologyByName("test ontology"))
    ontology.get.setName("my ontology")
    println(ontology)
    println(service.updateOntology(ontology.get, "Joseph Schmoo"))
    println(service.findOntologyNames())
  }

}
