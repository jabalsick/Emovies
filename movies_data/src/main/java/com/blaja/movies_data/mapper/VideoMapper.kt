package com.blaja.movies_data.mapper

import com.blaja.movies_data.model.Video
import com.blaja.movies_data.remote.model.VideoResponse

class VideoMapper : EntityMapper<Video, VideoResponse> {
    override fun mapToDomain(entity: VideoResponse): Video {
        return Video(
            id = entity.id,
            iso_3166_1 = entity.iso_3166_1,
            iso_639_1 = entity.iso_639_1,
            key = entity.key,
            name = entity.name,
            site = entity.site,
            size = entity.size,
            type = entity.type,
            official = entity.official
        )
    }

    override fun mapToEntity(model: Video): VideoResponse {
        return VideoResponse(
            id = model.id,
            iso_3166_1 = model.iso_3166_1,
            iso_639_1 = model.iso_639_1,
            key = model.key,
            name = model.name,
            site = model.site,
            size = model.size,
            type = model.type,
            official = model.official
        )
    }

}