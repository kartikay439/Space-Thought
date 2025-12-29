package com.kartikay.business.usecase.feed_usecase

import com.kartikay.business.repository.FeedRepository


class GetAllPostFeedUseCase(
    private val repository: FeedRepository
) {
    operator fun invoke() = repository.getFeed()
}
