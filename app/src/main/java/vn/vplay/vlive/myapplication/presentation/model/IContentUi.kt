package vn.vplay.vlive.myapplication.presentation.model

import vn.vplay.vlive.myapplication.domain.entity.ISideEntity

interface IContentUi

interface IIsSelected {
    val isSelected: Boolean
}

interface ISlideUi : IContentUi,IIsSelected {
    val iSlideEntity: ISideEntity

    class SlideUi(override val iSlideEntity: ISideEntity) : ISlideUi {
        override val isSelected: Boolean = false
    }
}