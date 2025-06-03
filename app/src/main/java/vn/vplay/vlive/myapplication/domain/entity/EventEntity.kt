package vn.vplay.vlive.myapplication.domain.entity

import vn.vplay.vlive.myapplication.data.remote.dto.EventDTO


interface IEventEntity {
    val idEvent: String
    val nameEvent: String
    val urlEvent: String

    class EventEntity(eventDTO: EventDTO?) : IEventEntity {
        override val idEvent: String = eventDTO?.id.orEmpty()
        override val nameEvent: String = eventDTO?.name.orEmpty()
        override val urlEvent: String = eventDTO?.url.orEmpty()
    }

}


data class D(
    val id: String,
    val name: String
)

interface A {
    val id: String
    val name: String

    class B(d: D) : A {
        override val id: String = d.id
        override val name: String = d.name
    }
}

interface E : A {
    val url: String
}

class F(d: D) : E {
    override val id: String = d.id
    override val name: String = d.name
    override val url: String = ""
}

class C(d: D) : A by A.B(d)
