package com.chalkboard.birthdays.ui.list.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chalkboard.birthdays.R
import com.chalkboard.birthdays.ui.detail.presentation.BirthdayDetailFragment.Companion.BUNDLE_BIRTHDAY
import com.chalkboard.birthdays.ui.list.data.Birthday
import kotlinx.android.synthetic.main.fragment_birthday_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirthdayListFragment : Fragment() {
    private val viewModel: BirthdayListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_birthday_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeUiState()

        viewModel.getBirthdays()
    }

    private fun setupView() {
        (activity as AppCompatActivity).supportActionBar?.show()

        birthdaysRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BirthdayListAdapter().apply {
                stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                onBirthdayTap = {
                    findNavController().navigate(R.id.openBirthdayDetail, bundleOf(BUNDLE_BIRTHDAY to it))
                }
            }
            itemAnimator = null
        }
    }

    private fun observeUiState() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                BirthdayListUiState.Error -> handleErrorState()
                is BirthdayListUiState.GetBirthdaysSuccess -> handleSuccessState(it.birthdays)
                is BirthdayListUiState.Loading -> handleLoadingState(it.show)
            }
        }
    }

    private fun handleErrorState() {
        Toast.makeText(requireContext(), getString(R.string.generic_error_message), Toast.LENGTH_SHORT).show()
    }

    private fun handleLoadingState(show: Boolean) {
        if (show) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    private fun handleSuccessState(birthdays: List<Birthday>) {
        (birthdaysRecyclerView.adapter as BirthdayListAdapter).bindData(birthdays)
    }
}