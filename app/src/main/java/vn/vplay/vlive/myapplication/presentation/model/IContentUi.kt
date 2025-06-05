package vn.vplay.vlive.myapplication.presentation.model

import vn.vplay.vlive.myapplication.domain.entity.ISideEntity

interface IContentUi

interface IIsSelected {
    var isSelected: Boolean
}

interface ISlideUi : IContentUi,IIsSelected {
    val iSlideEntity: ISideEntity

    class SlideUi(override val iSlideEntity: ISideEntity) : ISlideUi {
        override var isSelected: Boolean = false
    }
}