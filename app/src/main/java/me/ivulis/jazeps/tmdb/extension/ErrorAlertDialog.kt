package me.ivulis.jazeps.tmdb.extension

import android.content.DialogInterface
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.ivulis.jazeps.tmdb.R

fun Fragment.alert(
    @StyleRes style: Int = 0,
    dialogBuilder: MaterialAlertDialogBuilder.() -> Unit
) {
    context?.let {
        MaterialAlertDialogBuilder(it, style)
            .apply {
                setTitle(getString(R.string.error_alert_dialog_title))
                setCancelable(false)
                dialogBuilder()
                create()
                show()
            }
    }
}

fun MaterialAlertDialogBuilder.negativeButton(
    text: String = context.getString(R.string.error_alert_dialog_negative_button_text),
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun MaterialAlertDialogBuilder.positiveButton(
    text: String = context.getString(R.string.error_alert_dialog_positive_button_text),
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}
