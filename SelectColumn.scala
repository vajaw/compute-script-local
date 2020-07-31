import java.io.{File, PrintWriter}

import scala.io.Source

object SelectColumn {

  def main(args: Array[String]): Unit = {

    val inputfile = "21datawithsha"
    val outfile = "test2"
    val splitSymbol = ","

    val writer = new PrintWriter(new File(outfile))
    val iter = Source.fromFile(inputfile).getLines()
    while (iter.hasNext){
      val line = iter.next()
      val lines = line.split(splitSymbol)
      writer.write(s"${lines(3)}\n")
    }
    writer.close()
  }
}
