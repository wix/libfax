package com.wix.restaurants.fax.interfax.sl.model

import java.io.{StringReader, StringWriter}
import javax.xml.bind.JAXBContext

class QueryResultParser {
  val context = JAXBContext.newInstance(classOf[QueryResult])
  val marshaller = context.createMarshaller()
  val unmarshaller = context.createUnmarshaller()

  def stringify(obj: QueryResult): String = {
    val writer = new StringWriter()
    try {
      marshaller.marshal(obj, writer)
    } finally {
      writer.close()
    }
    writer.toString
  }

  def parse(xml: String): QueryResult = {
    val reader = new StringReader(xml)
    try {
      unmarshaller.unmarshal(reader).asInstanceOf[QueryResult]
    } finally {
      reader.close()
    }
  }
}
