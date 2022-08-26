package com.persAssistant.shopping_list.data.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.*
import androidx.room.PrimaryKey
import com.persAssistant.shopping_list.data.database.DbStruct
import com.persAssistant.shopping_list.data.database.DbStruct.Category.Cols.DEFAULT_CATEGORIES_COUNT
import com.persAssistant.shopping_list.data.database.DbStruct.ShoppingListTable.Cols.DEFAULT_INVALID_ID
import com.persAssistant.shopping_list.common.EMPTY_STRING
import com.persAssistant.shopping_list.common.PRICE_DEFAULT_DOUBLE
import com.persAssistant.shopping_list.common.ZERO_POSITION

@Entity(
    tableName = DbStruct.Purchase.tableName,
    foreignKeys = [
        ForeignKey(
            entity = RoomCategory::class,
            parentColumns = arrayOf(DbStruct.Category.Cols.id),
            childColumns = arrayOf(DbStruct.Purchase.Cols.categoryId),
            onDelete = SET_DEFAULT,
        ),

        ForeignKey(
            entity = RoomShoppingList::class,
            parentColumns = arrayOf(DbStruct.ShoppingListTable.Cols.id),
            childColumns = arrayOf(DbStruct.Purchase.Cols.listId),
            onDelete = CASCADE,
        )
    ]
)

data class RoomPurchase(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbStruct.Purchase.Cols.id)
    var id: Long? = null,

    @ColumnInfo(name = DbStruct.Purchase.Cols.name)
    var name: String = EMPTY_STRING,

    @ColumnInfo(
        name = DbStruct.Purchase.Cols.categoryId,
        defaultValue = DEFAULT_CATEGORIES_COUNT.toString(),
    )
    var categoryId: Long = DEFAULT_CATEGORIES_COUNT,

    @ColumnInfo(name = DbStruct.Purchase.Cols.listId)
    var listId: Long = DEFAULT_INVALID_ID,

    @ColumnInfo(name = DbStruct.Purchase.Cols.price)
    var price: Double = PRICE_DEFAULT_DOUBLE,

    @ColumnInfo(name = DbStruct.Purchase.Cols.isCompleted)
    var isCompleted: Int = ZERO_POSITION,

    @ColumnInfo(name = DbStruct.Purchase.Cols.unit)
    var unit: String = EMPTY_STRING,

    @ColumnInfo(name = DbStruct.Purchase.Cols.quantity)
    var quantity: String = EMPTY_STRING,

    )
