package com.chalkboard.birthdays.list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chalkboard.birthdays.R
import com.chalkboard.birthdays.extensions.extractInitials
import com.chalkboard.birthdays.helpers.DiffUtilsCallback
import com.chalkboard.birthdays.list.data.Birthday
import kotlinx.android.synthetic.main.list_item_birthday.view.*
import java.text.SimpleDateFormat
import java.util.*

class BirthdayListAdapter : RecyclerView.Adapter<BirthdayListAdapter.ViewHolder>() {
    var onBirthdayTap: ((birthday: Birthday) -> Unit)? = null

    private var birthdays = emptyList<Birthday>()

    fun bindData(birthdays: List<Birthday>) {
        val diff = DiffUtil.calculateDiff(DiffUtilsCallback(birthdays, this.birthdays))
        diff.dispatchUpdatesTo(this)
        this.birthdays = birthdays
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(birthdays[position])
    }

    override fun getItemCount() = birthdays.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) : this(LayoutInflater.from(parent.context).inflate(R.layout.list_item_birthday, parent, false))

        internal fun bind(birthday: Birthday) {
            setupListeners(birthday)

            with(itemView) {
                nameSurnameInitialsTextView.text = birthday.name.nameSurname.extractInitials()
                nameSurnameTextView.text = birthday.name.nameSurname
                birthdateTextView.text = SimpleDateFormat(BIRTHDAY_DATE_FORMAT, Locale.getDefault()).format(birthday.dateOfBirth.date)
            }
        }

        private fun setupListeners(birthday: Birthday) {
            itemView.setOnClickListener {
                onBirthdayTap?.invoke(birthday)
            }
        }
    }

    companion object {
        private const val BIRTHDAY_DATE_FORMAT = "dd-MM-yyyy"
    }
}
