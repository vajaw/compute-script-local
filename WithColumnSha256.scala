import java.io.{File, PrintWriter}

import scala.io.Source

object WithColumnSha256 {

  def main(args: Array[String]): Unit = {

    val inputfile = "21.csv"
    val outfile = "test"
    val splitSymbol = ","

    val writer = new PrintWriter(new File(outfile))
    val iter = Source.fromFile(inputfile).getLines()
    while (iter.hasNext){
      val line = iter.next()
      val lines = line.split(splitSymbol)
      writer.write(s"$line$splitSymbol${sha256(lines(0))}\n")
    }
    writer.close()
  }

  def sha256(s: String): String = {
    // Besides "MD5", "SHA-256", and other hashes are available
    val m = java.security.MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8"))
    m.map("%02x".format(_)).mkString
  }


}
