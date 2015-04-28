package com.wix.restaurants.fax.phaxio.model

/**
 * @see http://www.phaxio.com/docs/api/send/sendFax/
 * @see http://www.phaxio.com/docs/api/general/faxStatus/
 */
object Params {
  val to = "to"
  val filename = "filename"
  val stringData = "string_data"
  val stringDataType = "string_data_type"
  val batch = "batch"
  val batchDelay = "batch_delay"
  val batchCollisionAvoidance = "batch_collision_avoidance"
  val callbackUrl = "callback_url"
  val cancelTimeout = "cancel_timeout"
  val callerId = "caller_id"
  val apiKey = "api_key"
  val apiSecret = "api_secret"
  val id = "id"
}