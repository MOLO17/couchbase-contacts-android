package com.molo17.couchbasedemo.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molo17.couchbasedemo.R

/**
 * Created by Damiano Giusti on 2020-02-05.
 */
class ContactsListRecyclerViewAdapter :
    RecyclerView.Adapter<ContactsListRecyclerViewAdapter.ViewHolder>() {

    var contacts: List<UiContact> = emptyList()
    var onContactClickListener: ((contactId: String) -> Unit)? = null
    var onContactLongClickListener: ((contactId: String) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_contact, parent, false)
            .let(::ViewHolder)
            .apply {
                itemView.setOnClickListener { onContactClickListener?.invoke(contacts[adapterPosition].identifier) }
                itemView.setOnLongClickListener {
                    onContactLongClickListener?.invoke(contacts[adapterPosition].identifier)
                    true
                }
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameSurname.text = contact.displayName
        holder.phoneNumber.text = contact.phoneNumber
    }

    override fun getItemCount(): Int = contacts.size

    override fun getItemId(position: Int): Long = contacts[position].identifier.hashCode().toLong()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameSurname: TextView = view.findViewById(R.id.nameSurnameTextView)
        val phoneNumber: TextView = view.findViewById(R.id.phoneNumberTextView)
    }
}