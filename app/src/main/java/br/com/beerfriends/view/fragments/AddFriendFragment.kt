package br.com.beerfriends.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.databinding.FragmentAddFriendBinding
import br.com.beerfriends.viewmodel.AddFriendViewModel

class AddFriendFragment : Fragment() {

    private lateinit var addFriendViewModel: AddFriendViewModel
    private var _binding: FragmentAddFriendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addFriendViewModel =
            ViewModelProvider(this).get(AddFriendViewModel::class.java)

        _binding = FragmentAddFriendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textAddFriend
        addFriendViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}