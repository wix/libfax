/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.fax.model

import scala.util.Try

/**
 * The Fax trait, which allows sending fax documents and querying their status.
 * Fax providers should subclass and implement this trait.
 */
trait Fax {
  /**
   * @return the fax provider's unique ID.
   */
  def getId: String

  /**
   * Sends a new fax document.
   *
   * @param to     The intended recipient's phone number in E.164 format.
   * @param html   The fax content in HTML format.
   * @return a provider-specific fax document ID on success, or any kind of FaxException on error
   */
  def send(to: String, html: String): Try[String]

  /**
   * Retrieves the current status for a previously sent fax document.
   *
   * @param documentId   The fax document ID as returned by the send method.
   * @return the fax document's current status, or any kind of FaxException on error
   */
  def retrieveStatus(documentId: String): Try[String]

  /**
   * Retrieves the current statuses for multiple previously sent fax documents.
   * Fax providers may implement this as a single request, or as a series of retrieveStatus requests.
   *
   * @param documentIds   The fax document IDs as returned by the send method.
   * @return map between each fax document ID to its current status, or any kind of FaxException on error
   */
  def retrieveStatuses(documentIds: Iterable[String]): Try[Map[String, String]]
}

object Status {
  val pending = "pending"
  val failed = "failed"
  val sent = "sent"
}