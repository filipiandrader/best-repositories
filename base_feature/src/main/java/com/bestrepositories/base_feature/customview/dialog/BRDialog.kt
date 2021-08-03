package com.bestrepositories.base_feature.customview.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bestrepositories.base_feature.customview.base.BaseDialogFragment
import com.bestrepositories.base_feature.databinding.BrDialogBinding
import com.bestrepositories.base_feature.utils.delegateproperties.viewInflateBinding
import com.bestrepositories.base_feature.utils.extensions.setGone
import com.bestrepositories.base_feature.utils.extensions.setVisible

class BRDialog(private val params: Params) : BaseDialogFragment() {

    private val binding by viewInflateBinding(BrDialogBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        updateWindowFeature()
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog?.window?.setLayout((getDeviceMetrics().widthPixels * 0.9).toInt(), height)
        }

        setupDialog()
    }

    private fun getDeviceMetrics(): DisplayMetrics {
        val metrics = DisplayMetrics()
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getRealMetrics(metrics)
        return metrics
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        params.onDismiss?.invoke()
    }

    private fun setupDialog() {
        binding.apply {
            params.validateParams()

            params.drawableId?.let { icon ->
                brDialogImageView.setVisible()
                brDialogImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        icon
                    )
                )
            } ?: brDialogImageView.setGone()

            if (params.title.isEmpty()) {
                brDialogTitleTextView.setGone()
            } else {
                brDialogTitleTextView.setVisible()
                brDialogTitleTextView.text = params.title
            }

            if (params.description.isEmpty()) {
                brDialogDescriptionTextView.setGone()
            } else {
                brDialogDescriptionTextView.text = params.description
                brDialogDescriptionTextView.setVisible()
            }

            params.positiveTextAction?.let { positiveTextAction ->
                brDialogPositiveButton.text = positiveTextAction
                brDialogPositiveButton.setOnClickListener {
                    params.positiveAction?.invoke()
                    dismiss()
                }
            }

            if (params.negativeTextAction.isEmpty()) {
                brDialogNegativeButton.setGone()
            } else {
                brDialogNegativeButton.isEnabled = params.disableNegativeButton
                brDialogNegativeButton.text = params.negativeTextAction
                brDialogNegativeButton.setVisible()
                brDialogNegativeButton.setOnClickListener {
                    params.negativeAction?.invoke()
                    dismiss()
                }
            }

            isCancelable = params.isCancelable
        }
    }

    data class Params(
        var drawableId: Int? = null,
        var title: CharSequence = "",
        var description: CharSequence = "",
        var positiveTextAction: String? = null,
        var negativeTextAction: String = "",
        var positiveAction: (() -> Unit)? = null,
        var negativeAction: (() -> Unit)? = null,
        var onDismiss: (() -> Unit)? = null,
        var disableNegativeButton: Boolean = true,
        var isCancelable: Boolean = false
    ) {
        internal fun validateParams() {
            validateMessage()
            validateNegativeAction()
        }

        private fun validateMessage() {
            if (title.isEmpty() && description.isEmpty())
                throw RuntimeException("Dialog doesn't contains any message")
        }

        private fun validateNegativeAction() {
            if (negativeTextAction.isEmpty() && negativeAction != null)
                throw RuntimeException("Negative action can't be reached. NegativeTextAction is missing.")
        }
    }
}