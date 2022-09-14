package com.netguru.retail.android.shared

import com.netguru.commondomain.shop.model.Price

fun Price.format(): String =
    currency + "%,.2f".format(amount)
