package vn.vplay.vlive.myapplication.domain.repository

import vn.vplay.vlive.myapplication.data.remote.dto.SlideDTO
import vn.vplay.vlive.myapplication.data.remote.response.DefaultResponse
import vn.vplay.vlive.myapplication.data.remote.response.Resource

interface ContentRepo {
    suspend fun getSlides(): Resource<DefaultResponse<List<SlideDTO>>>
}



