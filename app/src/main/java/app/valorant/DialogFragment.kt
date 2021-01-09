package app.valorant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.view.*

class DialogFragment(val nombre: String, val desc: String) : DialogFragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_dialog, container, false)

        rootView.element_name.text = nombre
        rootView.element_description.text = desc
        rootView.close_btn.setOnClickListener {
            dismiss()
        }
        return rootView
    }
}