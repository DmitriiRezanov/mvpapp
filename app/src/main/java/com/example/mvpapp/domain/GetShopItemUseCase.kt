package com.example.mvpapp.domain

class GetShopItemUseCase (private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int) : ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}