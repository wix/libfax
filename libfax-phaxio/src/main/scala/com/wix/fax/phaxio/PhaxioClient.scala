package com.wix.fax.phaxio

import com.google.api.client.http.{GenericUrl, HttpRequestFactory, HttpResponse, UrlEncodedContent}
import com.wix.fax.phaxio.model.{Fax, FaxInfo, FaxStatusResponseParser, SendResponseParser}

import scala.collection.JavaConversions._
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

class PhaxioClient(requestFactory: HttpRequestFactory,
                   endpoint: String,
                   connectTimeout: Option[Duration] = None,
                   readTimeout: Option[Duration] = None,
                   numberOfRetries: Int = 0,
                   credentials: Credentials) {

  def send(to: String, html: String, cancelTimeout: Duration): Try[FaxInfo] = {
    val params = PhaxioHelper.createSendParams(
      credentials = credentials,
      to = to,
      html = html,
      cancelTimeout = cancelTimeout
    )

    val sendResponseJson = doRequest("send", params)
    val sendResponse = SendResponseParser.parse(sendResponseJson)

    if (sendResponse.success) {
      Success(sendResponse.data.get)
    } else {
      Failure(PhaxioException(sendResponse.message))
    }
  }

  def faxStatus(id: Long): Try[Fax] = {
    val params = PhaxioHelper.createFaxStatusParams(
      credentials = credentials,
      id = id
    )

    val faxStatusResponseJson = doRequest("faxStatus", params)
    val faxStatusResponse = FaxStatusResponseParser.parse(faxStatusResponseJson)

    if (faxStatusResponse.success) {
      Success(faxStatusResponse.data.get)
    } else {
      Failure(PhaxioException(faxStatusResponse.message))
    }
  }

  private def doRequest(resource: String, params: Map[String, String]): String = {
    val httpRequest = requestFactory.buildPostRequest(
      new GenericUrl(endpoint + resource),
      new UrlEncodedContent(mapAsJavaMap(params))
    )

    connectTimeout foreach (connectTimeout => httpRequest.setConnectTimeout(connectTimeout.toMillis.toInt))
    readTimeout foreach (readTimeout => httpRequest.setReadTimeout(readTimeout.toMillis.toInt))
    httpRequest.setNumberOfRetries(numberOfRetries)

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
