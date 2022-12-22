package com.shibainu.li.dblibs

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class TestDb(
    @Id
    var id :Long = 0,val num:Int,val time:String)
