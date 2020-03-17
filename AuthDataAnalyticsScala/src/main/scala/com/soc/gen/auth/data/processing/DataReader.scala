package com.soc.gen.auth.data.processing

trait DataReader[S] extends AutoCloseable {

  def read: Seq[S]

}
