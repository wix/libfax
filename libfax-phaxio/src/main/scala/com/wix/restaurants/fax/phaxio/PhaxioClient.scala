package com.wix.restaurants.fax.phaxio

import com.google.api.client.http.{GenericUrl, HttpRequestFactory, HttpResponse, UrlEncodedContent}
import com.twitter.util.{Return, Throw, Try}
import com.wix.restaurants.fax.phaxio.model.{Fax, FaxInfo, FaxStatusResponseParser, SendResponseParser}

import scala.collection.JavaConversions._
import scala.concurrent.duration.Duration

class PhaxioClient(requestFactory: HttpRequestFactory,
                   endpoint: String,
                   connectTimeout: Option[Duration] = None,
                   readTimeout: Option[Duration] = None,
                   credentials: Credentials) {
  private val helper = new PhaxioHelper
  private val sendResponseParser = new SendResponseParser
  private val faxStatusResponseParser = new FaxStatusResponseParser

  def send(to: String, html: String, cancelTimeout: Duration): Try[FaxInfo] = {
    val params = helper.createSendParams(
      credentials = credentials,
      to = to,
      html = html,
      cancelTimeout = cancelTimeout
    )

    val sendResponseJson = doRequest("send", params)
    val sendResponse = sendResponseParser.parse(sendResponseJson)

    if (sendResponse.success) {
      Return(sendResponse.data.get)
    } else {
      Throw(new PhaxioException(sendResponse.message))
    }
  }

  def faxStatus(id: Long): Try[Fax] = {
    val params = helper.createFaxStatusParams(
      credentials = credentials,
      id = id
    )

    val faxStatusResponseJson = doRequest("faxStatus", params)
    val faxStatusResponse = faxStatusResponseParser.parse(faxStatusResponseJson)

    if (faxStatusResponse.success) {
      Return(faxStatusResponse.data.get)
    } else {
      Throw(new PhaxioException(faxStatusResponse.message))
    }
  }

  private def doRequest(resource: String, params: Map[String, String]): String = {
    val httpRequest = requestFactory.buildPostRequest(
      new GenericUrl(endpoint + resource),
      new UrlEncodedContent(mapAsJavaMap(params))
    )

    connectTimeout foreach (connectTimeout => httpRequest.setConnectTimeout(connectTimeout.toMillis.toInt))
    readTimeout foreach (readTimeout => httpRequest.setReadTimeout(readTimeout.toMillis.toInt))

    val httpResponse = httpRequest.execute()
    extractAndCloseResponse(httpResponse)
  }

  private def extractAndCloseResponse(httpResponse: HttpResponse): String = {
    try {
      httpResponse.parseAsString()
    } finally {
      httpResponse.ignore()
    }
  }
}