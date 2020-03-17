package com.soc.gen.auth.data.processing

class AuthDataReader(path: String) extends DataReader[String] {

  private val ioHandler =   io.Source.fromFile(path)

  override def read: Seq[String] = ioHandler.getLines().toIndexedSeq

  override def close(): Unit = ioHandler.close()
}

object AuthDataReader {

  def getReader(path: String): AuthDataReader = new AuthDataReader(path)
}