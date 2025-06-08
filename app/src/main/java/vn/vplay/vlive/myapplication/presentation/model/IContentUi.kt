package vn.vplay.vlive.myapplication.presentation.model

import vn.vplay.vlive.myapplication.domain.entity.ISideEntity

interface IContentUi

interface IIsSelected {
    var isSelected: Boolean
}

interface ISlideItemUi : IIsSelected {
    val iSlideEntity: ISideEntity

    class SlideItemUi(override val iSlideEntity: ISideEntity) : ISlideItemUi {
        override var isSelected: Boolean = false
    }
}

interface ISlideUi : IContentUi {
    val list: List<ISlideItemUi>

    class SlideUi(override val list: List<ISlideItemUi>) : ISlideUi
}