import java.util.UUID

import org.javachannel.implicits.service.impl.GraphServiceImpl

/**
 * Created by Joseph on 12/29/2014.
 */
object TestThing {
  def main(args: Array[String]) {
    val service=new GraphServiceImpl
    val ontology=service.createOntology("test ontology")
    println(ontology)
    println(service.findOntologyByName("test ontology"))
  }

}
