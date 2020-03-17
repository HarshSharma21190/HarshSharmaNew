package com.soc.gen.auth.data.processing

case class AuthData(authCode: String, subReqId: String, aua: Option[Long], serviceAgency: String, resState: String)
