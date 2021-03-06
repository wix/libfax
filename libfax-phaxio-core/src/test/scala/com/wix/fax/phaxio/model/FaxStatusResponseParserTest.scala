package com.wix.fax.phaxio.model

import org.specs2.mutable.SpecWithJUnit
import org.specs2.specification.Scope

class FaxStatusResponseParserTest extends SpecWithJUnit {
  trait Ctx extends Scope {
    val someFaxStatusResponse = FaxStatusResponse(
      success = true,
      message = "some message",
      data = Some(Fax(
        id = 123L,
        direction = Direction.sent,
        num_pages = 3,
        cost = 11,
        status = Status.success,
        is_test = false,
        requested_at = System.currentTimeMillis(),
        from_number = Some("some from number"),
        to_number = Some("some to number"),
        recipients = List(Recipient(
          number = "some number",
          status = Status.success
        )),
        tags = Some(Map(
          "some tag key" -> "some tag value"
        ))
      ))
    )
  }

  "stringify and then parse" should {
    "yield an object similar to the original one" in new Ctx {
      val json = FaxStatusResponseParser.stringify(someFaxStatusResponse)
      FaxStatusResponseParser.parse(json) must beEqualTo(someFaxStatusResponse)
    }
  }
}
