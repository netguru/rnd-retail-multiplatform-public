package com.netguru.commondomain.ar

actual fun String.getPlatformArModel(): String = "models/$this.glb"
