
import java.io.{File, PrintWriter}
import java.util

import scala.io.Source

object Join {
  def main(args: Array[String]): Unit = {

    val inputfile = "result20200729.txt"
    val inputfile2 = "21datawithsha"
    val outfile = "test3"
    val splitSymbol = ","


    val writer = new PrintWriter(new File(outfile))
    val set1 = Source.fromFile(inputfile).getLines().toArray.toSet
    val arr = Source.fromFile(inputfile2).getLines().toArray.map(x => x.split(splitSymbol))
    val set2 = arr.map(x => x(3)).toSet



    val inset = set1.intersect(set2).toArray
    println(s"交集个数为${inset.length}")

    val tire = new Trie
    for (line <- inset) {
      tire.insert(line)
    }
    for (lines <- arr) {
      if (tire.search(lines(3))){
        writer.write(s"${lines.mkString(splitSymbol)}\n")

      }
    }
    writer.close()
  }
}

class TrieNode() {
  var path: Int = 0
  var end: Int = 0
  var next: util.HashMap[Character, TrieNode] = new util.HashMap[Character, TrieNode]
}

object Trie {
  def main(args: Array[String]): Unit = {
    val tree = new Trie
    tree.insert("asd")
    tree.insert("abd")
    tree.insert("abc")

    println(tree.search("asd"))
    println(tree.search("abc"))
  }
}

class Trie() {
  private var root = new TrieNode

  def insert(word: String): Unit = {
    if (word == null || word == "") return
    var node = root
    for (i <- 0 until word.length) {
      val ch = word.charAt(i)
      if (!node.next.containsKey(ch)) node.next.put(ch, new TrieNode)
      node = node.next.get(ch)
      node.path += 1
    }
    node.end += 1
  }

  def search(word: String): Boolean = {
    if (word == null || word == "") return false
    var node = root
    for (i <- 0 until word.length) {
      val ch = word.charAt(i)
      if (!node.next.containsKey(ch)) return false
      node = node.next.get(ch)
    }
    if (node.end == 0) return false
    true
  }

  def startsWith(word: String): Boolean = {
    if (word == null || word == "") return false
    var node = root
    for (i <- 0 until word.length) {
      val ch = word.charAt(i)
      if (!node.next.containsKey(ch)) return false
      node = node.next.get(ch)
    }
    true
  }
}
