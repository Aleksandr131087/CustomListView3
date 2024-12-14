package com.example.customlistview3

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyAlertDialog: DialogFragment() {

    private  var updateble: Updateble?=null
    private  var removable: Removable? =null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable=context as Removable?
        updateble=context as Updateble?
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val product = requireArguments().getSerializable("product")
        val builder = AlertDialog.Builder(requireActivity())
        return builder
            .setTitle("Внимание! Ваши дальнейшие действия?")
            .setPositiveButton("Показать информацию о продукте"){dialog, which ->
                updateble?.update(product as Product)
            }
            .setNeutralButton("Удалить"){
                    dialog, which ->removable?.remove(product as Product)
            }
            .setNegativeButton("Отмена", null)
            .create()
    }

}