package com.hu.dgswgr.remote.mapper

import com.hu.dgswgr.domain.model.user.UserInfo
import com.hu.dgswgr.remote.response.home.HomeResponse

internal fun HomeResponse.toModel() =
    UserInfo(
        name = name,
        grade = grade,
        classNumber = classNumber,
        studentNumber = studentNumber
    )