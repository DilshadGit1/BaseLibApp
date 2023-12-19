package com.example.municipal.model

data class Pagination(val pageNum: Long,
                      val limit: Long,
                      val count: Long,
                      val nextPage: Long,
                      val prevPage: Long,
                      val totalPage: Int,)
