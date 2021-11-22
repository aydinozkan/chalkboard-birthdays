package com.chalkboard.birthdays.detail.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.chalkboard.birthdays.R
import com.chalkboard.birthdays.extensions.extractInitials
import com.chalkboard.birthdays.list.data.Birthday
import kotlinx.android.synthetic.main.fragment_birthday_detail.*

class BirthdayDetailFragment : Fragment() {
    lateinit var birthday: Birthday

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_birthday_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        birthday = arguments?.get(BUNDLE_BIRTHDAY) as Birthday

        setupView()
        setupListeners()
    }

    private fun setupView() {
        nameSurnameInitialsTextView.text = birthday.name.nameSurname.extractInitials()
        nameSurnameTextView.text = birthday.name.nameSurname
        ageTextView.text = getString(R.string.birthday_detail_age, birthday.dateOfBirth.age)
    }

    private fun setupListeners() {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val BUNDLE_BIRTHDAY = "bundle_birthday"
    }
}