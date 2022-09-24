package com.vinctus.formula

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class EvalTests extends AnyFreeSpec with Matchers:

  "add" in {
    evalExpr("3 + 4") shouldBe "7"
  }
