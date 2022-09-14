package com.netguru.commondata

import com.netguru.commondomain.userContent.UserContentDataSource
import com.netguru.commondomain.userContent.UserContentImage

class UserContentMockDataSource : UserContentDataSource {

    override suspend fun getUserContentImages(productId: String): List<UserContentImage> =
        listOf(
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1Rjvdm5rjPGa4oX2b2EATNs2JrZNzujBI"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1ibhGRurd66fkLsyNeeGnOxN9V6v5xpJD"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1PWGTmha75GOQVIacZF0-50poK3s9TRmB"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1xyY-9se0fhYn8ourLxuEGfAGkgcD-T9w"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1vTH-cbvvRYPcU1Oa5ABrm4FIehEMsCuR"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1Hxx8WzC7_rPmKPjDYcbQXRa6B1qXg2H8"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1pqx1lJcVnf5mHJUfibRsszInOsKfh50Q"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1HdWbBaPwuaDfLIcAvCgGElrEq6Jzhyrx"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/1vUKThXUrPVEf7jRI61E2yVLI85dwe4aE"
            ),
            UserContentImage(
                imageUrl = "https://lh3.googleusercontent.com/d/16ZD_rEtSjn5txMZHohRpqnVhXRmK8nsj"
            )
        )
}
