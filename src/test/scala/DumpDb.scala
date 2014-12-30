import org.anormcypher.{Cypher, Neo4jREST}

object DumpDb {
  def main(args: Array[String]): Unit = {
    val connection = Neo4jREST
    println(connection)
    Cypher("""create (anorm {name:"AnormCypher"}), (test {name:"Test"})""").execute()
    val req=Cypher("start n=node(*) return n.name")
    val stream=req()
    println(stream.map(row => {row[String]("n.name")}).toList)

  }

}
