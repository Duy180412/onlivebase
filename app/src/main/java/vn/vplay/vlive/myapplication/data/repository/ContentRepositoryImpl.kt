package vn.vplay.vlive.myapplication.data.repository

import vn.vplay.vlive.myapplication.data.remote.api.ContentApi
import vn.vplay.vlive.myapplication.data.remote.dto.SlideDTO
import vn.vplay.vlive.myapplication.data.remote.response.DefaultResponse
import vn.vplay.vlive.myapplication.data.remote.response.Resource
import vn.vplay.vlive.myapplication.domain.repository.ContentRepo
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentApi: ContentApi) : ContentRepo,
    BaseResource {

    override suspend fun getSlides(): Resource<DefaultResponse<List<SlideDTO>>> {
        return safeApiCall { contentApi.getSlides() }
    }

}

