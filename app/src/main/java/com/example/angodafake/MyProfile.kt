package com.example.angodafake

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.angodafake.Utilities.UserUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [MyProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyProfile(private var idUser: String) : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btn_bookmark: Button
    private lateinit var btn_logout: Button
    private lateinit var bookmarkTittle: TextView
    private lateinit var btn_myHotel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        auth = Firebase.auth
        initUI(view)
        UserUtils.getNameByID(idUser) {
            bookmarkTittle.text = "Chào mừng, $it!"
        }

        btn_bookmark.setOnClickListener {
            val mainActivity = requireActivity() as MainActivity
            mainActivity.navigateToBookmarkFragment()
        }

        btn_myHotel.setOnClickListener {
            val mainActivity = requireActivity() as MainActivity
            mainActivity.navigateToMyHotelFragment()
        }

        btn_logout.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return view
    }

    private fun initUI(view: View){
        btn_bookmark = view.findViewById(R.id.btn_bookmark)
        btn_logout = view.findViewById(R.id.btn_logout)
        bookmarkTittle = view.findViewById(R.id.bookmarkTittle)
        btn_myHotel = view.findViewById(R.id.btn_my_hotel)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyProfile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, idUser: String) =
            MyProfile(idUser).apply {
                arguments = Bundle().apply {
                }
            }
    }

}