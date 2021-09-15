package br.com.beerfriends.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.databinding.FragmentProfileEditBinding
import br.com.beerfriends.viewmodel.ProfileEditViewModel

class ProfileEditFragment : Fragment() {

    private lateinit var profileEditViewModel: ProfileEditViewModel
    private var _binding: FragmentProfileEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileEditViewModel =
            ViewModelProvider(this).get(ProfileEditViewModel::class.java)

        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProfileEdit
        profileEditViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}