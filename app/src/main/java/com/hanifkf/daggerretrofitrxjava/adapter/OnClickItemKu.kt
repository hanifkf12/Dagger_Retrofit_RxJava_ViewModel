package com.hanifkf.daggerretrofitrxjava.adapter

import com.hanifkf.daggerretrofitrxjava.model.Result

interface OnClickItemKu {
    fun onClickItemKu(result: Result)

    fun onLongClick(result: Result)
}