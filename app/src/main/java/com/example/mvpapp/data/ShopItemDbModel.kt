package com.example.mvpapp.data

import androidx.room.AutoMigration
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvpapp.domain.ShopItem


@Entity(tableName = "shop_item")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)