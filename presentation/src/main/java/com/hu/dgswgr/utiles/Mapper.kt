package com.hu.dgswgr.utiles


internal fun String.replaceUrl() =
    this.replace("localhost", "10.0.2.2")

internal fun Int.toNumber(): String =
    if (this.toString().length > 1) this.toString() else "0${this}"