package com.example.kenyaeducationfund.ui.Main.dialogues

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.adapters.UserAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LikedByDialog(
    private val userAdapter: UserAdapter
): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val rvLikedBy = RecyclerView(requireContext()).apply {
         adapter = userAdapter
          layoutManager = LinearLayoutManager(requireContext())

      }
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.liked_by_dialog_title)
            .setView(rvLikedBy)
            .create()
    }
}