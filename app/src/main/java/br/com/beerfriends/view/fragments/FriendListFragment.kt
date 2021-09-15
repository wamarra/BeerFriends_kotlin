package br.com.beerfriends.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.databinding.FragmentFriendListBinding
import br.com.beerfriends.viewmodel.FriendListViewModel

class FriendListFragment : Fragment() {

    private lateinit var friendListViewModel: FriendListViewModel
    private var _binding: FragmentFriendListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        friendListViewModel =
            ViewModelProvider(this).get(FriendListViewModel::class.java)

        _binding = FragmentFriendListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFriendList
        friendListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}